package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Collection;

@Service
public class MedicationServiceImpl implements MedicationService{

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Collection<Medication> findAll() {
        Collection<Medication> medications = medicationRepository.findAll();
        return medications;
    }

    @Override
    public Collection<Medication> findAll(Long id) {
        Collection<Medication> medications = medicationRepository.findAllFromPharmacy(id.toString());
        return medications;
    }

    @Override
    public Optional<Medication> findOne(Long id) {
        Optional<Medication> medication = medicationRepository.findById(id);
        return medication;
    }

    @Override
    public Medication updateAmount(int amount, Medication medication) {
        Optional<Medication> medicationToUpdate = findOne(medication.getId());
        medicationToUpdate.get().addAmount(amount);
        Medication medicationSaved = medicationRepository.save(medicationToUpdate.get());
        return medicationSaved;
    }

    @Override
    public Medication create(Medication medication) throws Exception {
        if (medication.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        medication.setId(Long.valueOf(findAll().size())+1);
        Medication medicationSaved = medicationRepository.save(medication);
        return medicationSaved;
    }

    @Override
    public Medication update(Medication medication) throws Exception {
        Optional<Medication> medicationToUpdate = findOne(medication.getId());
        if (!medicationToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        medicationToUpdate.get().setName(medication.getName());
        medicationToUpdate.get().setAlergiesid(medication.getAlergiesid());
        medicationToUpdate.get().setPharmacyid(medication.getPharmacyid());
        medicationToUpdate.get().setPrice(medication.getPrice());
        medicationToUpdate.get().setNewPriceDate(medication.getNewPriceDate());
        medicationToUpdate.get().setAmount(medication.getAmount());
        Medication medicationSaved = medicationRepository.save(medicationToUpdate.get());
        return medicationSaved;
    }

    @Override
    public Collection<Medication> findMedicationToScore(long userId) {
        return medicationRepository.findMedicationToScore(userId);
    }

    @Override
    public void delete(Long id) {
        medicationRepository.deleteById(id);
    }
}
