package com.example.planetmanagement;

import com.example.planetmanagement.entity.PlanetEntity;
import com.example.planetmanagement.entity.SatelliteEntity;
import com.example.planetmanagement.repository.memoryImpl.PlanetRepositoryMemoryImpl;
import com.example.planetmanagement.repository.memoryImpl.SatelliteRepositoryMemoryImpl;
import com.example.planetmanagement.service.impl.PlanetServiceImpl;
import com.example.planetmanagement.service.impl.SatelliteServiceImpl;
import com.example.planetmanagement.utils.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import javax.persistence.EntityNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SatelliteServiceImplTest {

    @InjectMocks
    SatelliteServiceImpl satelliteService = new SatelliteServiceImpl(new SatelliteRepositoryMemoryImpl());

    private SatelliteEntity mockSatellite1;
    private SatelliteEntity mockSatellite2;

    @BeforeEach
    public void setUp() {
        mockSatellite1 = EntityBuilder.satellite(1);
        mockSatellite2 = EntityBuilder.satellite(2);
    }

    @Test
    void shouldGetSatelliteById() {
        // given

        // when
        var planet = satelliteService.getSatelliteById(mockSatellite1.getId());

        // then
        assertThat("Expect Satellite with id '1'", planet.getId(), is(1L));
    }

    @Test
    void shouldFailGetSatelliteByIdWithEntityNotFoundException() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> satelliteService.getSatelliteById(13l));
    }

    @Test
    void shouldUpdateSatelliteSuccessfully() {
        // given
        mockSatellite1.setName("test update");

        // when
        var satelliteEntity = satelliteService.updateSatellite(mockSatellite1.getId(), mockSatellite1);

        // then
        assertThat("Expect satellite to have updated name", satelliteEntity.getName(), is("test update"));
    }
}
