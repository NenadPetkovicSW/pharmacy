package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Objects;

@Entity
public class DermatologistWorkTime {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=false, nullable=false)
    private Time shiftStart;
    @Column(unique=false, nullable=false)
    private Time shiftEnd;
    @Column(unique=false, nullable=false)
    private long pharmacyId;
    @Column(unique=false, nullable=false)
    private long dermatologistId;

    public DermatologistWorkTime(Long id, Time shiftStart, Time shiftEnd, long pharmacyId, long dermatologistId) {
        this.id = id;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.pharmacyId = pharmacyId;
        this.dermatologistId = dermatologistId;
    }

    public DermatologistWorkTime(Time shiftStart, Time shiftEnd, long pharmacyId, long dermatologistId) {
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
        this.pharmacyId = pharmacyId;
        this.dermatologistId = dermatologistId;
    }

    public DermatologistWorkTime() {
    }

    public long getDermatologistId() {
        return dermatologistId;
    }

    public void setDermatologistId(long dermatologistId) {
        this.dermatologistId = dermatologistId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(Time shiftStart) {
        this.shiftStart = shiftStart;
    }

    public Time getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(Time shiftEnd) {
        this.shiftEnd = shiftEnd;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DermatologistWorkTime that = (DermatologistWorkTime) o;
        return pharmacyId == that.pharmacyId && dermatologistId == that.dermatologistId && Objects.equals(id, that.id) && Objects.equals(shiftStart, that.shiftStart) && Objects.equals(shiftEnd, that.shiftEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shiftStart, shiftEnd, pharmacyId, dermatologistId);
    }

    @Override
    public String toString() {
        return "DermatologistWorkTime{" +
                "id=" + id +
                ", shiftStart=" + shiftStart +
                ", shiftEnd=" + shiftEnd +
                ", pharmacyId=" + pharmacyId +
                ", dermatologistId=" + dermatologistId +
                '}';
    }

    //T ovo bi stajalo u DTO
    public void copyValues(DermatologistWorkTime p) {
            this.shiftEnd = p.getShiftEnd();
            this.shiftStart = p.getShiftStart();
            this.pharmacyId = p.getPharmacyId();
            this.dermatologistId = p.getDermatologistId();
    }


}
