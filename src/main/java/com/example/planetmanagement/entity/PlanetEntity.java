package com.example.planetmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "planet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlanetEntity {

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
    @CreationTimestamp
    private LocalDateTime create;
    @UpdateTimestamp
    private LocalDateTime version;
    @OneToMany(mappedBy="planet")
    @Column(name = "satellite_entity_list")
    private List<SatelliteEntity> satelliteEntityEntityList;
}
