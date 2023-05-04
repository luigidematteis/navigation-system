package com.wemakesoftware.navigationsystem.entities.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.time.Instant;

@Slf4j
@Entity
@Table(name = "mobile_station_report")
@Data
public class MobileStationReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detection_report_id")
    private DetectionReport detectionReport;

    private UUID mobileStationId;

    private float distance;

    private Instant timestamp;

}
