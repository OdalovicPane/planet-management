package com.example.planetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanetDto {

    private String name;

    private Long surfaceArea;

    private Long mass;

    private Long distanceFromSun;

    private Integer averageSurfaceTemperature;

    private LocalDateTime create;

    private LocalDateTime version;

    private List<SatelliteDto> satellites;
}
