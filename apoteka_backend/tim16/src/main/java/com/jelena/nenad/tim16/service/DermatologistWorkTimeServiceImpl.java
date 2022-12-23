package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.DermatologistWorkTime;
import com.jelena.nenad.tim16.repository.DermatologistWorkTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class DermatologistWorkTimeServiceImpl implements DermatologistWorkTimeService{

    @Autowired
    private DermatologistWorkTimeRepository dermatologistRepository;

    @Override
    public Collection<DermatologistWorkTime> findAll() {
        Collection<DermatologistWorkTime> dermatologists = dermatologistRepository.findAll();
        return dermatologists;
    }

    @Override
    public Collection<DermatologistWorkTime> findAll(Long id) {
        Collection<DermatologistWorkTime> foundActions = new ArrayList<>();
        for(DermatologistWorkTime a: dermatologistRepository.findAll()){
            if(a.getDermatologistId() == id){
                foundActions.add(a);
            }
        }
        return foundActions;
    }

    @Override
    public Collection<DermatologistWorkTime> findAllPharmacy(Long id, Long pharmacyId) {
        Collection<DermatologistWorkTime> foundActions = new ArrayList<>();
        for(DermatologistWorkTime a: dermatologistRepository.findAll()){
            if(a.getPharmacyId() == pharmacyId && a.getDermatologistId() == id){
                foundActions.add(a);
            }
        }
        return foundActions;
    }

    @Override
    public Optional<DermatologistWorkTime> findOne(Long id) {
        Optional<DermatologistWorkTime> dermatologist = dermatologistRepository.findById(id);
        return dermatologist;
    }

    @Override
    public DermatologistWorkTime create(DermatologistWorkTime dermatologist) throws Exception {
        if (dermatologist.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        dermatologist.setId(Long.valueOf(findAll().size())+1);
        //provera ako je isto vreme i id i sve, onda error jer ne moze tako

        DermatologistWorkTime dermatologistSaved = dermatologistRepository.save(dermatologist);
        return dermatologistSaved;
    }

    @Override
    public DermatologistWorkTime update(DermatologistWorkTime dermatologist) throws Exception {
        Optional<DermatologistWorkTime> dermatologistToUpdate = findOne(dermatologist.getId());
        if (!dermatologistToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        dermatologistToUpdate.get().setPharmacyId(dermatologist.getPharmacyId());
        dermatologistToUpdate.get().setShiftStart(dermatologist.getShiftStart());
        dermatologistToUpdate.get().setShiftEnd(dermatologist.getShiftEnd());
        dermatologistToUpdate.get().setDermatologistId(dermatologist.getDermatologistId());


        DermatologistWorkTime dermatologistSaved = dermatologistRepository.save(dermatologistToUpdate.get());
        return dermatologistSaved;
    }

    @Override
    public void delete(Long id) {
        dermatologistRepository.deleteById(id);
    }
}
