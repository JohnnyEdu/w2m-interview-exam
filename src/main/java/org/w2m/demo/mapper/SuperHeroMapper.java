package org.w2m.demo.mapper;

import org.springframework.stereotype.Component;
import org.w2m.demo.dto.SuperHeroDto;
import org.w2m.demo.entity.SuperHero;

@Component
public class SuperHeroMapper {

    public SuperHeroDto toDto(SuperHero superHero) {
        return new SuperHeroDto();
    }
}
