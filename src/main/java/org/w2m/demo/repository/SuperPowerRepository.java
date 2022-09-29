package org.w2m.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.w2m.demo.entity.SuperPower;

import java.util.List;
import java.util.Set;

public interface SuperPowerRepository extends JpaRepository<SuperPower, Long> {

    List<SuperPower> findAllByNameIn(Set<String> name);
}
