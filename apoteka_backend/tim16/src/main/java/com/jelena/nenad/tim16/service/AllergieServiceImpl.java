package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Allergie;
import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.repository.AllergieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jelena.nenad.tim16.service.MedicationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AllergieServiceImpl implements AllergieService{

    @Autowired
    private AllergieRepository allergieRepository;

    @Autowired
    private MedicationService medicationRepository;

    @Override
    public Collection<Allergie> findAll() {
        Collection<Allergie> allergie = allergieRepository.findAll();
        return allergie;
    }

    @Override
    public Optional<Allergie> findOne(Long id) {
        Optional<Allergie> allergie = allergieRepository.findById(id);
        return allergie;
    }

    @Override
    public Allergie create(Allergie allergie) throws Exception {
        if (allergie.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        allergie.setId(Long.valueOf(findAll().size())+1);
        Allergie allergieSaved = allergieRepository.save(allergie);
        return allergieSaved;
    }

    @Override
    public Allergie update(Allergie allergie) throws Exception {
        Optional<Allergie> allergieToUpdate = findOne(allergie.getId());
        if (!allergieToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        allergieToUpdate.get().setName(allergie.getName());


        Allergie allergieSaved = allergieRepository.save(allergieToUpdate.get());
        return allergieSaved;
    }

    @Override
    public Collection<Allergie> getAllergiesByMedicationId(long medicationId) {
        Optional<Medication> medication = medicationRepository.findOne(medicationId);

        Collection<Allergie> allergies = allergieRepository.findAll();
        Collection<Allergie> return_allergies = new ArrayList<>();
        if(medication.get().getAlergiesid() != null) {
            String[] medication_allergies = medication.get().getAlergiesid().replace(" ", "").split(",");
            for (Allergie a : allergies) {
                for (String ma : medication_allergies) {
                    if (a.getId() == Long.parseLong(ma)) {
                        return_allergies.add(a);
                    }
                }
            }
        }
        return return_allergies;
    }

    @Override
    public void delete(Long id) {
        allergieRepository.deleteById(id);
    }
}
