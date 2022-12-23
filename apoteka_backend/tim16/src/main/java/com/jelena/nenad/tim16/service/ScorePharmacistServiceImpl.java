package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.ScoreDermatologist;
import com.jelena.nenad.tim16.domain.ScorePharmacist;
import com.jelena.nenad.tim16.repository.ScorePharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ScorePharmacistServiceImpl implements ScorePharmacistService{

    @Autowired
    private ScorePharmacistRepository pharmacistRepository;

    @Override
    public Collection<ScorePharmacist> findAll() {
        Collection<ScorePharmacist> dermatologists = pharmacistRepository.findAll();
        return dermatologists;
    }


    @Override
    public Optional<ScorePharmacist> findOne(Long id) {
        Optional<ScorePharmacist> dermatologist = pharmacistRepository.findById(id);
        return dermatologist;
    }

    @Override
    public ScorePharmacist create(ScorePharmacist dermatologist) throws Exception {
        if (dermatologist.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }

        Collection<ScorePharmacist> dermatologists = pharmacistRepository.getScoresByUserAndPharmacist(dermatologist.getIdUser(),dermatologist.getIdPharmacist());

        if(dermatologists.isEmpty())
        {
            dermatologist.setId(Long.valueOf(findAll().size())+1);
            ScorePharmacist dermatologistSaved = pharmacistRepository.save(dermatologist);
            return dermatologistSaved;
        }
        else {
            for(ScorePharmacist sf : dermatologists) {
                dermatologist.setId(sf.getId());
                break;
            }
            return update(dermatologist);
        }
    }

    @Override
    public ScorePharmacist update(ScorePharmacist dermatologist) throws Exception {
        Optional<ScorePharmacist> dermatologistToUpdate = findOne(dermatologist.getId());
        if (!dermatologistToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        dermatologistToUpdate.get().setIdPharmacist(dermatologist.getIdPharmacist());
        dermatologistToUpdate.get().setScore(dermatologist.getScore());
        dermatologistToUpdate.get().setIdUser(dermatologist.getIdUser());
        ScorePharmacist dermatologistSaved = pharmacistRepository.save(dermatologistToUpdate.get());
        return dermatologistSaved;
    }


    @Override
    public void delete(Long id) {
        pharmacistRepository.deleteById(id);
    }
}
