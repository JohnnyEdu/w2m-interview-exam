package org.w2m.demo.service;

import org.springframework.stereotype.Service;
import org.w2m.demo.annotation.ExecutionTimeLog;
import org.w2m.demo.dto.SuperPowerDto;
import org.w2m.demo.entity.SuperPower;
import org.w2m.demo.exception.SuperPowerNotFoundException;
import org.w2m.demo.mapper.SuperPowerMapper;
import org.w2m.demo.repository.SuperPowerRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SuperPowerService {
    private final SuperPowerRepository superPowerRepository;

    public SuperPowerService(SuperPowerRepository superHeroRepository) {
        this.superPowerRepository = superHeroRepository;
    }

    @ExecutionTimeLog
    public Set<SuperPowerDto> findAllByNames(Set<String> names) throws SuperPowerNotFoundException {
        var superPowers = superPowerRepository.findAllByNameIn(names);
        if (superPowers.size() != names.size()) {
            throw new SuperPowerNotFoundException();
        }
        return convertToDto(superPowers);
    }

    private Set<SuperPowerDto> convertToDto(List<SuperPower> superPowers) {
        return superPowers.stream()
                .map(SuperPowerMapper::toDto)
                .collect(Collectors.toSet());
    }
}
