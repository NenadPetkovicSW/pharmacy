package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.PharmacistAppointment;
import com.jelena.nenad.tim16.repository.PharmacistAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PharmacistAppointmentImpl implements PharmacistAppointmentService{

    @Autowired
    private PharmacistAppointmentRepository pharmacistRepository;

    @Override
    public Collection<PharmacistAppointment> findAll() {
        Collection<PharmacistAppointment> pharamcistsAppointments = pharmacistRepository.findAll();
        return pharamcistsAppointments;
    }

    @Override
    public Optional<PharmacistAppointment> findOne(Long id) {
        Optional<PharmacistAppointment> pharamcistsAppointments = pharmacistRepository.findById(id);
        return pharamcistsAppointments;
    }

    @Override
    public PharmacistAppointment create(PharmacistAppointment pharamcistsAppointments) throws Exception {
        if (pharamcistsAppointments.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        pharamcistsAppointments.setId(Long.valueOf(findAll().size())+1);
        PharmacistAppointment pharmacistSaved = pharmacistRepository.save(pharamcistsAppointments);
        return pharmacistSaved;
    }

    @Override
    public PharmacistAppointment update(PharmacistAppointment pharamcistsAppointments) throws Exception {
        Optional<PharmacistAppointment> pharmacistToUpdate = findOne(pharamcistsAppointments.getId());
        if (!pharmacistToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        pharmacistToUpdate.get().setPharmacistId(pharamcistsAppointments.getPharmacistId());
        pharmacistToUpdate.get().setAppointmentEnd(pharamcistsAppointments.getAppointmentEnd());
        pharmacistToUpdate.get().setAppointmentStart(pharamcistsAppointments.getAppointmentStart());
        pharmacistToUpdate.get().setDate(pharamcistsAppointments.getDate());
        pharmacistToUpdate.get().setPatientId(pharamcistsAppointments.getPatientId());
        pharmacistToUpdate.get().setDone(pharamcistsAppointments.isDone());
        pharmacistToUpdate.get().setPharmacyId(pharamcistsAppointments.getPharmacyId());
        pharmacistToUpdate.get().setPrice(pharamcistsAppointments.getPrice());
        pharmacistToUpdate.get().setDuration(pharamcistsAppointments.getDuration());

        PharmacistAppointment dermatologistSaved = pharmacistRepository.save(pharmacistToUpdate.get());
        return dermatologistSaved;
    }

    @Override
    public void delete(Long id) {
        pharmacistRepository.deleteById(id);
    }

    @Override
    public int makeAppointment(long idAppointment, long idUser) {
        return pharmacistRepository.MakeAppointment(idAppointment,idUser);
    }

    @Override
    public int cancelAppointment(long idAppointment) {
        return pharmacistRepository.CancelAppointment(idAppointment);
    }

    @Override
    public Collection<PharmacistAppointment> getPAppointmentByPharmacist(long pharmacistId) {
        return pharmacistRepository.getPharAppointmentsByPhar(pharmacistId);
    }

    @Override
    public Collection<PharmacistAppointment> getAppointmentByUser(long patientId) {
        return pharmacistRepository.getPharAppointmentsByUser(patientId);
    }

}
