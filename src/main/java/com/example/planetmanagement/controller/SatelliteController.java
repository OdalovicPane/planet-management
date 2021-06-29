package com.example.planetmanagement.controller;

import com.example.planetmanagement.dto.SatelliteDto;
import com.example.planetmanagement.entity.SatelliteEntity;
import com.example.planetmanagement.service.SatelliteService;
import com.example.planetmanagement.utils.Response;
import com.example.planetmanagement.utils.ValidationHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/satellites")
public class SatelliteController {

    private static Logger LOG = Logger.getLogger(String.valueOf(SatelliteController.class));

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SatelliteService satelliteService;

    @GetMapping(value = "/planets/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<List<SatelliteDto>> getSatellitesByPlanetId(@PathVariable("id") Long id) {
        LOG.info("Get Satellites by Planet id: " + id);
        List<SatelliteEntity> satellitesByPlanetId;
        try {
            satellitesByPlanetId = satelliteService.getSatellitesByPlanetId(id);
        } catch (EntityNotFoundException e) {
            return new Response(Collections.singletonList("Satellite not found for id:"+id));
        }
        List<SatelliteDto> satellitesByPlanetIdDto = new ArrayList<>();
        for(SatelliteEntity se : satellitesByPlanetId) {
            satellitesByPlanetIdDto.add(convertToDto(se));
        }
        return new Response<List<SatelliteDto>>(satellitesByPlanetIdDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Response<SatelliteDto> createSatellite(@RequestBody SatelliteDto satelliteDto) {
        LOG.info("Create satellite request: " + satelliteDto);
        List<String> errorMessages = new ArrayList<>();
        if(!ValidationHelper.validate(satelliteDto, errorMessages)) {
            return new Response<>(errorMessages);
        }
        return new Response(convertToDto(satelliteService.createSatellite(convertToEntity(satelliteDto))));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<SatelliteDto> updateSatellite(@PathVariable("id") Long id, @RequestBody SatelliteDto satelliteDto) {
        LOG.info("Update Satellite by id: " + id+ ", request for update: " + satelliteDto);
        List<String> errorMessages = new ArrayList<>();
        if(!ValidationHelper.validate(satelliteDto, errorMessages)) {
            return new Response<>(errorMessages);
        }
        SatelliteEntity satelliteEntity = null;
        try {
            satelliteEntity = satelliteService.updateSatellite(id,convertToEntity(satelliteDto));
        } catch (EntityNotFoundException e) {
            return new Response(Collections.singletonList("Satellite not found for id:"+id));
        }
        return new Response(convertToDto(satelliteEntity));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<SatelliteDto> removeSatellite(@PathVariable("id") Long id) {
        LOG.info("Remove Satellite by id: " + id);
        try {
            satelliteService.deleteSatellite(id);
        } catch (EntityNotFoundException e) {
            return new Response(Collections.singletonList("Satellite not found for id:"+id));
        }
        return new Response("Satellite removed!");
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<SatelliteDto> getSatellite(@PathVariable("id") Long id) {
        LOG.info("Get satellite by id: " + id);
        SatelliteEntity satelliteEntity = null;
        try {
            satelliteEntity = satelliteService.getSatelliteById(id);
        } catch (EntityNotFoundException e) {
            return new Response(Collections.singletonList("Satellite not found for id:"+id));
        }
        return new Response(convertToDto(satelliteEntity));
    }

    private SatelliteDto convertToDto(SatelliteEntity satelliteEntity) {
        return modelMapper.map(satelliteEntity, SatelliteDto.class);
    }

    private SatelliteEntity convertToEntity(SatelliteDto satelliteDto) {
        return modelMapper.map(satelliteDto, SatelliteEntity.class);
    }

}
