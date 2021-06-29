package com.example.planetmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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
@Builder
public class PlanetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "surface_area",nullable = false)
    private Long surfaceArea;

    @Column(nullable = false)
    private Long mass;

    @Column(name = "distance_fromSun",nullable = false)
    private Long distanceFromSun;

    @Column(name = "average_surface_temperature")
    private Integer averageSurfaceTemperature;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="planet")
    @JsonIgnoreProperties("planet")
    private List<SatelliteEntity> satellites;

    @CreationTimestamp
    @Column
    private LocalDateTime created;

    @UpdateTimestamp
    @Column
    private LocalDateTime version;

}
