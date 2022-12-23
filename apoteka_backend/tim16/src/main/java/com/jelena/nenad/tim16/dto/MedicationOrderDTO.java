package com.jelena.nenad.tim16.dto;

import com.jelena.nenad.tim16.domain.Medication;

import javax.persistence.Column;

public class MedicationOrderDTO {
    public int Id;
    public boolean isNew;
    private Long medicationId;
    private String name;
    private String pharmacyid;
    private String alergiesid;
    private int price;
    private int amount;

    public MedicationOrderDTO(){}

    public MedicationOrderDTO(int id, boolean isNew, Long medicationId, String name, String pharmacyid, String alergiesid, int price, int amount) {
        Id = id;
        this.isNew = isNew;
        this.medicationId = medicationId;
        this.name = name;
        this.pharmacyid = pharmacyid;
        this.alergiesid = alergiesid;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharmacyid() {
        return pharmacyid;
    }

    public void setPharmacyid(String pharmacyid) {
        this.pharmacyid = pharmacyid;
    }

    public String getAlergiesid() {
        return alergiesid;
    }

    public void setAlergiesid(String alergiesid) {
        this.alergiesid = alergiesid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
