package org.w2m.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w2m.demo.dto.SuperHeroDto;
import org.w2m.demo.service.SuperHeroService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SuperHeroController {
    private final SuperHeroService superHeroService;

    public SuperHeroController(SuperHeroService superHeroService) {
        this.superHeroService = superHeroService;
    }

    @GetMapping("/super-heroes")
    List<SuperHeroDto> superHeroes() {
        return superHeroService.findAll();
    }

    @GetMapping("/super-hero/{id}")
    SuperHeroDto getSuperHeroes(@PathVariable Long id) {
        return superHeroService.findById(id).get();//TODO ver tratamiento
    }

    @GetMapping("/super-hero")
    List<SuperHeroDto> getSuperHero(@RequestParam("name_token") String nameToken) {
        return superHeroService.findAllByTokenInName(nameToken);
    }

    @DeleteMapping("/super-hero/{id}")
    void deleteSuperHero(@PathVariable Long id) {
        superHeroService.deleteById(id);
    }
}
