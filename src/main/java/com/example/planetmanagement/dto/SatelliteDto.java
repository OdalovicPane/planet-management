package com.example.planetmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SatelliteDto {

    private Long id;

    @NotNull(message = "Name cannot be null!")
    private String name;

    @NotNull(message = "Surface area cannot be null!")
    private Long surfaceArea;

    @NotNull(message = "Mass cannot be null!")
    private Long mass;

    private Boolean naturalSatellite;

    @JsonIgnoreProperties("satellites")
    private PlanetDto planet;

}
