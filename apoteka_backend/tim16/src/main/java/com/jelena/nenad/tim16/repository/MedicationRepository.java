package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

    @Query("select d from Medication d where d.pharmacyid=:#{#pharmacyid}")
    Collection<Medication> findAllFromPharmacy(@Param("pharmacyid") String pharmacyid);

    @Query("select d from Medication d where d.id in (select da.medicationId from MedicationOrder da where da.patientId = :#{#userId})")
    Collection<Medication> findMedicationToScore(@Param("userId") long userId);
}
