package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.DermatologistWorkTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Collection;

public interface DermatologistService {
    Collection<Dermatologist> findAll();

    Collection<Dermatologist> findDermatologistToScore(long userId);

    Collection<Dermatologist> findAllByName(String firstname, String lastname, Long id);

    Optional<Dermatologist> findOne(Long id);

    Dermatologist create(Dermatologist pharmacy) throws Exception;

    Dermatologist update(Dermatologist pharmacy) throws Exception;

    DermatologistWorkTime getWorkTime(long dermatologistId, long pharmacyId);

    void delete(Long id);
}
