package com.example.planetmanagement.service.impl;

import com.example.planetmanagement.entity.SatelliteEntity;
import com.example.planetmanagement.repository.SatelliteRepository;
import com.example.planetmanagement.service.SatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SatelliteServiceImpl implements SatelliteService {

    @Autowired
    private SatelliteRepository satelliteRepository;

    @Override
    public SatelliteEntity createPost(SatelliteEntity satelliteEntity) {
        return satelliteRepository.save(satelliteEntity);
    }

    @Override
    public Optional<SatelliteEntity> getSatelliteById(Long id) {
        return satelliteRepository.findById(id);
    }

}
