package com.wemakesoftware.navigationsystem.controllers;

import com.wemakesoftware.navigationsystem.entities.MobileStation;
import com.wemakesoftware.navigationsystem.entities.dto.MobileStationLocation;
import com.wemakesoftware.navigationsystem.entities.dto.DetectionReport;
import com.wemakesoftware.navigationsystem.exceptions.MobileStationNotFoundException;
import com.wemakesoftware.navigationsystem.services.MobileStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/")
public class NavigationController {

    @Autowired MobileStationService mobileStationService;

    @GetMapping(value = "/location/{uuid}")
    public ResponseEntity<MobileStationLocation> getLocation(@PathVariable UUID uuid) {
        MobileStation mobileStation = mobileStationService.findById(uuid);

        if (mobileStation == null) {
            throw new MobileStationNotFoundException("Mobile station with UUID " + uuid.toString() + " not found.");
        }

        MobileStationLocation location = new MobileStationLocation();
        location.setMobileStationId(uuid);
        location.setX(mobileStation.getLastKnownX());
        location.setY(mobileStation.getLastKnownY());
        location.setErrorRadius(0);
        location.setErrorCode(0);
        location.setErrorDescription(null);

        return ResponseEntity.ok(location);
    }

    @PostMapping("/report")
    public ResponseEntity<Map<String, String>> receiveDetectionReport(@RequestBody DetectionReport detectionReport) {
        // Process the detection report here

        // Return a response
        Map<String, String> response = new HashMap<>();
        response.put("message", "Detection report received");
        return ResponseEntity.ok().body(response);
    }

}
