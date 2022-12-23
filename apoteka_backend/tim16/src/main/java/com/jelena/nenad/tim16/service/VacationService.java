package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.PaidLeave;
import com.jelena.nenad.tim16.domain.Vacation;

import java.util.Collection;
import java.util.Optional;

public interface VacationService {
    Collection<Vacation> findAll();

    Optional<Vacation> findOne(Long id);

    Collection<Vacation> findAll(Long pharmacyId);

    Vacation updateStatus(Long id, String message, boolean status);

    Vacation create(Vacation paidLeave) throws Exception;
}
