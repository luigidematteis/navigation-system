package com.wemakesoftware.navigationsystem.services;

import com.wemakesoftware.navigationsystem.entities.MobileStation;
import com.wemakesoftware.navigationsystem.repositories.MobileStationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class MobileStationService {

    private final MobileStationRepository mobileStationRepository;

    private final BaseStationService baseStationService;

    public MobileStationService(MobileStationRepository mobileStationRepository, BaseStationService baseStationService) {
        this.mobileStationRepository = mobileStationRepository;
        this.baseStationService = baseStationService;
    }

    public MobileStation findById(UUID id) {
        return mobileStationRepository.findById(id).orElse(null);
    }

}
