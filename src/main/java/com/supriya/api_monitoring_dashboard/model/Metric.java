package com.supriya.api_monitoring_dashboard.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apiName;
    private long responseTime;
    private String status;
    private String timestamp;
}