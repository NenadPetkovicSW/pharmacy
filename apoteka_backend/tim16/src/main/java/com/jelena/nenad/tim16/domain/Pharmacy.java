package com.jelena.nenad.tim16.domain;

import com.sun.xml.bind.v2.runtime.reflect.Lister;

import javax.persistence.*;
import java.util.ArrayList;

/*
 * Prednosti strategije nasledjivanja gde se koristi po jedna tabela za svaki entitet:
 * - sve kolone su relevantne za svaku torku u tabeli, lakse su za razumevanje i nema bacanja prostora
 * - mapiranje modela na bazu je skoro 1 na 1 (svaka klasa ima svoju tabelu, svaki atribut ima svoju kolonu)
 * Mane strategije:
 * - da bi se ucitao objekat mora se koristiti vise tabela, sto znaci neizbeznu upotrebu JOINova
 * - roditeljska klasa moze biti usko grlo jer joj se precesto pristupa
 */

@Entity
public class Pharmacy {

    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(unique=false, nullable=false)
    private String name;

    @Column(unique=false, nullable=false)
    private String address;

    @Column(unique=false, nullable=true)
    private float averageScore;

    @Column(unique=false, nullable=true)
    private String medicationIds; //bice ubacene i cene

    //TODO neka mapa medication i cenu uvezujemo za svaku apoteku

    public Pharmacy() {

    }

    public Pharmacy(String name, String address, float averageScore, String medicationIds) {
        this.name = name;
        this.address = address;
        this.averageScore = averageScore;
        this.medicationIds = medicationIds;
    }

    public Pharmacy(Long id, String name, String address, float averageScore, String medicationIds) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.averageScore = averageScore;
        this.medicationIds = medicationIds;
    }

    public Pharmacy(Pharmacy p) {
        this.address = p.getAddress();
        this.averageScore = p.getAverageScore();
        this.name = p.getName();
        this.id = p.getId();
        this.medicationIds = p.getMedicationIds();
    }

    public String getMedicationIds() {
        return medicationIds;
    }

    public void setMedicationIds(String medicationIds) {
        this.medicationIds = medicationIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public Long getId() {
        return id;
    }

    // ovo bi stajalo u DTO
    public void copyValues(Pharmacy p) {
        this.address = p.getAddress();
        this.averageScore = p.getAverageScore();
        this.name = p.getName();
        this.medicationIds = p.getMedicationIds();
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", averageScore=" + averageScore +
                ", medicationIds='" + medicationIds + '\'' +
                '}';
    }
}
