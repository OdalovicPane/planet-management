package com.example.planetmanagement.service.impl;

import com.example.planetmanagement.entity.SatelliteEntity;
import com.example.planetmanagement.repository.SatelliteRepository;
import com.example.planetmanagement.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class SatelliteServiceImpl implements SatelliteService {

    private SatelliteRepository satelliteRepository;

    public SatelliteServiceImpl(@Autowired @Qualifier("satelliteRepository") SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    @Override
    public List<SatelliteEntity> getSatellitesByPlanetId(Long id) {
        return satelliteRepository.findByPlanet(id);
    }

    @Override
    public SatelliteEntity createSatellite(SatelliteEntity satelliteEntity) {
        return satelliteRepository.save(satelliteEntity);
    }

    @Override
    public SatelliteEntity updateSatellite(Long id, SatelliteEntity satelliteEntity) throws EntityNotFoundException {
        Optional<SatelliteEntity> optionalSatelliteEntity = satelliteRepository.findById(id);
        if(!optionalSatelliteEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        SatelliteEntity satelliteEntityDB = optionalSatelliteEntity.get();
        satelliteEntity.setId(satelliteEntityDB.getId());
        return satelliteRepository.save(satelliteEntity);
    }

    @Override
    public void deleteSatellite(Long id) throws EntityNotFoundException {
        Optional<SatelliteEntity> optionalSatelliteEntity = satelliteRepository.findById(id);
        if(!optionalSatelliteEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        satelliteRepository.deleteById(id);
    }

    @Override
    public SatelliteEntity getSatelliteById(Long id) throws EntityNotFoundException {
        Optional<SatelliteEntity> optionalSatelliteEntity = satelliteRepository.findById(id);
        if(!optionalSatelliteEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        return optionalSatelliteEntity.get();
    }

}
