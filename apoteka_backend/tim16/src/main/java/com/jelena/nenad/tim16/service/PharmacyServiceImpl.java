package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Pharmacy;
import com.jelena.nenad.tim16.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class PharmacyServiceImpl implements PharmacyService{

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public Collection<Pharmacy> findAll() {
        Collection<Pharmacy> pharmacies = pharmacyRepository.findAll();
        return pharmacies;
    }

    @Override
    public Optional<Pharmacy> findOne(Long id) {
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(id);
        return pharmacy;
    }

    @Override
    public Pharmacy create(Pharmacy pharmacy) throws Exception {
        if (pharmacy.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        pharmacy.setId(Long.valueOf(findAll().size())+1);
        Pharmacy pharmacySaved = pharmacyRepository.save(pharmacy);
        return pharmacySaved;
    }

    @Override
    public Pharmacy update(Pharmacy pharmacy) throws Exception {
        Optional<Pharmacy> pharmacyToUpdate = findOne(pharmacy.getId());
        if (!pharmacyToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        pharmacyToUpdate.get().setAddress(pharmacy.getAddress());
        pharmacyToUpdate.get().setAverageScore(pharmacy.getAverageScore());
        pharmacyToUpdate.get().setAddress(pharmacy.getAddress());
        pharmacyToUpdate.get().setMedicationIds(pharmacy.getMedicationIds());

        Pharmacy pharmacySaved = pharmacyRepository.save(pharmacyToUpdate.get());
        return pharmacySaved;
    }

    @Override
    public void delete(Long id) {
        pharmacyRepository.deleteById(id);
    }

    @Override
    public Collection<Pharmacy> findPharmacyToScore(long userId) {
        return pharmacyRepository.findPharmacyToScore(userId);
    }


    @Override
    public Collection<Pharmacy> getFreePharmacies() {
        return pharmacyRepository.getFreePharmacist();
    }

    @Override
    public Collection<Pharmacy> getFreePharmaciesByDateAndTime(Date date, Time from, Time to) {
        return pharmacyRepository.getFreePharmacistByDateAndTime(date,from,to);
    }
}
