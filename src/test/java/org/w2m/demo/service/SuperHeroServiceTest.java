package org.w2m.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.w2m.demo.dto.SuperHeroDto;
import org.w2m.demo.entity.SuperHero;
import org.w2m.demo.exception.SuperHeroNotFoundException;
import org.w2m.demo.mapper.SuperHeroMapper;
import org.w2m.demo.repository.SuperHeroRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class SuperHeroServiceTest {
    SuperHeroService superHeroService;


    SuperPowerService superPowerService = Mockito.mock(SuperPowerService.class);

    SuperHeroRepository superHeroRepository = Mockito.mock(SuperHeroRepository.class);

    @BeforeEach
    void setup() {
        superHeroService = new SuperHeroService(superHeroRepository, superPowerService);
    }


    @Test
    public void retrieveAllSuperHeroesNotEmpty() {
        var resultMock = new ArrayList<SuperHero>();
        resultMock.add(new SuperHero());

        Mockito.when(superHeroRepository.findAll()).thenReturn(resultMock);

        assertDoesNotThrow(superHeroService::findAll);

        var result = superHeroService.findAll();
        assertFalse(result.isEmpty());
    }

    @Test
    public void retrieveOneSuperHeroByIdSuccessfully() throws Exception {
        Mockito.when(superHeroRepository.findById(22L))
                .thenReturn(Optional.of(new SuperHero()));

        assertDoesNotThrow(() -> superHeroService.findById(22));
        assertNotNull(superHeroService.findById(22));
    }

    @Test
    public void retrieveSuperHeroesByTokenSuccessfully() {
        var superHeroes = new ArrayList<SuperHero>();
        superHeroes.add(new SuperHero());

        Mockito.when(superHeroRepository.findAllByTokenInName("man"))
                .thenReturn(superHeroes);

        assertDoesNotThrow(() -> superHeroService.findAllByTokenInName("man"));

        assertNotNull(superHeroService.findAllByTokenInName("man"));
    }

    @Test
    public void modifyOneSuperHeroSuccessfully() throws Exception {

        Mockito.when(superHeroRepository.findById(22L))
                .thenReturn(Optional.of(new SuperHero()));

        assertDoesNotThrow(() -> superHeroService.findById(22L));

        SuperHeroDto superHeroBefore = superHeroService.findById(22);

        superHeroBefore.setName("Dr strange");
        superHeroBefore.update();

        Mockito.when(superHeroRepository.findById(22L))
                .thenReturn(Optional.of(SuperHeroMapper.fromDto(superHeroBefore)));

        SuperHeroDto superHeroAfter = superHeroService.findById(22);
        assertEquals("Dr strange", superHeroAfter.getName());
    }

    @Test
    public void deleteOneSuperHeroSuccessfully() throws Exception {
        Mockito.doNothing().when(superHeroRepository).deleteById(22L);

        assertDoesNotThrow(() -> superHeroService.deleteById(22));
    }

    @Test
    public void retrieveSuperHeroByIdNotFoundException() throws Exception {

        when(superHeroRepository.findById(22L)).thenReturn(Optional.empty());

        assertThrows(SuperHeroNotFoundException.class, () -> superHeroService.findById(22));
    }
}
