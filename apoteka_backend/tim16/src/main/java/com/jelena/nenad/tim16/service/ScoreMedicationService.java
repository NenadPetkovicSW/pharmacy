package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.ScoreMedication;

import java.util.Collection;
import java.util.Optional;

public interface ScoreMedicationService {
    Collection<ScoreMedication> findAll();

    Optional<ScoreMedication> findOne(Long id);

    ScoreMedication create(ScoreMedication pharmacy) throws Exception;

    ScoreMedication update(ScoreMedication pharmacy) throws Exception;

    void delete(Long id);
}
