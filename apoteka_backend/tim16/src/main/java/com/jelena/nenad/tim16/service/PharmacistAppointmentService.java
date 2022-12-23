package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.PharmacistAppointment;

import java.util.Collection;
import java.util.Optional;

public interface PharmacistAppointmentService {
    Collection<PharmacistAppointment> findAll();

    Optional<PharmacistAppointment> findOne(Long id);

    PharmacistAppointment create(PharmacistAppointment pharmacy) throws Exception;

    PharmacistAppointment update(PharmacistAppointment pharmacy) throws Exception;

    void delete(Long id);

    int makeAppointment(long idAppointment, long idUser);

    int cancelAppointment(long idAppointment);

    Collection<PharmacistAppointment> getPAppointmentByPharmacist(long pharmacistId);

    Collection<PharmacistAppointment> getAppointmentByUser(long patientId);

}
