package com.wemakesoftware.navigationsystem;

import com.wemakesoftware.navigationsystem.entities.MobileStation;
import com.wemakesoftware.navigationsystem.entities.BaseStation;
import com.wemakesoftware.navigationsystem.entities.Coordinate;
import com.wemakesoftware.navigationsystem.entities.dto.DetectionReport;
import com.wemakesoftware.navigationsystem.entities.dto.MobileStationReport;
import com.wemakesoftware.navigationsystem.repositories.MobileStationRepository;
import com.wemakesoftware.navigationsystem.repositories.BaseStationRepository;
import com.wemakesoftware.navigationsystem.services.RandomCoordinateGenerator;
import com.wemakesoftware.navigationsystem.services.StationInitializer;
import com.wemakesoftware.navigationsystem.repositories.DetectionReportRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.ArrayList;

import java.time.Instant;

@Configuration
@Profile("test")
@Slf4j
public class TestConfiguration {

    @Autowired
    private BaseStationRepository baseStationRepository;

    @Autowired
    private MobileStationRepository mobileStationRepository;

    @Autowired
    private DetectionReportRepository detectionReportRepository;

    @PostConstruct
    public void initDatabase() {
        StationInitializer stationInitializer = new StationInitializer();
        List<BaseStation> baseStations = stationInitializer.initBaseStations();
        List<MobileStation> mobileStations = stationInitializer.initMobileStations();

        log.info("Saving {} base stations and {} mobile stations...", baseStations.size(), mobileStations.size());

        baseStationRepository.saveAll(baseStations);
        mobileStationRepository.saveAll(mobileStations);

        // Call flush() to ensure that the entities are persisted in the database
        baseStationRepository.flush();
        mobileStationRepository.flush();

        log.info("Data saved to the database");

        log.info("Base stations in the database: {}", baseStationRepository.count());
        log.info("Mobile stations in the database: {}", mobileStationRepository.count());

        generateReportsForBaseStations();
    }

    public void generateReportsForBaseStations() {
        RandomCoordinateGenerator randomCoordinateGenerator = new RandomCoordinateGenerator();
        List<BaseStation> baseStations = baseStationRepository.findAll();
        List<MobileStation> mobileStations = mobileStationRepository.findAll();

        for (BaseStation baseStation : baseStations) {
            // Create a new DetectionReport
            DetectionReport detectionReport = new DetectionReport();
            detectionReport.setBaseStationId(baseStation.getId());
            List<MobileStationReport> mobileStationReports = new ArrayList<>();

            for (MobileStation mobileStation : mobileStations) {
                float distance = randomCoordinateGenerator.calculateDistance(
                        new Coordinate(baseStation.getX(), baseStation.getY()),
                        new Coordinate(mobileStation.getLastKnownX(), mobileStation.getLastKnownY())
                );

                if (distance <= baseStation.getDetectionRadiusInMeters()) {
                    // Create a new MobileStationReport
                    MobileStationReport mobileStationReport = new MobileStationReport();
                    mobileStationReport.setMobileStationId(mobileStation.getId());
                    mobileStationReport.setDistance(distance);
                    mobileStationReport.setTimestamp(Instant.now());

                    // Set the relationship between MobileStationReport and DetectionReport
                    mobileStationReport.setDetectionReport(detectionReport);
                    mobileStationReports.add(mobileStationReport);
                }
            }

            // Save the DetectionReport and related MobileStationReports
            if (!mobileStationReports.isEmpty()) {
                detectionReport.setReports(mobileStationReports);
                detectionReportRepository.save(detectionReport);
            }
        }
    }

}