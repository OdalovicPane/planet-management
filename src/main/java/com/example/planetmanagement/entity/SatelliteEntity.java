package com.example.planetmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "satellite")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SatelliteEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "surface_area")
    private Long surfaceArea;

    @Column
    private Long mass;

    @Column(name = "natural_satellite")
    private Boolean naturalSatellite;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private PlanetEntity planet;

}
