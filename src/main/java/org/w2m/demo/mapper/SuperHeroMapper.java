package org.w2m.demo.mapper;

import org.w2m.demo.dto.SuperHeroDto;
import org.w2m.demo.dto.SuperPowerDto;
import org.w2m.demo.entity.SuperHero;
import org.w2m.demo.entity.SuperPower;

import java.util.Set;
import java.util.stream.Collectors;

public class SuperHeroMapper {

    public static SuperHeroDto toDto(SuperHero superHero) {
        var superPowers = mapSuperPowers(superHero);
        return SuperHeroDto.builder()
                .id(superHero.getId())
                .name(superHero.getName())
                .superPowers(superPowers)
                .build();
    }

    public static SuperHero fromDto(SuperHeroDto superHeroDto) {
        var superPowers = mapSuperPowers(superHeroDto);
        return new SuperHero(
                superHeroDto.getId(),
                superHeroDto.getName(),
                superPowers
        );
    }

    private static Set<SuperPower> mapSuperPowers(SuperHeroDto superHeroDto) {
        return superHeroDto.getSuperPowers().stream()
                .map(SuperPowerMapper::fromDto)
                .collect(Collectors.toSet());
    }

    private static Set<SuperPowerDto> mapSuperPowers(SuperHero superHero) {
        return superHero.getSuperPowers().stream()
                .map(SuperPowerMapper::toDto)
                .collect(Collectors.toSet());
    }
}
