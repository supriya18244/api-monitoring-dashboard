package com.supriya.api_monitoring_dashboard.controller;

import com.supriya.api_monitoring_dashboard.model.Metric;
import com.supriya.api_monitoring_dashboard.service.MetricService;
import org.springframework.web.bind.annotation.*;

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
}
