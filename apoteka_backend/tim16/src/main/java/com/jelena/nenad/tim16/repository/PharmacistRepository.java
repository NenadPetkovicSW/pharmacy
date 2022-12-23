package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long> {
    @Query(value = "SELECT * FROM pharmacist WHERE pharmacist.id IN (SELECT pharmacist_appointment.pharmacist_id FROM pharmacist_appointment WHERE patient_id = -1 and is_done is false and pharmacy_id = ?1 and date>(NOW() + interval '1 day'))", nativeQuery = true)
    Collection<Pharmacist> getFreePharmacist(long pharmacyId);

    @Query("select d from Pharmacist d where d.firstName = :#{#firstname}")
    Collection<Pharmacist> findAllByName(@Param("firstname") String firstname);

    @Query("select d from Pharmacist d where d.lastName = :#{#lastname}")
    Collection<Pharmacist> findAllByLastName(@Param("lastname") String lastname);

    @Query("select d from Pharmacist d where d.firstName = :#{#firstname} and d.lastName=:#{#lastname}")
    Collection<Pharmacist> findAllByNameLastName(@Param("firstname") String firstname,
                                                    @Param("lastname") String lastname);
    @Query("select d from Pharmacist d where d.firstName = :#{#firstname} and d.lastName=:#{#lastname} and d.pharmacy=:#{#pharmacyid}")
    Collection<Pharmacist> findAllByNameLastNameFromPharmacy(@Param("firstname") String firstname,
                                                                @Param("lastname") String lastname,
                                                                @Param("pharmacyid") Long pharmacyid);
    @Query("select d from Pharmacist d where d.firstName = :#{#firstname} and d.pharmacy=:#{#pharmacyid}")
    Collection<Pharmacist> findAllByNameFromPharmacy(@Param("firstname") String firstname,
                                                        @Param("pharmacyid") Long pharmacyid);
    @Query("select d from Pharmacist d where d.pharmacy=:#{#pharmacyid}")
    Collection<Pharmacist> findAllFromPharmacy(@Param("pharmacyid") Long pharmacyid);

    @Query("select d from Pharmacist d where d.id in (select da.pharmacistId from PharmacistAppointment da where da.patientId = :#{#userId})")
    Collection<Pharmacist> findPharmacistToScore(@Param("userId") long userId);
}
