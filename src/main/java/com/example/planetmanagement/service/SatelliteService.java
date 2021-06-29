package com.example.planetmanagement.service;

import com.example.planetmanagement.entity.SatelliteEntity;

import java.util.List;

public interface SatelliteService {

    List<SatelliteEntity> getSatellitesByPlanetId(Long id);

    SatelliteEntity createSatellite(SatelliteEntity satelliteEntity);

    SatelliteEntity updateSatellite(Long id, SatelliteEntity satelliteEntity);

    void deleteSatellite(Long id);

    SatelliteEntity getSatelliteById(Long id);

}
