package com.jelena.nenad.tim16.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Dermatologist {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=false, nullable=false)
    private String firstName;
    @Column(unique=false, nullable=false)
    private String lastName;
    @Column(unique=false, nullable=true)
    private String pharmacy;

    public Dermatologist() {
    }

    public Dermatologist(Long id, String firstName, String lastName, String pharmacy) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pharmacy = pharmacy;
    }

    public Dermatologist(String firstName, String lastName, String pharmacy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pharmacy = pharmacy;
    }

    public Dermatologist(Dermatologist d)
    {
        this.id = d.getId();
        this.firstName = d.getFirstName();
        this.lastName = d.getLastName();
        this.pharmacy = d.getPharmacy();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    //T ovo bi stajalo u DTO
    public void copyValues(Dermatologist p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.pharmacy = p.getPharmacy();
    }

    @Override
    public String toString() {
        return "Dermatologist{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pharmacy='" + pharmacy + '\'' +
                '}';
    }
}
