package com.wemakesoftware.navigationsystem.entities.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@Data
public class MobileStationLocation {

    private UUID mobileStationId;

    private float x;

    private float y;

    private float errorRadius;

    private int errorCode;

    private String errorDescription;

}
