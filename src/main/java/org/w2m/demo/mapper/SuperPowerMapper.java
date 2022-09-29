package org.w2m.demo.mapper;

import org.w2m.demo.dto.SuperPowerDto;
import org.w2m.demo.entity.SuperPower;

public class SuperPowerMapper {

    public static SuperPowerDto toDto(SuperPower superPower) {
        return SuperPowerDto.builder()
                .id(superPower.getId())
                .name(superPower.getName())
                .build();
    }

    public static SuperPower fromDto(SuperPowerDto superPowerDto) {
        return new SuperPower(
                superPowerDto.getId(),
                superPowerDto.getName()
        );
    }
}
