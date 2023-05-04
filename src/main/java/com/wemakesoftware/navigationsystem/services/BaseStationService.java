package com.wemakesoftware.navigationsystem.services;

import com.wemakesoftware.navigationsystem.entities.BaseStation;
import com.wemakesoftware.navigationsystem.repositories.BaseStationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BaseStationService {

    private final BaseStationRepository baseStationRepository;

    public BaseStationService(BaseStationRepository baseStationRepository) {
        this.baseStationRepository = baseStationRepository;
    }

    public BaseStation findById(UUID id) {
        return baseStationRepository.findById(id).orElse(null);
    }

}
