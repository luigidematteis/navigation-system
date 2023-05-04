package com.wemakesoftware.navigationsystem.entities;

import lombok.extern.slf4j.Slf4j;
import lombok.Data;

@Slf4j
@Data
public class Coordinate {
    private float x;
    private float y;

    public Coordinate(float x, float y) {
        this.x = x;
        this.y = y;
    }

}