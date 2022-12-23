package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.PharmacistAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@Repository
public interface PharmacistAppointmentRepository extends JpaRepository<PharmacistAppointment, Long> {

    @Query(value = "select * from pharmacist_appointment where patient_id = :patient_id and is_done is false order by appointment_start asc", nativeQuery = true)
    Collection<PharmacistAppointment> getPharAppointmentsByUser(@Param("patient_id")  long patient_id);

    @Modifying
    @Transactional
    @Query(value = "update pharmacist_appointment set patient_id=?2 where id=?1", nativeQuery = true)
    int MakeAppointment(long idAppointment, long idUser);

    @Modifying
    @Transactional
    @Query(value = "update pharmacist_appointment set patient_id=-1 where id=?1 and date>(NOW() + interval '1 day')", nativeQuery = true)
    int CancelAppointment(long idAppointment);

    @Query(value = "select * from pharmacist_appointment where pharmacist_id=:pharmacistId and patient_id = -1 and date>(NOW() + interval '1 day') order by date asc,appointment_start asc", nativeQuery = true)
    Collection<PharmacistAppointment> getPharAppointmentsByPhar(@Param("pharmacistId")  long pharmacistId);
}
