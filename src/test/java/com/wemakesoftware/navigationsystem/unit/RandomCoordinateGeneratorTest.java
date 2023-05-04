package com.wemakesoftware.navigationsystem.unit;

import com.wemakesoftware.navigationsystem.entities.Coordinate;
import com.wemakesoftware.navigationsystem.services.RandomCoordinateGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomCoordinateGeneratorTest {

    private RandomCoordinateGenerator randomCoordinateGenerator;

    @BeforeEach
    public void setUp() {
        randomCoordinateGenerator = new RandomCoordinateGenerator();
    }

    @Test
    public void testGenerateRandomCoordinate() {
        Coordinate coordinate = randomCoordinateGenerator.generateRandomCoordinate();

        System.out.println("Generated Latitude: " + coordinate.getY());
        System.out.println("Generated Longitude: " + coordinate.getX());

        assertTrue(coordinate.getY() >= RandomCoordinateGenerator.MIN_LATITUDE
                        && coordinate.getY() <= RandomCoordinateGenerator.MAX_LATITUDE,
                "Generated latitude should be within the valid range.");

        assertTrue(coordinate.getX() >= RandomCoordinateGenerator.MIN_LONGITUDE
                        && coordinate.getX() <= RandomCoordinateGenerator.MAX_LONGITUDE,
                "Generated longitude should be within the valid range.");
    }

    @Test
    public void testCalculateDistance() {
        Coordinate coordinate1 = randomCoordinateGenerator.generateRandomCoordinate();
        Coordinate coordinate2 = randomCoordinateGenerator.generateRandomCoordinate();

        float distance = randomCoordinateGenerator.calculateDistance(coordinate1, coordinate2);

        assertTrue(distance >= 0, "Distance between two coordinates should be non-negative.");
    }

}
