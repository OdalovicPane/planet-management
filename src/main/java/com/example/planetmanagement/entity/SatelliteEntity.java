package com.example.planetmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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
@Builder
public class SatelliteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "surface_area",nullable = false)
    private Long surfaceArea;

    @Column(nullable = false)
    private Long mass;

    @Column(name = "natural_satellite")
    private Boolean naturalSatellite;

    @ManyToOne
    @JsonIgnoreProperties("satellites")
    @JoinColumn(name = "planet_id")
    private PlanetEntity planet;

    @CreationTimestamp
    @Column
    private LocalDateTime created;

    @UpdateTimestamp
    @Column
    private LocalDateTime version;


}
