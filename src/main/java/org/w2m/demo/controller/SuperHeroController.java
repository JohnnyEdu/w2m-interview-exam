package org.w2m.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w2m.demo.dto.SaveSuperHeroRequestDto;
import org.w2m.demo.dto.SuperHeroDto;
import org.w2m.demo.dto.UpdateSuperHeroRequestDto;
import org.w2m.demo.exception.DuplicatedSuperHeroException;
import org.w2m.demo.exception.SuperHeroNotFoundException;
import org.w2m.demo.exception.SuperPowerNotFoundException;
import org.w2m.demo.service.SuperHeroService;

import javax.validation.Valid;
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
        return  superHeroService.findAll();
    }

    @GetMapping("/super-hero/{id}")
    SuperHeroDto getSuperHeroes(@PathVariable Long id) throws SuperHeroNotFoundException {
        return superHeroService.findById(id);
    }

    @GetMapping("/super-hero")
    List<SuperHeroDto> getSuperHero(@Valid @RequestParam("name_token") String nameToken) {
        return superHeroService.findAllByTokenInName(nameToken);
    }

    @PostMapping("/super-hero")
    SuperHeroDto saveSuperHero(@Valid @RequestBody SaveSuperHeroRequestDto saveSuperHeroRequestDto)
            throws DuplicatedSuperHeroException, SuperPowerNotFoundException {
        return superHeroService.save(saveSuperHeroRequestDto);
    }

    @PutMapping("/super-hero")
    SuperHeroDto updateSuperHero(@Valid @RequestBody UpdateSuperHeroRequestDto updateSuperHeroRequestDto)
            throws SuperHeroNotFoundException, SuperPowerNotFoundException {
        return superHeroService.update(updateSuperHeroRequestDto);
    }

    @DeleteMapping("/super-hero/{id}")
    void deleteSuperHero(@PathVariable Long id) {
        superHeroService.deleteById(id);
    }
}
