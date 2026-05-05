package com.supriya.api_monitoring_dashboard.service;

import com.supriya.api_monitoring_dashboard.model.Metric;
import com.supriya.api_monitoring_dashboard.repository.MetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricService {

    private final MetricRepository metricRepository;


    public MetricService(MetricRepository metricRepository) {
        this.metricRepository = metricRepository;
    }

    public Metric saveMetric(Metric metric){
        return metricRepository.save(metric);
    }

    public List<Metric> getAllMetrics(){
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
}
