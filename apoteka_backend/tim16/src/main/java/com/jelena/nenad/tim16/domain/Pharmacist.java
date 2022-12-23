package com.jelena.nenad.tim16.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Pharmacist {
    //TODO dto ce imati ID, a ovo pravi objekat pharmacy
    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=false, nullable=false)
    private String firstName;
    @Column(unique=false, nullable=false)
    private String lastName;
    @Column(unique=false, nullable=true)
    private Long pharmacy;
    @Column(unique=false, nullable=true)
    private LocalTime startTime; //format HH-mm-ss-ns
    @Column(unique=false, nullable=true)
    private LocalTime endTime; //format HH-mm-ss-ns

    public Pharmacist() {
    }


    public Pharmacist(Long id, String firstName, String lastName, Long pharmacy, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pharmacy = pharmacy;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Pharmacist(Long id, String firstName, String lastName, Long pharmacy, String startTime, String endTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pharmacy = pharmacy;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    public Pharmacist(Pharmacist p) {
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.pharmacy = p.getPharmacy();
        this.startTime = p.getStartTime();
        this.endTime = p.getEndTime();
    }



    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pharmacist that = (Pharmacist) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(pharmacy, that.pharmacy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, pharmacy);
    }

    public Long getPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(Long pharmacy) {
        this.pharmacy = pharmacy;
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

    // ovo bi stajalo u DTO
    public void copyValues(Pharmacist p) {
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.id = p.getId();
        this.pharmacy = p.getPharmacy();
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pharmacy=" + pharmacy +
                '}';
    }
}
