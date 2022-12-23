package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.DermatologistAppointment;
import com.jelena.nenad.tim16.repository.DermatologistAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

@Service
public class DermatologistAppointmentImpl implements DermatologistAppointmentService{

    @Autowired
    private DermatologistAppointmentRepository dermatologistRepository;

    @Override
    public Collection<DermatologistAppointment> findAll() {
        Collection<DermatologistAppointment> dermatologists = dermatologistRepository.findAll();
        return dermatologists;
    }

    @Override
    public Optional<DermatologistAppointment> findOne(Long id) {
        Optional<DermatologistAppointment> dermatologist = dermatologistRepository.findById(id);
        return dermatologist;
    }

    @Override
    public DermatologistAppointment create(DermatologistAppointment dermatologist) throws Exception {
        if (dermatologist.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        dermatologist.setId(Long.valueOf(findAll().size())+1);
        DermatologistAppointment dermatologistSaved = dermatologistRepository.save(dermatologist);
        return dermatologistSaved;
    }

    @Override
    public DermatologistAppointment update(DermatologistAppointment dermatologist) throws Exception {
        Optional<DermatologistAppointment> dermatologistToUpdate = findOne(dermatologist.getId());
        if (!dermatologistToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        dermatologistToUpdate.get().setDermatologistId(dermatologist.getDermatologistId());
        dermatologistToUpdate.get().setAppointmentEnd(dermatologist.getAppointmentEnd());
        dermatologistToUpdate.get().setAppointmentStart(dermatologist.getAppointmentStart());
        dermatologistToUpdate.get().setDate(dermatologist.getDate());
        dermatologistToUpdate.get().setPatientId(dermatologist.getPatientId());
        dermatologistToUpdate.get().setDone(dermatologist.isDone());
        dermatologistToUpdate.get().setPharmacyId(dermatologist.getPharmacyId());
        dermatologistToUpdate.get().setPrice(dermatologist.getPrice());
        dermatologistToUpdate.get().setDuration(dermatologist.getDuration());

        DermatologistAppointment dermatologistSaved = dermatologistRepository.save(dermatologistToUpdate.get());
        return dermatologistSaved;
    }

    @Override
    public void delete(Long id) {
        dermatologistRepository.deleteById(id);
    }

    @Override
    public Collection<DermatologistAppointment> getDerAppointmentsByPhar(long pharmacyId) {
        Collection<DermatologistAppointment> data = dermatologistRepository.getDerAppointmentsByPhar(pharmacyId);

        return data;
    }

    @Override
    public Collection<DermatologistAppointment> getDerAppointmentsByUser(long userId) {
        Collection<DermatologistAppointment> data = dermatologistRepository.getDerAppointmentsByUser(userId);

        return data;
    }

    @Override
    public int makeAppointment(long idAppointment, long idUser) {
        int da= dermatologistRepository.MakeAppointment(idAppointment,idUser);
        return da;
    }

    @Override
    public int cancelAppointment(long idAppointment) {
        int da= dermatologistRepository.CancelAppointment(idAppointment);
        return da;
    }
}
