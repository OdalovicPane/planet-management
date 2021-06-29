package com.example.planetmanagement.controller;

import com.example.planetmanagement.dto.PlanetDto;
import com.example.planetmanagement.entity.PlanetEntity;
import com.example.planetmanagement.entity.PlanetAndCountSatellite;
import com.example.planetmanagement.service.PlanetService;
import com.example.planetmanagement.utils.PageResponse;
import com.example.planetmanagement.utils.Response;
import com.example.planetmanagement.utils.ValidationHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private static Logger LOG = Logger.getLogger(String.valueOf(PlanetController.class));

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlanetService planetService;

    @GetMapping
    public PageResponse getAllPlanets(
            @RequestParam(required = false,name = "name") String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size) {
        LOG.info("get All Planets: name: "+ name+ ", page: "+page+", size:"+ size);
        return new PageResponse(planetService.getAllPlanets(name, page, size), (entity) -> convertToDto((PlanetEntity) entity));
    }

    @GetMapping("/sort-by-number-of-satellites")
    public Response<Map<String, Object>> getPlanetsSortByNumberOfSatellites(
            @RequestParam(defaultValue = "0") Integer sort,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size) {
        LOG.info("get All Planets sort-by-number-of-satellites: sort: "+ sort+ ", page: "+page+", size:"+ size);
        return new PageResponse(planetService.getAllPlanetsBySortOfNumberOfSatellites(sort, page, size), (planetAndCountSatellite) -> convertToDto(((PlanetAndCountSatellite) planetAndCountSatellite).getPlanet()));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<PlanetDto> getPlanet(@PathVariable("id") Long id) {
        LOG.info("Get planet by id: " + id);
        PlanetEntity planetEntity = null;
        try {
            planetEntity = planetService.getPlanetById(id);
        } catch (EntityNotFoundException e) {
            return new Response(Collections.singletonList("Planet not found for id:"+id));
        }
        return new Response(convertToDto(planetEntity));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Response<PlanetDto> createPlanet(@RequestBody PlanetDto planetDto) {
        LOG.info("Create planet request: " + planetDto);
        List<String> errorMessages = new ArrayList<>();
        if(!ValidationHelper.validate(planetDto, errorMessages)) {
            return new Response<>(errorMessages);
        }
        return new Response(convertToDto(planetService.createPlanet(convertToEntity(planetDto))));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<PlanetDto> removePlanet(@PathVariable("id") Long id) {
        LOG.info("Remove planet by id: " + id);
        try {
            planetService.deletePlanet(id);
        } catch (EntityNotFoundException e) {
            return new Response(Collections.singletonList("Planet not found for id:"+id));
        }
        return new Response("Planet removed!");
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response<PlanetDto> updateSatellite(@PathVariable("id") Long id, @RequestBody PlanetDto planetDto) {
        LOG.info("Update planet by id: " + id+ ", request for update: " + planetDto);
        List<String> errorMessages = new ArrayList<>();
        if(!ValidationHelper.validate(planetDto, errorMessages)) {
            return new Response<>(errorMessages);
        }
        PlanetEntity planetEntity = null;
        try {
            planetEntity = planetService.updatePlanet(id,convertToEntity(planetDto));
        } catch (EntityNotFoundException e) {
            return new Response(Collections.singletonList("Planet not found for id:"+id));
        }
        return new Response(convertToDto(planetEntity));
    }

    private PlanetDto convertToDto(PlanetEntity planetEntity) {
        return modelMapper.map(planetEntity, PlanetDto.class);
    }

    private PlanetEntity convertToEntity(PlanetDto planetDto) {
        return modelMapper.map(planetDto, PlanetEntity.class);
    }

}
