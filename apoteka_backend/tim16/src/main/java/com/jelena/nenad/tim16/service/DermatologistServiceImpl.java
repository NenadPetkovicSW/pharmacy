package com.jelena.nenad.tim16.service;
import java.util.Optional;
import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.DermatologistAppointment;
import com.jelena.nenad.tim16.domain.DermatologistWorkTime;
import com.jelena.nenad.tim16.repository.DermatologistAppointmentRepository;
import com.jelena.nenad.tim16.repository.DermatologistRepository;
import com.jelena.nenad.tim16.repository.DermatologistWorkTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DermatologistServiceImpl implements DermatologistService{

    @Autowired
    private DermatologistRepository dermatologistRepository;

    @Autowired
    private DermatologistWorkTimeRepository dermatologistWTRepository;

    @Override
    public Collection<Dermatologist> findAll() {
        Collection<Dermatologist> dermatologists = dermatologistRepository.findAll();
        return dermatologists;
    }

    @Override
    public Collection<Dermatologist> findDermatologistToScore(long userID) {

        return dermatologistRepository.findDermatologistToScore(userID);
    }

    @Override
    public Collection<Dermatologist> findAllByName(String firstname, String lastname, Long id) {
        if(firstname.equals("0") && lastname.equals("0")){
            return dermatologistRepository.findAllFromPharmacy(id.toString());
        }
        if(firstname.equals("0") || lastname.equals("0")) {
            //ako je jedan od njih null
            if(id == -1){
                //sve apoteke
                return dermatologistRepository.findAllByName(firstname.equals("0")?lastname:firstname);
            }else{
                if(firstname.equals("0")){
                    return dermatologistRepository.findAllByLastName(lastname);
                }
                return dermatologistRepository.findAllByNameFromPharmacy(firstname,id.toString());
            }
        }else if(id == -1){
            return dermatologistRepository.findAllByNameLastName(firstname,lastname);
        }else{
            return dermatologistRepository.findAllByNameLastNameFromPharmacy(firstname, lastname, id.toString());
        }
    }

    @Override
    public Optional<Dermatologist> findOne(Long id) {
        Optional<Dermatologist> dermatologist = dermatologistRepository.findById(id);
        return dermatologist;
    }

    @Override
    public Dermatologist create(Dermatologist dermatologist) throws Exception {
        if (dermatologist.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        dermatologist.setId(Long.valueOf(findAll().size())+1);
        Dermatologist dermatologistSaved = dermatologistRepository.save(dermatologist);
        return dermatologistSaved;
    }

    @Override
    public Dermatologist update(Dermatologist dermatologist) throws Exception {
        Optional<Dermatologist> dermatologistToUpdate = findOne(dermatologist.getId());
        if (!dermatologistToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        dermatologistToUpdate.get().setFirstName(dermatologist.getFirstName());
        dermatologistToUpdate.get().setLastName(dermatologist.getLastName());
        dermatologistToUpdate.get().setPharmacy(dermatologist.getPharmacy());
        Dermatologist dermatologistSaved = dermatologistRepository.save(dermatologistToUpdate.get());
        return dermatologistSaved;
    }

    @Override
    public DermatologistWorkTime getWorkTime(long dermatologistId, long pharmacyId) {
        DermatologistWorkTime dwt = dermatologistWTRepository.findWorkTime(dermatologistId, pharmacyId);
        return dwt;
    }

    @Override
    public void delete(Long id) {
        dermatologistRepository.deleteById(id);
    }
}
