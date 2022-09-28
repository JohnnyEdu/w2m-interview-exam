package org.w2m.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w2m.demo.entity.SuperHero;
import org.w2m.demo.util.QueryConstants;

import java.util.List;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
    @Query(QueryConstants.FIND_BY_TOKEN)
    List<SuperHero> findAllByTokenInName(String token);
}
