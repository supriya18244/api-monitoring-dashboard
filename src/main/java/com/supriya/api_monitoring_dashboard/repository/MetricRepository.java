package com.supriya.api_monitoring_dashboard.repository;

import com.supriya.api_monitoring_dashboard.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}