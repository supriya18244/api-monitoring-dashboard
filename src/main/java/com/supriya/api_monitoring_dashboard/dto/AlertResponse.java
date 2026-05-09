package com.supriya.api_monitoring_dashboard.dto;

public class AlertResponse {


    private String apiName;
    private long responseTime;
    private String status;
    private String severity;

    public AlertResponse(String apiName, long responseTime, String status, String severity) {
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.status = status;
        this.severity = severity;
    }

    public String getApiName() {
        return apiName;
    }


    public long getResponseTime() {
        return responseTime;
    }


    public String getStatus() {
        return status;
    }

    public String getSeverity() {
        return severity;
    }
}
