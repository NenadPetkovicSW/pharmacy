package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.ScorePharmacy;

import java.util.Collection;
import java.util.Optional;

public interface ScorePharmacyService {
    Collection<ScorePharmacy> findAll();

    Optional<ScorePharmacy> findOne(Long id);

    ScorePharmacy create(ScorePharmacy pharmacy) throws Exception;

    ScorePharmacy update(ScorePharmacy pharmacy) throws Exception;

    void delete(Long id);
}
