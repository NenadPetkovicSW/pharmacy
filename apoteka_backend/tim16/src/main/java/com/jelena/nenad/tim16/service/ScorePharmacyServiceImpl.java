package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.ScorePharmacy;
import com.jelena.nenad.tim16.repository.ScorePharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ScorePharmacyServiceImpl implements ScorePharmacyService{

    @Autowired
    private ScorePharmacyRepository pharmacistRepository;

    @Override
    public Collection<ScorePharmacy> findAll() {
        Collection<ScorePharmacy> dermatologists = pharmacistRepository.findAll();
        return dermatologists;
    }


    @Override
    public Optional<ScorePharmacy> findOne(Long id) {
        Optional<ScorePharmacy> dermatologist = pharmacistRepository.findById(id);
        return dermatologist;
    }

    @Override
    public ScorePharmacy create(ScorePharmacy dermatologist) throws Exception {
        if (dermatologist.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }

        Collection<ScorePharmacy> dermatologists = pharmacistRepository.getScoresByUserAndPharmacy(dermatologist.getIdUser(),dermatologist.getIdPharmacy());

        if(dermatologists.isEmpty())
        {
            dermatologist.setId(Long.valueOf(findAll().size())+1);
            ScorePharmacy dermatologistSaved = pharmacistRepository.save(dermatologist);
            return dermatologistSaved;
        }
        else {
            for(ScorePharmacy sf : dermatologists) {
                dermatologist.setId(sf.getId());
                break;
            }
            return update(dermatologist);
        }
    }

    @Override
    public ScorePharmacy update(ScorePharmacy dermatologist) throws Exception {
        Optional<ScorePharmacy> dermatologistToUpdate = findOne(dermatologist.getId());
        if (!dermatologistToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        dermatologistToUpdate.get().setIdPharmacy(dermatologist.getIdPharmacy());
        dermatologistToUpdate.get().setScore(dermatologist.getScore());
        dermatologistToUpdate.get().setIdUser(dermatologist.getIdUser());
        ScorePharmacy dermatologistSaved = pharmacistRepository.save(dermatologistToUpdate.get());
        return dermatologistSaved;
    }


    @Override
    public void delete(Long id) {
        pharmacistRepository.deleteById(id);
    }
}
