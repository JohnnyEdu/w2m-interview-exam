package org.w2m.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.w2m.demo.annotation.ExecutionTimeLog;
import org.w2m.demo.dto.SaveSuperHeroRequestDto;
import org.w2m.demo.dto.SuperHeroDto;
import org.w2m.demo.dto.SuperPowerDto;
import org.w2m.demo.dto.UpdateSuperHeroRequestDto;
import org.w2m.demo.entity.SuperHero;
import org.w2m.demo.entity.SuperPower;
import org.w2m.demo.exception.DuplicatedSuperHeroException;
import org.w2m.demo.exception.SuperHeroNotFoundException;
import org.w2m.demo.exception.SuperPowerNotFoundException;
import org.w2m.demo.mapper.SuperHeroMapper;
import org.w2m.demo.mapper.SuperPowerMapper;
import org.w2m.demo.repository.SuperHeroRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SuperHeroService {
    private final SuperHeroRepository superHeroRepository;
    private final SuperPowerService superPowerService;

    public SuperHeroService(SuperHeroRepository superHeroRepository, SuperPowerService superPowerService) {
        this.superHeroRepository = superHeroRepository;
        this.superPowerService = superPowerService;
    }

    @Cacheable(value = "allSuperHeroCache")
    @ExecutionTimeLog
    public List<SuperHeroDto> findAll() {
        var superHeroes = superHeroRepository.findAll();
        return convertToDto(superHeroes);
    }

    @Cacheable(value = "superHeroCache")
    @ExecutionTimeLog
    public SuperHeroDto findById(long id) throws SuperHeroNotFoundException {
        var superHero = superHeroRepository.findById(id);
        var result = superHero.map(SuperHeroMapper::toDto);
        return result.orElseThrow(SuperHeroNotFoundException::new);
    }

    @ExecutionTimeLog
    public List<SuperHeroDto> findAllByTokenInName(String token) {
        var superHeroes = superHeroRepository.findAllByTokenInName(token);
        return convertToDto(superHeroes);
    }

    @CacheEvict(cacheNames = {"allSuperHeroCache", "superHeroCache"}, allEntries = true)
    @ExecutionTimeLog
    public SuperHeroDto save(SaveSuperHeroRequestDto saveSuperHeroRequestDto) throws DuplicatedSuperHeroException,
            SuperPowerNotFoundException {
        checkExists(saveSuperHeroRequestDto.getName());
        var superHero = buildSuperHero(
                saveSuperHeroRequestDto.getName(),
                saveSuperHeroRequestDto.getSuperPowers()
        );
        var saved = superHeroRepository.save(superHero);
        return SuperHeroMapper.toDto(saved);
    }

    @CacheEvict(cacheNames = {"allSuperHeroCache", "superHeroCache"}, allEntries = true)
    @ExecutionTimeLog
    public SuperHeroDto update(UpdateSuperHeroRequestDto updateSuperHeroRequestDto)
            throws SuperHeroNotFoundException, SuperPowerNotFoundException {
        checkExists(updateSuperHeroRequestDto.getId());
        var superHero = buildSuperHero(
                updateSuperHeroRequestDto.getId(),
                updateSuperHeroRequestDto.getName(),
                updateSuperHeroRequestDto.getSuperPowers());

        var updated = superHeroRepository.save(superHero);
        return SuperHeroMapper.toDto(updated);
    }

    @CacheEvict(cacheNames = {"allSuperHeroCache", "superHeroCache"}, allEntries = true)
    @ExecutionTimeLog
    public void deleteById(long id) {
        superHeroRepository.deleteById(id);
    }

    private void checkExists(String name) throws DuplicatedSuperHeroException {
        var exists = superHeroRepository.existsByName(name);
        if (exists) {
            throw new DuplicatedSuperHeroException();
        }
    }

    private void checkExists(long id) throws SuperHeroNotFoundException {
        var exists = superHeroRepository.existsById(id);
        if (!exists) {
            throw new SuperHeroNotFoundException();
        }
    }

    private SuperHero buildSuperHero(String name, Set<String> superPowersNames) throws SuperPowerNotFoundException {
        return buildSuperHero(0, name, superPowersNames);
    }

    private SuperHero buildSuperHero(long id, String name, Set<String> superPowersNames)
            throws SuperPowerNotFoundException {
        var superPowersDtos = getSuperPowersForNames(superPowersNames);
        var superPowers = getEntitiesFromDtos(superPowersDtos);
        return new SuperHero(id
                , name
                , superPowers);
    }

    private Set<SuperPower> getEntitiesFromDtos(Set<SuperPowerDto> superPowersDtos) {
        return superPowersDtos.stream()
                .map(SuperPowerMapper::fromDto)
                .collect(Collectors.toSet());
    }

    private Set<SuperPowerDto> getSuperPowersForNames(Set<String> superPowerNames) throws SuperPowerNotFoundException {
        return superPowerService.findAllByNames(superPowerNames);
    }

    private List<SuperHeroDto> convertToDto(List<SuperHero> superHeroes) {
        return superHeroes.stream()
                .map(SuperHeroMapper::toDto)
                .collect(Collectors.toList());
    }
}
