package com.example.planetmanagement.utils;

import com.example.planetmanagement.entity.PlanetEntity;
import com.example.planetmanagement.entity.SatelliteEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityBuilder {

    public static SatelliteEntity satellite(long id) {
        return SatelliteEntity.builder().id(id).mass(1L).name("test satellite").naturalSatellite(false).planet(planet(1)).build();
    }

    public static PlanetEntity planet(long id) {
        return PlanetEntity.builder().id(id).averageSurfaceTemperature(1).distanceFromSun(200L).mass(2L).name("test planet").surfaceArea(2L).build();
    }
}
