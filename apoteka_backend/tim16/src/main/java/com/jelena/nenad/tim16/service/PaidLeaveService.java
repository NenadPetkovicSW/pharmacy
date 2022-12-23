package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.domain.PaidLeave;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;


public interface PaidLeaveService {
    Collection<PaidLeave> findAll();

    Collection<PaidLeave> findAll(Long pharmacyId);

    Optional<PaidLeave> findOne(Long id);

    PaidLeave updateStatus(Long id, String message, boolean status);

    PaidLeave create(PaidLeave paidLeave) throws Exception;

}
