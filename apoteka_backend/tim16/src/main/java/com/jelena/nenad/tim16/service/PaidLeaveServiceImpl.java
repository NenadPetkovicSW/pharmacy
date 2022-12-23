package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Dermatologist;
import com.jelena.nenad.tim16.domain.Medication;
import com.jelena.nenad.tim16.domain.PaidLeave;
import com.jelena.nenad.tim16.domain.Pharmacist;
import com.jelena.nenad.tim16.repository.DermatologistRepository;
import com.jelena.nenad.tim16.repository.PaidLeaveRepository;
import com.jelena.nenad.tim16.repository.PharmacistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class PaidLeaveServiceImpl implements PaidLeaveService{

    @Autowired
    private PaidLeaveRepository repository;
    @Autowired
    private PharmacistService pharmRepo;
    @Autowired
    private DermatologistService dermRepo;


    @Override
    public Collection<PaidLeave> findAll() {
        Collection<PaidLeave> paidLeaves = repository.findAll();
        return paidLeaves;
    }

    @Override
    public Collection<PaidLeave> findAll(Long pharmacyId) {
        Collection<PaidLeave> paidLeaves = repository.findAll();
        Collection<PaidLeave> paidLeaveFromPharmacy = new ArrayList<>();
        for(PaidLeave p : paidLeaves){
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
    public Optional<PaidLeave> findOne(Long id) {
        Optional<PaidLeave> paidLeave = repository.findById(id);
        return paidLeave;
    }

    @Override
    public PaidLeave updateStatus(Long id, String message, boolean status) {
        Optional<PaidLeave> paidLeaveToUpdate = findOne(id);
        paidLeaveToUpdate.get().setApproved(status);
        paidLeaveToUpdate.get().setResponse(message);
        paidLeaveToUpdate.get().setNew(false);
        PaidLeave obj = repository.save(paidLeaveToUpdate.get());
        return obj;
    }

    @Override
    public PaidLeave create(PaidLeave paidLeave) throws Exception {
        if (paidLeave.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        paidLeave.setNew(true);
        paidLeave.setId(Long.valueOf(findAll().size())+1);
        PaidLeave obj = repository.save(paidLeave);
        return obj;
    }
}
