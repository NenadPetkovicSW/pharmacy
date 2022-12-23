package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.ScoreDermatologist;

import java.util.Collection;
import java.util.Optional;

public interface ScoreDermatologistService {
    Collection<ScoreDermatologist> findAll();

    Optional<ScoreDermatologist> findOne(Long id);

    ScoreDermatologist create(ScoreDermatologist pharmacy) throws Exception;

    ScoreDermatologist update(ScoreDermatologist pharmacy) throws Exception;

    Collection<ScoreDermatologist> getUserScores(long idUser);

    Collection<ScoreDermatologist> getScoresByUserAndDermatologist(long idUser, long idDermatologist);

    void delete(Long id);
}
