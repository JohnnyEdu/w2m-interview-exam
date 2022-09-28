package org.w2m.demo.mapper;

import org.w2m.demo.dto.SuperHeroDto;
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

    private static Set<String> mapSuperPowers(SuperHero superHero) {
        return superHero.getSuperPowers().stream()
                .map(SuperPower::getName)
                .collect(Collectors.toSet());
    }
}
