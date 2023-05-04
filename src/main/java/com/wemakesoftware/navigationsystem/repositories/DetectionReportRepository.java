package com.wemakesoftware.navigationsystem.repositories;

import com.wemakesoftware.navigationsystem.entities.dto.DetectionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetectionReportRepository extends JpaRepository<DetectionReport, Long> {
}