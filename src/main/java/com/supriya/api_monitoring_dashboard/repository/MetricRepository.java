package com.supriya.api_monitoring_dashboard.repository;

import com.supriya.api_monitoring_dashboard.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetricRepository extends JpaRepository<Metric, Long> {

    List<Metric> findByApiName(String apiName);
    List<Metric> findByStatus(String status);
    List<Metric> findByResponseTimeGreaterThan(long responseTime);

}