package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.ScoreMedication;
import com.jelena.nenad.tim16.repository.ScoreMedcaationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ScoreMedicationServiceImpl implements ScoreMedicationService{

    @Autowired
    private ScoreMedcaationRepository pharmacistRepository;

    @Override
    public Collection<ScoreMedication> findAll() {
        Collection<ScoreMedication> dermatologists = pharmacistRepository.findAll();
        return dermatologists;
    }


    @Override
    public Optional<ScoreMedication> findOne(Long id) {
        Optional<ScoreMedication> dermatologist = pharmacistRepository.findById(id);
        return dermatologist;
    }

    @Override
    public ScoreMedication create(ScoreMedication dermatologist) throws Exception {
        if (dermatologist.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }

        Collection<ScoreMedication> dermatologists = pharmacistRepository.getScoresByUserAndPMedcatio(dermatologist.getIdUser(),dermatologist.getIdMedication());

        if(dermatologists.isEmpty())
        {
            dermatologist.setId(Long.valueOf(findAll().size())+1);
            ScoreMedication dermatologistSaved = pharmacistRepository.save(dermatologist);
            return dermatologistSaved;
        }
        else {
            for(ScoreMedication sf : dermatologists) {
                dermatologist.setId(sf.getId());
                break;
            }
            return update(dermatologist);
        }
    }

    @Override
    public ScoreMedication update(ScoreMedication dermatologist) throws Exception {
        Optional<ScoreMedication> dermatologistToUpdate = findOne(dermatologist.getId());
        if (!dermatologistToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        dermatologistToUpdate.get().setIdMedication(dermatologist.getIdMedication());
        dermatologistToUpdate.get().setScore(dermatologist.getScore());
        dermatologistToUpdate.get().setIdUser(dermatologist.getIdUser());
        ScoreMedication dermatologistSaved = pharmacistRepository.save(dermatologistToUpdate.get());
        return dermatologistSaved;
    }


    @Override
    public void delete(Long id) {
        pharmacistRepository.deleteById(id);
    }
}
