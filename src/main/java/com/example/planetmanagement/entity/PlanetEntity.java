package com.example.planetmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "planet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanetEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "surface_area")
    private Long surfaceArea;

    @Column
    private Long mass;

    @Column(name = "distance_fromSun")
    private Long distanceFromSun;

    @Column(name = "average_surface_temperature")
    private Integer averageSurfaceTemperature;

    @OneToMany(mappedBy="planet")
    @Column(name = "satellite_entity_list")
    private List<SatelliteEntity> satelliteEntityEntityList;

}
