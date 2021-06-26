package com.example.planetmanagement.controller;

import com.example.planetmanagement.dto.PlanetDto;
import com.example.planetmanagement.entity.PlanetEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    @Autowired
    private ModelMapper modelMapper;

    private PlanetDto convertToDto(PlanetEntity planetEntity) {
        return modelMapper.map(planetEntity, PlanetDto.class);
    }

    private PlanetEntity convertToEntity(PlanetDto planetDto) {
        return modelMapper.map(planetDto, PlanetEntity.class);
    }

}
