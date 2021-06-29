package com.example.planetmanagement.repository.memoryImpl;

import com.example.planetmanagement.entity.PlanetEntity;
import com.example.planetmanagement.entity.PlanetAndCountSatellite;
import com.example.planetmanagement.repository.PlanetRepository;
import com.example.planetmanagement.utils.EntityBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PlanetRepositoryMemoryImpl implements PlanetRepository {

    private List<PlanetEntity> planetEntities = new ArrayList<>(Arrays.asList(EntityBuilder.planet(1), EntityBuilder.planet(2)));

    @Override
    public Page<PlanetEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<PlanetAndCountSatellite> findAllBySortOfNumberOfSatellites(Pageable pageable) {
        return null;
    }

    @Override
    public Page<PlanetEntity> findByName(String name, Pageable pageable) {
        return null;
    }

    @Override
    public PlanetEntity save(PlanetEntity planetEntity) {
        planetEntity.setId(new Random().nextLong());
        planetEntities.add(planetEntity);
        return planetEntity;
    }

    @Override
    public <S extends PlanetEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<PlanetEntity> findById(Long id) {
        if (id == null) {
            return null;
        }
        PlanetEntity planetEntity = planetEntities.stream()
                .filter(planet -> planet.getId().equals(id))
                .findFirst().orElse(null);
        return Optional.ofNullable(planetEntity);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<PlanetEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<PlanetEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        Optional<PlanetEntity> planetEntityOptional = findById(id);
        if (planetEntityOptional.isPresent()) {
            planetEntities.remove(planetEntityOptional.get());
        }
    }

    @Override
    public void delete(PlanetEntity planetEntity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends PlanetEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
