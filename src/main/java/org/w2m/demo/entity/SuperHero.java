package org.w2m.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "super_hero")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperHero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "super_hero_id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "super_hero_power",
            joinColumns = @JoinColumn(name = "super_hero_id"),
            inverseJoinColumns = @JoinColumn(name = "super_power_id")
    )
    @Column(name = "super_powers", nullable = false)
    private Set<SuperPower> superPowers = new HashSet<>();
}
