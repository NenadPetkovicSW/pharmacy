package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.Pharmacist;
import com.jelena.nenad.tim16.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    @Query(value = "SELECT * FROM pharmacy WHERE pharmacy.id IN (SELECT pharmacist_appointment.pharmacy_id FROM pharmacist_appointment WHERE patient_id = -1 and is_done is false and date>(NOW() + interval '1 day'))", nativeQuery = true)
    Collection<Pharmacy> getFreePharmacist();

    @Query(value = "SELECT * FROM pharmacy WHERE pharmacy.id IN (SELECT pharmacist_appointment.pharmacy_id FROM pharmacist_appointment WHERE patient_id = -1 and is_done is false and date=?1 and appointment_start>=?2 and appointment_end<= ?3 )", nativeQuery = true)
    Collection<Pharmacy> getFreePharmacistByDateAndTime(Date date, Time from, Time to);

    @Query("select d from Pharmacy d where d.id in (select da.pharmacyId from PharmacistAppointment da where da.patientId = :#{#userId}) or d.id in (select db.pharmacyId from DermatologistAppointment db where db.patientId = :#{#userId}) or d.id in (select dc.pharmacyId from MedicationOrder dc where dc.patientId = :#{#userId})")
    Collection<Pharmacy> findPharmacyToScore(@Param("userId") long userId);
}
