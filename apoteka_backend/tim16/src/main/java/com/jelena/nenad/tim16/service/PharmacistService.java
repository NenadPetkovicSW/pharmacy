package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.Pharmacist;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import java.util.Collection;

public interface PharmacistService {
    Collection<Pharmacist> findAll();

    Collection<Pharmacist> findAllByName(String firstname, String lastname, Long id);

    Optional<Pharmacist> findOne(Long id);

    Pharmacist create(Pharmacist pharmacy) throws Exception;

    Pharmacist update(Pharmacist pharmacy) throws Exception;

    void delete(Long id);

    Collection<Pharmacist> getFreePharmacist(long pharmacyId);

    Collection<Pharmacist> findPharmacistToScore(@Param("userId") long userId);
}
