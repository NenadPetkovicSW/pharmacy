package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Allergie;

import java.util.Collection;
import java.util.Optional;

public interface AllergieService {
    Collection<Allergie> findAll();

    Optional<Allergie> findOne(Long id);

    Allergie create(Allergie allergie) throws Exception;

    Allergie update(Allergie allergie) throws Exception;

    Collection<Allergie> getAllergiesByMedicationId(long medicationId);



    void delete(Long id);
}
