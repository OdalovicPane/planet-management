package com.example.planetmanagement.repository;

import com.example.planetmanagement.entity.PlanetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends CrudRepository<PlanetEntity, Long> {
}
