package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/*
 * Prednosti strategije nasledjivanja gde se koristi po jedna tabela za svaki entitet:
 * - sve kolone su relevantne za svaku torku u tabeli, lakse su za razumevanje i nema bacanja prostora
 * - mapiranje modela na bazu je skoro 1 na 1 (svaka klasa ima svoju tabelu, svaki atribut ima svoju kolonu)
 * Mane strategije:
 * - da bi se ucitao objekat mora se koristiti vise tabela, sto znaci neizbeznu upotrebu JOINova
 * - roditeljska klasa moze biti usko grlo jer joj se precesto pristupa
 */

@Entity
public class Allergie {

    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String name;


    public Allergie() {

    }

    public Allergie(Allergie a) {
        this.id = a.getId();
        this.name = a.getName();
    }

    public Allergie(String name) {
        this.name = name;
    }

    public Allergie(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allergie alergie = (Allergie) o;
        return Objects.equals(id, alergie.id) && Objects.equals(name, alergie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void copyValues(Allergie p) {
        this.name = p.name;
    }
}
