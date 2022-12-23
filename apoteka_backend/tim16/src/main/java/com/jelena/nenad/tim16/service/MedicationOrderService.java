package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.dto.MedicationOrderDTO;

import java.util.Collection;
import java.util.Optional;

public interface MedicationOrderService {

    Collection<MedicationOrderDTO> findAll();

    MedicationOrderDTO findOne(Long id);

    Medication create(MedicationOrderDTO medicationOrderDTO) throws Exception;
}
