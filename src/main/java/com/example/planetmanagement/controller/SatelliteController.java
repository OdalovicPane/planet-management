package com.example.planetmanagement.controller;

import com.example.planetmanagement.dto.SatelliteDto;
import com.example.planetmanagement.entity.SatelliteEntity;
import com.example.planetmanagement.service.SatelliteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/satellites")
public class SatelliteController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SatelliteService satelliteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SatelliteDto createPost(@RequestBody SatelliteDto satelliteDto) {
        return convertToDto(satelliteService.createPost(convertToEntity(satelliteDto)));
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public SatelliteDto getPost(@PathVariable("id") Long id) {
        return convertToDto(satelliteService.getSatelliteById(id).get());
    }

    private SatelliteDto convertToDto(SatelliteEntity satelliteEntity) {
        return modelMapper.map(satelliteEntity, SatelliteDto.class);
    }

    private SatelliteEntity convertToEntity(SatelliteDto satelliteDto) {
        return modelMapper.map(satelliteDto, SatelliteEntity.class);
    }

}
