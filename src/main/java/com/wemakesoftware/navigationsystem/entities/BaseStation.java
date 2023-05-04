package com.wemakesoftware.navigationsystem.entities;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

@Slf4j
@Entity
@Table(name = "base_station")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BaseStation implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    private String name;

    private float x;

    private float y;

    private float detectionRadiusInMeters;

}
