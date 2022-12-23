package com.jelena.nenad.tim16.domain;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


@Entity
public class Medication {
    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=false, nullable=false)
    private String name;
    @Column(unique=false, nullable=true)
    private String pharmacyid;
    @Column(unique=false, nullable=true)
    private String alergiesid; //id,id,id...
    @Column(unique=false, nullable=true)
    private int price;
    @Column(unique = false, nullable = true)
    private String newPriceDate; //ovo se ubacuje kada se izmeni cena
    @Column(unique = false, nullable = true)
    private int amount;

    public String getNewPriceDate() {
        return newPriceDate;
    }

    public void setNewPriceDate(String newPriceDate) {
        this.newPriceDate = newPriceDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Medication() {
    }


    public Medication(String name, String pharmacyid, String alergiesid, int price, String newPriceDate) {
        this.name = name;
        this.pharmacyid = pharmacyid;
        this.alergiesid = alergiesid;
        this.price = price;
        this.amount = 5;//po defaultu
        if(this.price != price)
            this.newPriceDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public Medication(Long id, String name, String pharmacyid,int amount, String alergiesid, int price) {
        this.id = id;
        this.name = name;
        this.pharmacyid = pharmacyid;
        this.alergiesid = alergiesid;
        this.price = price;
        //menja se samo ako se promeni cena
        if(this.price != price)
            this.newPriceDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.amount = amount;
    }

    public Medication(String name, String pharmacyid,int amount, String alergiesid, int price) {
        this.name = name;
        this.pharmacyid = pharmacyid;
        this.alergiesid = alergiesid;
        this.price = price;
        //menja se samo ako se promeni cena
        if(this.price != price)
            this.newPriceDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.amount = amount;
    }



    public Medication(Medication m) {
        this.id = m.getId();
        this.name = m.getName();
        this.pharmacyid = m.getPharmacyid();
        this.alergiesid = m.getAlergiesid();
        if(this.price != m.getPrice())
            this.newPriceDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.price = m.getPrice();
        this.amount = m.getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(pharmacyid, that.pharmacyid) && Objects.equals(alergiesid, that.alergiesid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pharmacyid, alergiesid);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount){
        this.amount += amount;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    // ovo bi stajalo u DTO
    public void copyValues(Medication p) {

        this.name = p.getName();
        this.pharmacyid = p.getPharmacyid();
        this.alergiesid = p.getAlergiesid();
        if(this.price != p.getPrice())
            this.newPriceDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        this.price = p.getPrice();
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pharmacyid='" + pharmacyid + '\'' +
                ", alergiesid='" + alergiesid + '\'' +
                '}';
    }
}
