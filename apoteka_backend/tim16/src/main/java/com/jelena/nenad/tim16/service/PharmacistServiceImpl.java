package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.Pharmacist;
import com.jelena.nenad.tim16.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Collection;

@Service
public class PharmacistServiceImpl implements PharmacistService{

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Override
    public Collection<Pharmacist> findAll() {
        Collection<Pharmacist> pharmacists = pharmacistRepository.findAll();
        return pharmacists;
    }

    @Override
    public Collection<Pharmacist> findAllByName(String firstname, String lastname, Long id) {
        if(firstname.equals("0") && lastname.equals("0")){
            return pharmacistRepository.findAllFromPharmacy(id);
        }
        if(firstname.equals("0") || lastname.equals("0")) {
            //ako je jedan od njih null
            if(id == -1){
                //sve apoteke
                return pharmacistRepository.findAllByName(firstname.equals("0")?lastname:firstname);
            }else{
                if(firstname.equals("0")){
                    return pharmacistRepository.findAllByLastName(lastname);
                }
                return pharmacistRepository.findAllByNameFromPharmacy(firstname,id);
            }
        }else if(id == -1){
            return pharmacistRepository.findAllByNameLastName(firstname,lastname);
        }else{
            return pharmacistRepository.findAllByNameLastNameFromPharmacy(firstname, lastname, id);
        }
    }

    @Override
    public Optional<Pharmacist> findOne(Long id) {
        Optional<Pharmacist> pharmacist = pharmacistRepository.findById(id);
        return pharmacist;
    }

    @Override
    public Pharmacist create(Pharmacist pharmacist) throws Exception {
        if (pharmacist.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        pharmacist.setId(Long.valueOf(findAll().size())+1);
        Pharmacist pharmacistSaved = pharmacistRepository.save(pharmacist);
        return pharmacistSaved;
    }

    @Override
    public Pharmacist update(Pharmacist pharmacist) throws Exception {
        Optional<Pharmacist> pharmacistToUpdate = findOne(pharmacist.getId());
        if (!pharmacistToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        pharmacistToUpdate.get().setFirstName(pharmacist.getFirstName());
        pharmacistToUpdate.get().setLastName(pharmacist.getLastName());
        pharmacistToUpdate.get().setPharmacy(pharmacist.getPharmacy());
        Pharmacist pharmacistSaved = pharmacistRepository.save(pharmacistToUpdate.get());
        return pharmacistSaved;
    }

    @Override
    public void delete(Long id) {
        pharmacistRepository.deleteById(id);
    }

    @Override
    public Collection<Pharmacist> getFreePharmacist(long pharmacyId) {
        return pharmacistRepository.getFreePharmacist(pharmacyId);
    }

    @Override
    public Collection<Pharmacist> findPharmacistToScore(long userId) {
        return pharmacistRepository.findPharmacistToScore(userId);
    }
}
