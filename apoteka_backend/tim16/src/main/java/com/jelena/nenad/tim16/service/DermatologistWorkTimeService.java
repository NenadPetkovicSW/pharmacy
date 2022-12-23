package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.DermatologistWorkTime;

import java.util.Collection;
import java.util.Optional;

public interface DermatologistWorkTimeService {
    Collection<DermatologistWorkTime> findAll();

    Collection<DermatologistWorkTime> findAll(Long id);

    Collection<DermatologistWorkTime> findAllPharmacy(Long id, Long pharmacyId);

    Optional<DermatologistWorkTime> findOne(Long id);

    DermatologistWorkTime create(DermatologistWorkTime pharmacy) throws Exception;

    DermatologistWorkTime update(DermatologistWorkTime pharmacy) throws Exception;

    void delete(Long id);
}
