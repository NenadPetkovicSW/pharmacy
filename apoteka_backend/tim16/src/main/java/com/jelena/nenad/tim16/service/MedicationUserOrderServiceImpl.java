package com.jelena.nenad.tim16.service;



import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.domain.MedicationOrder;
import com.jelena.nenad.tim16.service.MedicationService;
import com.jelena.nenad.tim16.repository.MedicationOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MedicationUserOrderServiceImpl implements MedicationUserOrderService{

    @Autowired
    private MedicationOrderRepository medicationRepository;

    @Autowired
    private MedicationService medicationService;

    @Override
    public Collection<MedicationOrder> findAll() {
        return medicationRepository.findAll();
    }

    @Override
    public Optional<MedicationOrder> findOne(Long id) {
        Optional<MedicationOrder> one = medicationRepository.findById(id);
        return one;
    }

    @Override
    public MedicationOrder create(MedicationOrder medicationOrder) throws Exception {
        if (medicationOrder.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }

        medicationOrder.setId(Long.valueOf(findAll().size())+1);
        MedicationOrder medicationOrderSaved = medicationRepository.save(medicationOrder);

        Medication new_med = medicationService.findOne(medicationOrder.getMedicationId()).get();
        if(new_med.getAmount()-medicationOrder.getAmount()<0)
        {
            throw new Exception("Ima manje lekova nego potraznja.");
        }
        new_med.setAmount(new_med.getAmount()-medicationOrder.getAmount());
        medicationService.update(new_med);

        return medicationOrderSaved;
    }

    @Override
    public MedicationOrder update(MedicationOrder medicationOrder) throws Exception {
        Optional<MedicationOrder> medicationOrderToUpdate = findOne(medicationOrder.getId());
        if (!medicationOrderToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        medicationOrderToUpdate.get().setMedicationId(medicationOrder.getMedicationId());
        medicationOrderToUpdate.get().setPharmacyId(medicationOrder.getPharmacyId());
        medicationOrderToUpdate.get().setAmount(medicationOrder.getAmount());
        medicationOrderToUpdate.get().setPrice(medicationOrder.getPrice());
        medicationOrderToUpdate.get().setDeliveryDate(medicationOrder.getDeliveryDate());
        medicationOrderToUpdate.get().setReservationDate(medicationOrder.getReservationDate());
        medicationOrderToUpdate.get().setPatientId(medicationOrder.getPatientId());

        MedicationOrder medicationOrderSaved = medicationRepository.save(medicationOrderToUpdate.get());
        return medicationOrderSaved;
    }

    @Override
    public Collection<MedicationOrder> getOrderByUser(long patientId) {
        return medicationRepository.getOrderByUser(patientId);
    }

    @Override
    public void delete(Long id) throws Exception {

        Optional<MedicationOrder> pom_ord = findOne(id);
        Optional<Medication> pom_med = medicationService.findOne(pom_ord.get().getMedicationId());
        pom_med.get().setAmount(pom_med.get().getAmount() + pom_ord.get().getAmount());
        Medication updateThis = pom_med.get();

        medicationService.update(updateThis);

        medicationRepository.deleteById(id);
    }
}
