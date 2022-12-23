package com.jelena.nenad.tim16.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Entity
public class PharmacistAppointment {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=false, nullable=false)
    private boolean isDone;
    @Column(unique=false, nullable=false)
    private Date date;
    @Column(unique=false, nullable=false)
    private Time appointmentStart;
    @Column(unique=false, nullable=false)
    private Time appointmentEnd;
    @Column(unique=false, nullable=false)
    private long duration;
    @Column(unique=false, nullable=false)
    private long pharmacistId;
    @Column(unique=false, nullable=false)
    private double price;
    @Column(unique=false, nullable=true)
    private Long patientId;
    @Column(unique=false, nullable=false)
    private long pharmacyId;

    public PharmacistAppointment() {
    }

    public PharmacistAppointment(boolean isDone, Date date, Time appointmentStart, Time appointmentEnd, long duration, long pharmacistId, double price, Long patientId, long pharmacyId) {
        this.isDone = isDone;
        this.date = date;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.duration = duration;
        this.pharmacistId = pharmacistId;
        this.price = price;
        this.patientId = patientId;
        this.pharmacyId = pharmacyId;
    }

    public PharmacistAppointment(Long id, boolean isDone, Date date, Time appointmentStart, Time appointmentEnd, long duration, long pharmacistId, double price, Long patientId, long pharmacyId) {
        this.id = id;
        this.isDone = isDone;
        this.date = date;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.duration = duration;
        this.pharmacistId = pharmacistId;
        this.price = price;
        this.patientId = patientId;
        this.pharmacyId = pharmacyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(Time appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public Time getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(Time appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getPharmacistId() {
        return pharmacistId;
    }

    public void setPharmacistId(long pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    @Override
    public String toString() {
        return "PharamcistAppointment{" +
                "id=" + id +
                ", isDone=" + isDone +
                ", date=" + date +
                ", appointmentStart=" + appointmentStart +
                ", appointmentEnd=" + appointmentEnd +
                ", duration=" + duration +
                ", pharmacistId=" + pharmacistId +
                ", price=" + price +
                ", patientId=" + patientId +
                ", pharmacyId=" + pharmacyId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PharmacistAppointment that = (PharmacistAppointment) o;
        return isDone == that.isDone && duration == that.duration && pharmacistId == that.pharmacistId && Double.compare(that.price, price) == 0 && pharmacyId == that.pharmacyId && Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(appointmentStart, that.appointmentStart) && Objects.equals(appointmentEnd, that.appointmentEnd) && Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isDone, date, appointmentStart, appointmentEnd, duration, pharmacistId, price, patientId, pharmacyId);
    }

    //T ovo bi stajalo u DTO
    public void copyValues(PharmacistAppointment p) {
            this.appointmentEnd = p.getAppointmentEnd();
            this.appointmentStart = p.getAppointmentStart();
            this.duration = ChronoUnit.MINUTES.between(this.appointmentStart.toInstant(),this.appointmentEnd.toInstant());
            this.date = p.getDate();
            this.pharmacistId = p.getPharmacistId();
            this.patientId = p.getPatientId();
            this.price = p.getPrice();
            this.pharmacyId = p.getPharmacyId();
    }


}
