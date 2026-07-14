package com.supriya.api_monitoring_dashboard.service;

import com.supriya.api_monitoring_dashboard.dto.AlertResponse;
import com.supriya.api_monitoring_dashboard.dto.AlertSeverity;
import com.supriya.api_monitoring_dashboard.dto.AlertType;
import com.supriya.api_monitoring_dashboard.dto.MetricSummary;
import com.supriya.api_monitoring_dashboard.model.Metric;
import com.supriya.api_monitoring_dashboard.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetricService {

    private final MetricRepository metricRepository;

    private final long slowThreshold;
    private final long highThreshold;
    private final long criticalThreshold;

    public MetricService(
            MetricRepository metricRepository,
            @Value("${monitoring.alert.slow-threshold}") long slowThreshold,
            @Value("${monitoring.alert.high-threshold}") long highThreshold,
            @Value("${monitoring.alert.critical-threshold}") long criticalThreshold
    ) {
        this.metricRepository = metricRepository;
        this.slowThreshold = slowThreshold;
        this.highThreshold = highThreshold;
        this.criticalThreshold = criticalThreshold;
    }

    public Metric saveMetric(Metric metric) {

        if (metric.getTimestamp() == null) {
            metric.setTimestamp(LocalDateTime.now());
        }

        return metricRepository.save(metric);
    }

    public List<Metric> getAllMetrics() {
        return metricRepository.findAll();
    }

    public List<Metric> getByApiName(String apiName) {
        return metricRepository.findByApiName(apiName);
    }

    public List<Metric> getByStatus(String status) {
        return metricRepository.findByStatus(status);
    }

    public List<Metric> getSlowApis(long responseTime) {
        return metricRepository.findByResponseTimeGreaterThan(responseTime);
    }

    public MetricSummary getSummary() {

        List<Metric> metrics = metricRepository.findAll();

        long totalRequests = metrics.size();

        long failedRequests = metrics.stream()
                .filter(this::isFailure)
                .count();

        double averageResponseTime = metrics.stream()
                .mapToLong(Metric::getResponseTime)
                .average()
                .orElse(0.0);

        return new MetricSummary(
                totalRequests,
                failedRequests,
                averageResponseTime
        );
    }

    public List<AlertResponse> getAlerts() {

        return metricRepository.findAll()
                .stream()
                .filter(this::shouldCreateAlert)
                .map(this::createAlertResponse)
                .toList();
    }

    private boolean shouldCreateAlert(Metric metric) {
        return isFailure(metric)
                || metric.getResponseTime() > slowThreshold;
    }

    private boolean isFailure(Metric metric) {
        return "FAILURE".equalsIgnoreCase(metric.getStatus())
                || "FAILED".equalsIgnoreCase(metric.getStatus());
    }

    private AlertResponse createAlertResponse(Metric metric) {

        if (isFailure(metric)) {
            return new AlertResponse(
                    metric.getApiName(),
                    metric.getResponseTime(),
                    metric.getStatus(),
                    AlertType.API_FAILURE,
                    AlertSeverity.CRITICAL,
                    "API request failed",
                    metric.getTimestamp()
            );
        }

        AlertSeverity severity = calculateSeverity(metric.getResponseTime());

        String message = String.format(
                "API response time of %d ms exceeded the slow threshold of %d ms",
                metric.getResponseTime(),
                slowThreshold
        );

        return new AlertResponse(
                metric.getApiName(),
                metric.getResponseTime(),
                metric.getStatus(),
                AlertType.SLOW_RESPONSE,
                severity,
                message,
                metric.getTimestamp()
        );
    }

    private AlertSeverity calculateSeverity(long responseTime) {

        if (responseTime > criticalThreshold) {
            return AlertSeverity.CRITICAL;
        }

        if (responseTime > highThreshold) {
            return AlertSeverity.HIGH;
        }

        if (responseTime > slowThreshold) {
            return AlertSeverity.MEDIUM;
        }

        return AlertSeverity.LOW;
    }
}