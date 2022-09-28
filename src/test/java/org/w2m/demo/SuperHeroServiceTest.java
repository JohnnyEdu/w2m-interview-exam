package org.w2m.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.w2m.demo.dto.SuperHeroDto;
import org.w2m.demo.service.SuperHeroService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class SuperHeroServiceTest {
    private final SuperHeroService superHeroService;

    public SuperHeroServiceTest(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @Test
    void contextLoads() {
    }


    @Test
    public void retrieveAllSuperHeroesNotEmpty() {
        List<SuperHeroDto> superHeroes = superHeroService.findAll();
        assertFalse(superHeroes.isEmpty());
    }

    @Test
    public void retrieveOneSuperHeroByIdSuccessfully() {
        Optional<SuperHeroDto> superHero = superHeroService.findById(22);
        assertTrue(superHero.isPresent());
    }

    @Test
    public void retrieveSuperHeroesByTokenSuccessfully() {
        List<SuperHeroDto> superHeroes = superHeroService.findAllByTokenInName("man");
        assertFalse(superHeroes.isEmpty());
    }

    @Test
    public void modifyOneSuperHeroSuccessfully() {
        Optional<SuperHeroDto> superHeroBefore = superHeroService.findById(22);
        assertTrue(superHeroBefore.isPresent());
        superHeroBefore.ifPresent(it -> {
            it.setName("Dr strange");
            it.update();
        });

        Optional<SuperHeroDto> superHeroAfter = superHeroService.findById(22);
        superHeroAfter.ifPresent(it -> {
            assertEquals("Dr strange", it.getName());
        });

    }

    @Test
    public void deleteOneSuperHeroSuccessfully() {
        superHeroService.deleteById(22);

        Optional<SuperHeroDto> superHeroAfter = superHeroService.findById(22);
        assertTrue(superHeroAfter.isEmpty());
    }
}
