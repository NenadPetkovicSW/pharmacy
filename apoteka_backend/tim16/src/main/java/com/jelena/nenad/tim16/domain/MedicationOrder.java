package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


@Entity
public class MedicationOrder {
    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=false, nullable=false)
    private Long pharmacyId;
    @Column(unique=false, nullable=false)
    private Long medicationId;
    @Column(unique=false, nullable=false)
    private Long patientId;
    @Column(unique=false, nullable=false)
    private double price;
    @Column(unique = false, nullable = false)
    private Date reservationDate;
    @Column(unique = false, nullable = false)
    private Date deliveryDate;
    @Column(unique = false, nullable = false)
    private int amount;

    public MedicationOrder(Long pharmacyId, Long medicationId, Long patientId, double price, Date reservationDate, Date deliveryDate, int amount) {
        this.pharmacyId = pharmacyId;
        this.medicationId = medicationId;
        this.patientId = patientId;
        this.price = price;
        this.reservationDate = reservationDate;
        this.deliveryDate = deliveryDate;
        this.amount = amount;
    }

    public MedicationOrder(Long id, Long pharmacyId, Long medicationId, Long patientId, double price, Date reservationDate, Date deliveryDate, int amount) {
        this.id = id;
        this.pharmacyId = pharmacyId;
        this.medicationId = medicationId;
        this.patientId = patientId;
        this.price = price;
        this.reservationDate = reservationDate;
        this.deliveryDate = deliveryDate;
        this.amount = amount;
    }

    public MedicationOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationOrder that = (MedicationOrder) o;
        return Double.compare(that.price, price) == 0 && amount == that.amount && Objects.equals(id, that.id) && Objects.equals(pharmacyId, that.pharmacyId) && Objects.equals(medicationId, that.medicationId) && Objects.equals(patientId, that.patientId) && Objects.equals(reservationDate, that.reservationDate) && Objects.equals(deliveryDate, that.deliveryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pharmacyId, medicationId, patientId, price, reservationDate, deliveryDate, amount);
    }

    @Override
    public String toString() {
        return "MedicationOrder{" +
                "id=" + id +
                ", pharmacyId=" + pharmacyId +
                ", medicationId=" + medicationId +
                ", patientId=" + patientId +
                ", price=" + price +
                ", reservationDate=" + reservationDate +
                ", deliveryDate=" + deliveryDate +
                ", amount=" + amount +
                '}';
    }



    // ovo bi stajalo u DTO
    public void copyValues(MedicationOrder p) {

        this.amount = p.getAmount();
        this.deliveryDate = p.getDeliveryDate();
        this.medicationId = p.getMedicationId();
        this.reservationDate = p.getReservationDate();
        this.price = p.getPrice();
        this.pharmacyId = p.getPharmacyId();
        this.patientId = p.getPatientId();
    }
}
