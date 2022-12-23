package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.DermatologistAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface DermatologistAppointmentRepository extends JpaRepository<DermatologistAppointment, Long> {
    @Modifying
    @Transactional
    @Query(value = "update dermatologist_appointment set patient_id=?2 where id=?1", nativeQuery = true)
    int MakeAppointment(long idAppointment, long idUser);

    @Modifying
    @Transactional
    @Query(value = "update dermatologist_appointment set patient_id=-1 where id=?1 and date>(NOW() + interval '1 day')", nativeQuery = true)
    int CancelAppointment(long idAppointment);

    @Query(value = "select * from dermatologist_appointment where pharmacy_id=:pharmacyId and patient_id = -1 and date>(NOW() + interval '1 day') order by appointment_start asc", nativeQuery = true)
    Collection<DermatologistAppointment> getDerAppointmentsByPhar( @Param("pharmacyId")  long pharmacyId);

    @Query(value = "select * from dermatologist_appointment where patient_id = :patient_id and is_done is false order by appointment_start asc", nativeQuery = true)
    Collection<DermatologistAppointment> getDerAppointmentsByUser( @Param("patient_id")  long patient_id);
}
