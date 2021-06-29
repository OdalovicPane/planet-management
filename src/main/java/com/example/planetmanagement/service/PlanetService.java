package com.example.planetmanagement.service;

import com.example.planetmanagement.entity.PlanetEntity;
import com.example.planetmanagement.entity.PlanetAndCountSatellite;
import org.springframework.data.domain.Page;

public interface PlanetService {

    PlanetEntity createPlanet(PlanetEntity planetEntity);

    PlanetEntity updatePlanet(Long id, PlanetEntity planetEntity);

    PlanetEntity getPlanetById(Long id);

    Page<PlanetEntity> getAllPlanets(String name, int page, int size);

    Page<PlanetAndCountSatellite> getAllPlanetsBySortOfNumberOfSatellites(int sort, int page, int size);

    void deletePlanet(Long id);
}
