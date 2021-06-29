package com.example.planetmanagement.repository;

import com.example.planetmanagement.entity.SatelliteEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatelliteRepository extends CrudRepository<SatelliteEntity, Long> {

    @Query("SELECT s FROM SatelliteEntity s where s.planet.id = :planet_id")
    List<SatelliteEntity> findByPlanet(@Param("planet_id")  Long planet_id);
}
