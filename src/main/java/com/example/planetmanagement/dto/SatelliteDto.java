package com.example.planetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SatelliteDto {

    private String name;

    private Long surfaceArea;

    private Long mass;

    private Boolean naturalSatellite;

    private PlanetDto planetDto;

}
