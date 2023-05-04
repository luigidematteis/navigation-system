package com.wemakesoftware.navigationsystem.unit;

import com.wemakesoftware.navigationsystem.entities.MobileStation;
import com.wemakesoftware.navigationsystem.repositories.MobileStationRepository;

import com.wemakesoftware.navigationsystem.services.MobileStationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;
import java.util.Optional;
@Slf4j
@ExtendWith(MockitoExtension.class)
public class MobileStationServiceTest {

    @InjectMocks
    private MobileStationService mobileStationService;

    @Mock
    private MobileStationRepository mobileStationRepository;

    private UUID mobileStationId;
    private MobileStation existingMobileStation;
    private MobileStation newMobileStation;

    @BeforeEach
    public void setUp() {
        mobileStationId = UUID.randomUUID();
        existingMobileStation = new MobileStation();
        existingMobileStation.setId(mobileStationId);

        newMobileStation = new MobileStation();
        newMobileStation.setId(UUID.randomUUID());
    }

    @Test
    public void testFindById_found() {
        // Mock the repository to return the existingMobileStation object when findById is called with mobileStationId.
        when(mobileStationRepository.findById(mobileStationId)).thenReturn(Optional.of(existingMobileStation));

        // Call the findById method of the service with mobileStationId.
        MobileStation result = mobileStationService.findById(mobileStationId);

        // Verify that the result returned by the service is the same as the existingMobileStation object.
        assertEquals(existingMobileStation, result);

        // Verify that the repository's findById method was called exactly once with mobileStationId.
        verify(mobileStationRepository, times(1)).findById(mobileStationId);
    }

    @Test
    public void testFindById_notFound() {
        // Mock the repository to return an empty optional when findById is called with mobileStationId.
        when(mobileStationRepository.findById(mobileStationId)).thenReturn(Optional.empty());

        // Call the findById method of the service with mobileStationId.
        MobileStation result = mobileStationService.findById(mobileStationId);

        // Verify that the result returned by the service is null.
        assertNull(result);

        // Verify that the repository's findById method was called exactly once with mobileStationId.
        verify(mobileStationRepository, times(1)).findById(mobileStationId);
    }

}