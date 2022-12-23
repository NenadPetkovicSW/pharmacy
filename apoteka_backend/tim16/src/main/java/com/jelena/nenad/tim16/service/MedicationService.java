package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Medication;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Collection;

public interface MedicationService {
    Collection<Medication> findAll();

    Collection<Medication> findAll(Long id);

    Optional<Medication> findOne(Long id);

    Medication updateAmount(int amount, Medication m);

    Medication create(Medication medication) throws Exception;

    Medication update(Medication medication) throws Exception;

    Collection<Medication> findMedicationToScore(@Param("userId") long userId);

    void delete(Long id);
}
