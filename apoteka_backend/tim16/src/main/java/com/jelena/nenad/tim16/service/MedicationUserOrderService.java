package com.jelena.nenad.tim16.service;


import com.jelena.nenad.tim16.domain.MedicationOrder;

import java.util.Collection;
import java.util.Optional;

public interface MedicationUserOrderService {

    Collection<MedicationOrder> findAll();

    Optional<MedicationOrder> findOne(Long id);

    MedicationOrder create(MedicationOrder medicationOrder) throws Exception;

    MedicationOrder update(MedicationOrder medicationOrder) throws Exception;

    Collection<MedicationOrder> getOrderByUser(long patientId);

    void delete(Long id) throws Exception;
}
