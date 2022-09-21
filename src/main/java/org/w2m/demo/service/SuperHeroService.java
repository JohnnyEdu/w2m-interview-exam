package org.w2m.demo.service;

import org.w2m.demo.dto.SuperHeroDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SuperHeroService {

    public List<SuperHeroDto> findAll() {
        return new ArrayList<SuperHeroDto>();
    }

    public Optional<SuperHeroDto> findById(int i) {
        return Optional.of(new SuperHeroDto());
    }

    public List<SuperHeroDto> findAllByTokenInName(String token) {
        return new ArrayList<SuperHeroDto>();
    }

    public void deleteById(int i) {
    }
}
