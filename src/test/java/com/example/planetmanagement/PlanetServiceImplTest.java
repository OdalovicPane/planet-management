package com.example.planetmanagement;

import com.example.planetmanagement.entity.PlanetEntity;
import com.example.planetmanagement.repository.memoryImpl.PlanetRepositoryMemoryImpl;
import com.example.planetmanagement.service.impl.PlanetServiceImpl;
import com.example.planetmanagement.utils.EntityBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceImplTest {

    @InjectMocks
    PlanetServiceImpl planetService = new PlanetServiceImpl(new PlanetRepositoryMemoryImpl());

    private PlanetEntity mockPlanet1;
    private PlanetEntity mockPlanet2;

    @BeforeEach
    public void setUp() {
        mockPlanet1 = EntityBuilder.planet(1);
        mockPlanet2 = EntityBuilder.planet(2);
    }

    @Test
    void shouldGetPlaneById() {
        // given

        // when
        var planet = planetService.getPlanetById(mockPlanet1.getId());

        // then
        assertThat("Expect Planet with id '1'", planet.getId(), is(1L));
    }

    @Test
    void shouldFailGetPlanetByIdWithEntityNotFoundException() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> planetService.getPlanetById(13l));
    }

    @Test
    void shouldUpdatePlanetSuccessfully() {
        // given
        mockPlanet1.setName("test update");

        // when
        var planetEntity = planetService.updatePlanet(mockPlanet1.getId(), mockPlanet1);

        // then
        assertThat("Expect planet to have updated name", planetEntity.getName(), is("test update"));
    }
}
