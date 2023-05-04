package com.wemakesoftware.navigationsystem.integration;

import com.wemakesoftware.navigationsystem.entities.MobileStation;
import com.wemakesoftware.navigationsystem.entities.BaseStation;
import com.wemakesoftware.navigationsystem.entities.Coordinate;
import com.wemakesoftware.navigationsystem.entities.dto.DetectionReport;
import com.wemakesoftware.navigationsystem.entities.dto.MobileStationReport;
import com.wemakesoftware.navigationsystem.repositories.MobileStationRepository;
import com.wemakesoftware.navigationsystem.repositories.BaseStationRepository;
import com.wemakesoftware.navigationsystem.services.RandomCoordinateGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StationControllersIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BaseStationRepository baseStationRepository;

    @Autowired
    private MobileStationRepository mobileStationRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenDetectionReport_whenPostToEndpoint1_thenResponseIsOk() {
        // Get a random base station and mobile station
        BaseStation baseStation = baseStationRepository.findAll().get(0);
        MobileStation mobileStation = mobileStationRepository.findAll().get(0);

        // Calculate the distance between the base station and mobile station using the Haversine formula
        RandomCoordinateGenerator randomCoordinateGenerator = new RandomCoordinateGenerator();
        Coordinate baseCoordinate = new Coordinate(baseStation.getY(), baseStation.getX());
        Coordinate mobileCoordinate = new Coordinate(mobileStation.getLastKnownY(), mobileStation.getLastKnownX());
        float distance = randomCoordinateGenerator.calculateDistance(baseCoordinate, mobileCoordinate);

        // Create a detection report
        DetectionReport detectionReport = new DetectionReport();
        detectionReport.setBaseStationId(baseStation.getId());
        MobileStationReport mobileStationReport = new MobileStationReport();
        mobileStationReport.setMobileStationId(mobileStation.getId());
        mobileStationReport.setDistance(distance);
        mobileStationReport.setTimestamp(new Date().toInstant());
        detectionReport.setReports(Collections.singletonList(mobileStationReport));

        // Send a POST request to the endpoint with the detection report as the request body
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DetectionReport> requestEntity = new HttpEntity<>(detectionReport, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/report", requestEntity, String.class);

        // Log the report data
        log.info("Base station id: {}", baseStation.getId());
        log.info("Mobile station id: {}", mobileStation.getId());
        log.info("Distance: {}", distance);
        log.info("Timestamp: {}", mobileStationReport.getTimestamp());

        // Assert that the response is OK and matches the expected output
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String expectedOutput = "{\"message\":\"Detection report received\"}";
        assertEquals(expectedOutput, response.getBody());
    }

    @Test
    public void givenMobileStationId_whenGetLocation_thenResponseIsOk() throws Exception {
        // Get a random mobile station
        MobileStation mobileStation = mobileStationRepository.findAll().get(0);

        // Send a GET request to the endpoint with the mobile station UUID as a path variable
        mockMvc.perform(MockMvcRequestBuilders.get("/location/{uuid}", mobileStation.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mobileStationId").value(mobileStation.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.x").value(mobileStation.getLastKnownX()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.y").value(mobileStation.getLastKnownY()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorRadius").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorDescription").doesNotExist());
    }

}