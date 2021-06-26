package com.example.planetmanagement.service;

import com.example.planetmanagement.entity.SatelliteEntity;

import java.util.Optional;

public interface SatelliteService {

    SatelliteEntity createPost(SatelliteEntity satelliteEntity);

    Optional<SatelliteEntity> getSatelliteById(Long id);

}
