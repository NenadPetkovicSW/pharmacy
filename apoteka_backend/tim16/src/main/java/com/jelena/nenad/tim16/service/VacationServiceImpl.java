package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.PaidLeave;
import com.jelena.nenad.tim16.domain.Pharmacist;
import com.jelena.nenad.tim16.domain.Vacation;
import com.jelena.nenad.tim16.repository.PaidLeaveRepository;
import com.jelena.nenad.tim16.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class VacationServiceImpl implements VacationService {
    @Autowired
    private VacationRepository repository;
    @Autowired
    private PharmacistService pharmRepo;
    @Autowired
    private DermatologistService dermRepo;

    @Override
    public Collection<Vacation> findAll() {
        Collection<Vacation> vacations = repository.findAll();
        return vacations;
    }

    @Override
    public Optional<Vacation> findOne(Long id) {
        Optional<Vacation> vacation = repository.findById(id);
        return vacation;
    }

    @Override
    public Vacation updateStatus(Long id, String message, boolean status) {
        Optional<Vacation> vacationToUpdate = findOne(id);
        vacationToUpdate.get().setApproved(status);
        vacationToUpdate.get().setResponse(message);
        vacationToUpdate.get().setNew(false);
        Vacation obj = repository.save(vacationToUpdate.get());

        return obj;
    }

    @Override
    public Collection<Vacation> findAll(Long pharmacyId) {
        Collection<Vacation> paidLeaves = repository.findAll();
        Collection<Vacation> paidLeaveFromPharmacy = new ArrayList<>();
        for(Vacation p : paidLeaves){
            if(p.isPharmacist()){
                Pharmacist pharmacist = pharmRepo.findOne(Long.valueOf(p.getEmployeeId())).get();
                if(pharmacist.getPharmacy() == pharmacyId){
                    paidLeaveFromPharmacy.add(p);
                }
            }else{
                //dermatologist
                Dermatologist dermatologist = dermRepo.findOne(Long.valueOf(p.getEmployeeId())).get();
                if(dermatologist.getPharmacy().contains(pharmacyId.toString())){
                    paidLeaveFromPharmacy.add(p);
                }
            }
        }
        return  paidLeaveFromPharmacy;
    }

    @Override
    public Vacation create(Vacation vacation) throws Exception {
        if (vacation.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        vacation.setId(Long.valueOf(findAll().size())+1);
        vacation.setNew(true);
        Vacation obj = repository.save(vacation);
        return obj;
    }
}
