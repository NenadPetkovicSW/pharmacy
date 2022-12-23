package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Promotion {
    //npr moze biti 2+1 za neku cenu
    @Id
    private Long id;

    @Column(unique=false, nullable=true)
    private String name; //npr kao "AKCIJA BLA BLA"

    @Column(unique=false, nullable=false)
    private Long medicationID;

    @Column(unique=false, nullable=true)
    private String description; //stvarnni opis

    @Column(unique=false, nullable=false)
    private int amountOfProduct;

    @Column(unique=false, nullable=false)
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

    public Promotion(){
    }

    public Promotion(Long id, String name, Long medicationID, String description,
                     int amountOfProduct, int price, boolean isActive, String endDate, String pharmacyId) {
        this.id = id;
        this.name = name;
        this.medicationID = medicationID;
        this.description = description;
        this.amountOfProduct = amountOfProduct;
        this.price = price;
        this.isActive = isActive;
        this.endDate = endDate;
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

    public int getAmountOfProduct() {
        return amountOfProduct;
    }

    public void setAmountOfProduct(int amountOfProduct) {
        this.amountOfProduct = amountOfProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void copyValues(Promotion p) {
        this.name = p.getName();
        this.id = p.getId();
        this.medicationID = p.getMedicationID();
        this.endDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.price = p.getPrice();
        this.isActive = p.isActive();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
