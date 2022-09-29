package org.w2m.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.w2m.demo.entity.SuperPower;
import org.w2m.demo.exception.SuperPowerNotFoundException;
import org.w2m.demo.repository.SuperPowerRepository;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class SuperPowerServiceTest {
    SuperPowerService superPowerService;

    SuperPowerRepository superPowerRepository = Mockito.mock(SuperPowerRepository.class);

    @BeforeEach
    void setup() {
        superPowerService = new SuperPowerService(superPowerRepository);
    }


    @Test
    public void retrieveAllSuperPowersByNameNotEmpty() throws Exception {
        var resultMock = new ArrayList<SuperPower>();
        resultMock.add(new SuperPower(1, "STRENGTH"));
        resultMock.add(new SuperPower(2, "FLY"));

        var superPowers = Set.of("STRENGTH", "FLY");

        when(superPowerRepository.findAllByNameIn(superPowers)).thenReturn(resultMock);

        assertDoesNotThrow(() -> superPowerService.findAllByNames(superPowers));

        var result = superPowerService.findAllByNames(superPowers);
        assertEquals(result.size(), resultMock.size());
    }

    @Test
    public void retrieveAllByNameSuperPowerNotFoundException() throws Exception {
        var superPowers = Set.of("STRENGTH", "FLY");

        when(superPowerRepository.findAllByNameIn(superPowers)).thenReturn(new ArrayList<>());

        assertThrows(SuperPowerNotFoundException.class, () -> superPowerService.findAllByNames(superPowers));
    }
}
