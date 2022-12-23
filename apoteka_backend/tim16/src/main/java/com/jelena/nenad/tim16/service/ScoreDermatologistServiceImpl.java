package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.ScoreDermatologist;
import com.jelena.nenad.tim16.repository.ScoreDermatologistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ScoreDermatologistServiceImpl implements ScoreDermatologistService{

    @Autowired
    private ScoreDermatologistRepository dermatologistRepository;

    @Override
    public Collection<ScoreDermatologist> findAll() {
        Collection<ScoreDermatologist> dermatologists = dermatologistRepository.findAll();
        return dermatologists;
    }


    @Override
    public Optional<ScoreDermatologist> findOne(Long id) {
        Optional<ScoreDermatologist> dermatologist = dermatologistRepository.findById(id);
        return dermatologist;
    }

    @Override
    public ScoreDermatologist create(ScoreDermatologist dermatologist) throws Exception {
        if (dermatologist.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }

        Collection<ScoreDermatologist> dermatologists = dermatologistRepository.getScoresByUserAndDermatologist(dermatologist.getIdUser(),dermatologist.getIdDermatologist());

        if(dermatologists.isEmpty())
        {
            dermatologist.setId(Long.valueOf(findAll().size())+1);
            ScoreDermatologist dermatologistSaved = dermatologistRepository.save(dermatologist);
            return dermatologistSaved;
        }
        else {
            for(ScoreDermatologist sd : dermatologists) {
                dermatologist.setId(sd.getId());
                break;
            }
            return update(dermatologist);
        }
    }

    @Override
    public ScoreDermatologist update(ScoreDermatologist dermatologist) throws Exception {
        Optional<ScoreDermatologist> dermatologistToUpdate = findOne(dermatologist.getId());
        if (!dermatologistToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        dermatologistToUpdate.get().setIdDermatologist(dermatologist.getIdDermatologist());
        dermatologistToUpdate.get().setScore(dermatologist.getScore());
        dermatologistToUpdate.get().setIdUser(dermatologist.getIdUser());
        ScoreDermatologist dermatologistSaved = dermatologistRepository.save(dermatologistToUpdate.get());
        return dermatologistSaved;
    }

    @Override
    public Collection<ScoreDermatologist> getUserScores(long idUser) {
        return dermatologistRepository.getUserScores(idUser);
    }

    @Override
    public Collection<ScoreDermatologist> getScoresByUserAndDermatologist(long idUser, long idDermatologist) {
        return dermatologistRepository.getScoresByUserAndDermatologist(idUser,idDermatologist);
    }


    @Override
    public void delete(Long id) {
        dermatologistRepository.deleteById(id);
    }
}
