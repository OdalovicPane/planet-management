package com.example.planetmanagement.repository;

import com.example.planetmanagement.entity.PlanetAndCountSatellite;
import com.example.planetmanagement.entity.PlanetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends CrudRepository<PlanetEntity, Long> {

    Page<PlanetEntity> findAll(Pageable pageable);

    @Query(value = "SELECT planet AS planet, COUNT(satellite.id) AS satelliteCount FROM PlanetEntity planet LEFT JOIN SatelliteEntity satellite ON planet.id = satellite.planet.id GROUP BY planet.id")
    Page<PlanetAndCountSatellite> findAllBySortOfNumberOfSatellites(Pageable pageable);

    @Query(value = "SELECT planet FROM PlanetEntity planet WHERE planet.name LIKE %:name%")
    Page<PlanetEntity> findByName(String name, Pageable pageable);
}
