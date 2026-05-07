package com.supriya.api_monitoring_dashboard.controller;

import com.supriya.api_monitoring_dashboard.model.Metric;
import com.supriya.api_monitoring_dashboard.service.MetricService;
import org.springframework.web.bind.annotation.*;
import com.supriya.api_monitoring_dashboard.dto.MetricSummary;

import java.util.List;

@RestController
@RequestMapping("/metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @PostMapping
    public Metric createMetric(@RequestBody Metric metric){
        return metricService.saveMetric(metric);
    }

    @GetMapping
    public List<Metric> getMetrics(){
        return metricService.getAllMetrics();
    }

    @GetMapping("/api/{apiName}")
    public List<Metric> getByApiName(@PathVariable String apiName) {
        return metricService.getByApiName(apiName);
    }

    @GetMapping("/status/{status}")
    public List<Metric> getByStatus(@PathVariable String status) {
        return metricService.getByStatus(status);
    }

    @GetMapping("/slow")
    public List<Metric> getSlowApis(@RequestParam long threshold) {
        return metricService.getSlowApis(threshold);
    }

    @GetMapping("/summary")
    public MetricSummary getSummary() {
        return metricService.getSummary();
    }
}
