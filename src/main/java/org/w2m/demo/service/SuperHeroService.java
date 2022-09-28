package org.w2m.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.w2m.demo.annotation.ExecutionTimeLog;
import org.w2m.demo.dto.SuperHeroDto;
import org.w2m.demo.entity.SuperHero;
import org.w2m.demo.mapper.SuperHeroMapper;
import org.w2m.demo.repository.SuperHeroRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuperHeroService {
    private final SuperHeroRepository superHeroRepository;

    public SuperHeroService(SuperHeroRepository superHeroRepository) {
        this.superHeroRepository = superHeroRepository;
    }

    @Cacheable(value = "allSuperHeroCache")
    @ExecutionTimeLog
    public List<SuperHeroDto> findAll() {
        var superHeroes = superHeroRepository.findAll();
        return convertToDto(superHeroes);
    }

    @Cacheable(value = "superHeroCache")
    @ExecutionTimeLog
    public Optional<SuperHeroDto> findById(long id) {
        var superHero = superHeroRepository.findById(id);
        return superHero.map(SuperHeroMapper::toDto);
    }

    @ExecutionTimeLog
    public List<SuperHeroDto> findAllByTokenInName(String token) {
        var superHeroes = superHeroRepository.findAllByTokenInName(token);
        return convertToDto(superHeroes);
    }

    @CacheEvict(cacheNames = {"allSuperHeroCache", "superHeroCache"}, allEntries = true)
    @ExecutionTimeLog
    public void deleteById(long id) {
        superHeroRepository.deleteById(id);
    }

    private List<SuperHeroDto> convertToDto(List<SuperHero> superHeroes) {
        return superHeroes.stream()
                .map(SuperHeroMapper::toDto)
                .collect(Collectors.toList());
    }
}
