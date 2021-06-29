package com.example.planetmanagement.repository.memoryImpl;

import com.example.planetmanagement.entity.SatelliteEntity;
import com.example.planetmanagement.repository.SatelliteRepository;
import com.example.planetmanagement.utils.EntityBuilder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SatelliteRepositoryMemoryImpl implements SatelliteRepository {

    private List<SatelliteEntity> satelliteEntities = new ArrayList<>(Arrays.asList(EntityBuilder.satellite(1), EntityBuilder.satellite(2)));

    @Override
    public List<SatelliteEntity> findByPlanet(Long id) {
        return satelliteEntities.stream()
                .filter(satellite -> satellite.getPlanet().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public SatelliteEntity save(SatelliteEntity satelliteEntity) {
        satelliteEntity.setId(new Random().nextLong());
        satelliteEntities.add(satelliteEntity);
        return satelliteEntity;
    }

    @Override
    public <S extends SatelliteEntity> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<SatelliteEntity> findById(Long id) {
        if(id == null) {
            return null;
        }
        SatelliteEntity satelliteEntity = satelliteEntities.stream()
                .filter(satellite -> satellite.getId().equals(id))
                .findFirst().orElse(null);
        return Optional.ofNullable(satelliteEntity);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<SatelliteEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<SatelliteEntity> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        Optional<SatelliteEntity> satelliteEntityOptional = findById(id);
        if (satelliteEntityOptional.isPresent()) {
            satelliteEntities.remove(satelliteEntityOptional.get());
        }
    }

    @Override
    public void delete(SatelliteEntity satelliteEntity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends SatelliteEntity> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
