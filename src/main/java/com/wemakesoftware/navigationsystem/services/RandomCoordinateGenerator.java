package com.wemakesoftware.navigationsystem.services;

import com.wemakesoftware.navigationsystem.entities.Coordinate;

import java.util.Random;

public class RandomCoordinateGenerator {
    public static final float EARTH_RADIUS = 6371; // in kilometers
    public static final float MIN_LATITUDE = -90;
    public static final float MAX_LATITUDE = 90;
    public static final float MIN_LONGITUDE = -180;
    public static final float MAX_LONGITUDE = 180;

    private Random random;

    public RandomCoordinateGenerator() {
        this.random = new Random();
    }

    public Coordinate generateRandomCoordinate() {
        float latitude = MIN_LATITUDE + (MAX_LATITUDE - MIN_LATITUDE) * random.nextFloat();
        float longitude = MIN_LONGITUDE + (MAX_LONGITUDE - MIN_LONGITUDE) * random.nextFloat();

        // Ensure that the latitude is within the valid range of -90 to 90 degrees
        if (latitude < MIN_LATITUDE) {
            latitude = MIN_LATITUDE;
        } else if (latitude > MAX_LATITUDE) {
            latitude = MAX_LATITUDE;
        }

        // Ensure that the longitude is within the valid range of -180 to 180 degrees
        if (longitude < MIN_LONGITUDE) {
            longitude = MIN_LONGITUDE;
        } else if (longitude > MAX_LONGITUDE) {
            longitude = MAX_LONGITUDE;
        }

        return new Coordinate(longitude, latitude);
    }

    public float generateRandomDetectionRadius() {
        // Generate a random detection radius between 100 meters and 10 kilometers
        return 100 + random.nextFloat() * 9900;
    }

    // Helper method to calculate the distance between two coordinates using the Haversine formula
    public float calculateDistance(Coordinate coordinate1, Coordinate coordinate2) {
        float lat1 = (float)Math.toRadians(coordinate1.getY());
        float lon1 = (float)Math.toRadians(coordinate1.getX());
        float lat2 = (float)Math.toRadians(coordinate2.getY());
        float lon2 = (float)Math.toRadians(coordinate2.getX());

        float dLat = lat2 - lat1;
        float dLon = lon2 - lon1;

        float a = (float)(Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2));

        float c = (float)(2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));

        return EARTH_RADIUS * c;
    }

}