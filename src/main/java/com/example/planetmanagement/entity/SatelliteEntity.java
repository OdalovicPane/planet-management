package com.example.planetmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "satellite")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SatelliteEntity {

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
    @CreationTimestamp
    private LocalDateTime create;
    @UpdateTimestamp
    private LocalDateTime version;
    @ManyToOne
    @JoinColumn(name = "planet_id")
    private PlanetEntity planetEntity;
}
