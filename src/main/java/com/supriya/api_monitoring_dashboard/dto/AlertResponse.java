package com.supriya.api_monitoring_dashboard.dto;

import java.time.LocalDateTime;

public class AlertResponse {

    private final String apiName;
    private final long responseTime;
    private final String status;
    private final AlertType alertType;
    private final AlertSeverity severity;
    private final String message;
    private final LocalDateTime timestamp;

    public AlertResponse(
            String apiName,
            long responseTime,
            String status,
            AlertType alertType,
            AlertSeverity severity,
            String message,
            LocalDateTime timestamp
    ) {
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.status = status;
        this.alertType = alertType;
        this.severity = severity;
        this.message = message;
        this.timestamp = timestamp;
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

    public AlertType getAlertType() {
        return alertType;
    }

    public AlertSeverity getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}