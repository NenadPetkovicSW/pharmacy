package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Action {
    @Id
    private Long id;

    @Column(unique=true, nullable=false)
    private String name;

    @Column(unique=true, nullable=false)
    private Long medicationID;

    @Column(unique=true, nullable=false)
    private String description;

    @Column(unique=true, nullable=false)
    private int newPrice;

    @Column(unique=true, nullable=false)
    private int price;

    @Column(unique=false, nullable=false)
    private boolean isActive;

    @Column(unique = false, nullable = false)
    private String endDate; //ovo se ubacuje kada se izmeni cena

    @Column(unique = false, nullable = false)
    private String pharmacyId;

    public String getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(String pharmacyId) {
        this.pharmacyId = pharmacyId;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Action(){
    }

    public Action(Long id, String name, Long medicationID, String description, int newPrice, int price,
                  boolean isActive, String endDate,String pharmacyId) {
        this.id = id;
        this.name = name;
        this.medicationID = medicationID;
        this.description = description;
        this.newPrice = newPrice;
        this.price = price;
        this.isActive = isActive;
        this.endDate = endDate;
        this.pharmacyId = pharmacyId;
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

    public Long getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(Long medicationID) {
        this.medicationID = medicationID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int price) {
        this.newPrice = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void copyValues(Action p) {
        this.name = p.getName();
        this.id = p.getId();
        this.medicationID = p.getMedicationID();
        this.endDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.price = p.getPrice();
        this.newPrice = p.getNewPrice();
        this.isActive = p.isActive();
    }
}
