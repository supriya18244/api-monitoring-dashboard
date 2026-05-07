package com.supriya.api_monitoring_dashboard.dto;

import lombok.Getter;

@Getter
public class MetricSummary {

    private final long totalRequests;
    private final long failedRequests;
    private final double averageResponseTime;

    public MetricSummary(long totalRequests, long failedRequests, double averageResponseTime) {
        this.totalRequests = totalRequests;
        this.failedRequests = failedRequests;
        this.averageResponseTime = averageResponseTime;
    }

}
