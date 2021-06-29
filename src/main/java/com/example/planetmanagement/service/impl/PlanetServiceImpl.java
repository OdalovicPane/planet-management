package com.example.planetmanagement.service.impl;

import com.example.planetmanagement.entity.PlanetEntity;
import com.example.planetmanagement.entity.PlanetAndCountSatellite;
import com.example.planetmanagement.repository.PlanetRepository;
import com.example.planetmanagement.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    private PlanetRepository planetRepository;

    public PlanetServiceImpl(@Autowired @Qualifier("planetRepository") PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    public PlanetEntity createPlanet(PlanetEntity planetEntity) {
        return planetRepository.save(planetEntity);
    }

    @Override
    public PlanetEntity updatePlanet(Long id, PlanetEntity planetEntity) throws EntityNotFoundException {
        Optional<PlanetEntity> optionalPlanetEntity = planetRepository.findById(id);
        if(!optionalPlanetEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        PlanetEntity planetEntityDB = optionalPlanetEntity.get();
        planetEntity.setId(planetEntityDB.getId());
        return planetRepository.save(planetEntity);
    }

    @Override
    public PlanetEntity getPlanetById(Long id) {
        Optional<PlanetEntity> optionalPlanetEntity = planetRepository.findById(id);
        if(!optionalPlanetEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        return optionalPlanetEntity.get();
    }

    @Override
    public Page<PlanetEntity> getAllPlanets(String name, int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<PlanetEntity> pagePlanet;
            if (name == null)
                pagePlanet = planetRepository.findAll(paging);
            else
                pagePlanet = planetRepository.findByName(name, paging);
            return pagePlanet;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Page<PlanetAndCountSatellite> getAllPlanetsBySortOfNumberOfSatellites(int sort, int page, int size) {
        Page<PlanetAndCountSatellite> planetAndCountSatellites = planetRepository.findAllBySortOfNumberOfSatellites(PageRequest.of(page, size, Sort.by("satelliteCount").ascending()));
        List<PlanetAndCountSatellite> test =  planetAndCountSatellites.getContent();
        for(PlanetAndCountSatellite t : test) {
            t.getPlanet();
            t.getSatelliteCount();
        }
        if(sort == 1) {
            return planetRepository.findAllBySortOfNumberOfSatellites(PageRequest.of(page, size, Sort.by("satelliteCount").ascending()));
        } else {
            return planetRepository.findAllBySortOfNumberOfSatellites(PageRequest.of(page, size, Sort.by("satelliteCount").descending()));
        }
    }

    @Override
    public void deletePlanet(Long id) throws EntityNotFoundException {
        Optional<PlanetEntity> optionalPlanetEntity = planetRepository.findById(id);
        if(!optionalPlanetEntity.isPresent()) {
            throw new EntityNotFoundException();
        }
        planetRepository.deleteById(id);
    }
}
