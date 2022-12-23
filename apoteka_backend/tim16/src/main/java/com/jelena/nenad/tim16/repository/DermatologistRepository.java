package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.Dermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DermatologistRepository extends JpaRepository<Dermatologist, Long> {

    @Query("select d from Dermatologist d where d.firstName = :#{#firstname}")
    Collection<Dermatologist> findAllByName(@Param("firstname") String firstname);

    @Query("select d from Dermatologist d where d.lastName = :#{#lastname}")
    Collection<Dermatologist> findAllByLastName(@Param("lastname") String lastname);

    @Query("select d from Dermatologist d where d.firstName = :#{#firstname} and d.lastName=:#{#lastname}")
    Collection<Dermatologist> findAllByNameLastName(@Param("firstname") String firstname,
                                                    @Param("lastname") String lastname);
    @Query("select d from Dermatologist d where d.firstName = :#{#firstname} and d.lastName=:#{#lastname} and d.pharmacy LIKE %:pharmacyid%")
    Collection<Dermatologist> findAllByNameLastNameFromPharmacy(@Param("firstname") String firstname,
                                                    @Param("lastname") String lastname,
                                                    @Param("pharmacyid") String pharmacyid);
    @Query("select d from Dermatologist d where d.firstName = :#{#firstname} and d.pharmacy LIKE %:pharmacyid%")
    Collection<Dermatologist> findAllByNameFromPharmacy(@Param("firstname") String firstname,
                                                                @Param("pharmacyid") String pharmacyid);

    @Query("select d from Dermatologist d where d.pharmacy LIKE %:pharmacyid%")
    Collection<Dermatologist> findAllFromPharmacy(@Param("pharmacyid") String pharmacyid);

    @Query("select d from Dermatologist d where d.id in (select da.dermatologistId from DermatologistAppointment da where da.patientId = :#{#userId})")
    Collection<Dermatologist> findDermatologistToScore(@Param("userId") long userId);
}
