package com.wemakesoftware.navigationsystem.services;

import com.wemakesoftware.navigationsystem.entities.BaseStation;
import com.wemakesoftware.navigationsystem.entities.MobileStation;
import com.wemakesoftware.navigationsystem.entities.Coordinate;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class StationInitializer {

    private RandomCoordinateGenerator randomCoordinateGenerator = new RandomCoordinateGenerator();
    private static final int NUM_BASE_STATIONS = 99;
    private static final int NUM_MOBILE_STATIONS = 99;

    public List<BaseStation> initBaseStations() {
        List<BaseStation> baseStations = new ArrayList<>();
        for (int i = 0; i < NUM_BASE_STATIONS; i++) {
            float x = getRandomCoordinate().getX();
            float y = getRandomCoordinate().getY();
            float radius = getRandomDetectionRadius();
            BaseStation baseStation = new BaseStation();
            baseStation.setId(UUID.randomUUID());
            baseStation.setX(x);
            baseStation.setY(y);
            baseStation.setName("baseStation" + i);
            baseStation.setDetectionRadiusInMeters(radius);
            baseStations.add(baseStation);
        }
        return baseStations;
    }

    public List<MobileStation> initMobileStations() {
        List<MobileStation> mobileStations = new ArrayList<>();
        for (int i = 0; i < NUM_MOBILE_STATIONS; i++) {
            Coordinate randomCoordinate = getRandomCoordinate();
            float x = randomCoordinate.getX();
            float y = randomCoordinate.getY();
            MobileStation mobileStation = new MobileStation();
            mobileStation.setId(UUID.randomUUID());
            mobileStation.setLastKnownX(x);
            mobileStation.setLastKnownY(y);
            mobileStations.add(mobileStation);
        }
        return mobileStations;
    }

    private Coordinate getRandomCoordinate() {
        return randomCoordinateGenerator.generateRandomCoordinate();
    }

    private float getRandomDetectionRadius() {
        return randomCoordinateGenerator.generateRandomDetectionRadius();
    }
}