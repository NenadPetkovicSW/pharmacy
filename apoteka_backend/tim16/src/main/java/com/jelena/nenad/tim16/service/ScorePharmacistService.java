package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.ScorePharmacist;

import java.util.Collection;
import java.util.Optional;

public interface ScorePharmacistService {
    Collection<ScorePharmacist> findAll();

    Optional<ScorePharmacist> findOne(Long id);

    ScorePharmacist create(ScorePharmacist pharmacy) throws Exception;

    ScorePharmacist update(ScorePharmacist pharmacy) throws Exception;

    void delete(Long id);
}
