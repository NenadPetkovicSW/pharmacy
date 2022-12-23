package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.dto.MedicationOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MedicationOrderServiceImpl implements MedicationOrderService {

    public Collection<MedicationOrderDTO> order = new ArrayList<>();
    @Autowired
    private MedicationService medicationService;

    @Override
    public Collection<MedicationOrderDTO> findAll() {
        return order;
    }

    @Override
    public MedicationOrderDTO findOne(Long id) {
        for(MedicationOrderDTO o : order){
            if(o.getId() == id)
                return o;
        }
        return null;
    }

    @Override
    public Medication create(MedicationOrderDTO medicationOrderDTO) throws Exception {
        Medication tempMedication = new Medication(medicationOrderDTO.getName(),
                                                    medicationOrderDTO.getPharmacyid(),
                                                    medicationOrderDTO.getAmount(),
                                                    medicationOrderDTO.getAlergiesid(),
                                                    medicationOrderDTO.getPrice());
        if(medicationOrderDTO.isNew){
            return medicationService.create(tempMedication);
        }else {
            tempMedication.setId(medicationOrderDTO.getMedicationId());
            return medicationService.updateAmount(medicationOrderDTO.getAmount(),tempMedication);
        }
    }
}
