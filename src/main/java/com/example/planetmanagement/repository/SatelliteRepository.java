package com.example.planetmanagement.repository;

import com.example.planetmanagement.entity.SatelliteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelliteRepository extends CrudRepository<SatelliteEntity, Long> {


}
