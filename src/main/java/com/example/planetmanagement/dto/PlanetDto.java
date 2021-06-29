package com.example.planetmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlanetDto {

    private Long id;

    @NotNull(message = "Name cannot be null!")
    private String name;

    @NotNull(message = "Surface area cannot be null!")
    private Long surfaceArea;

    @NotNull(message = "Mass cannot be null!")
    private Long mass;

    @NotNull(message = "Distance from sun cannot be null!")
    private Long distanceFromSun;

    private Integer averageSurfaceTemperature;

    @JsonIgnoreProperties("planet")
    private List<SatelliteDto> satellites;
}
