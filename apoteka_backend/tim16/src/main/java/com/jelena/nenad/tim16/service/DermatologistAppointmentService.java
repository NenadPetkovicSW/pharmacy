package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.DermatologistAppointment;

import java.util.Collection;
import java.util.Optional;

public interface DermatologistAppointmentService {
    Collection<DermatologistAppointment> findAll();

    Optional<DermatologistAppointment> findOne(Long id);

    DermatologistAppointment create(DermatologistAppointment pharmacy) throws Exception;

    DermatologistAppointment update(DermatologistAppointment pharmacy) throws Exception;

    void delete(Long id);

    Collection<DermatologistAppointment> getDerAppointmentsByPhar(long pharmacyId);

    Collection<DermatologistAppointment> getDerAppointmentsByUser(long userId);

    int makeAppointment(long idAppointment, long idUser);

    int cancelAppointment(long idAppointment);
}
