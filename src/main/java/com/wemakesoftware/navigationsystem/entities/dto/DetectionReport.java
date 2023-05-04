package com.wemakesoftware.navigationsystem.entities.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@Entity
@Table(name = "detection_report")
@Data
public class DetectionReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID baseStationId;

    @OneToMany(mappedBy = "detectionReport", cascade = CascadeType.ALL)
    private List<MobileStationReport> reports;
}
