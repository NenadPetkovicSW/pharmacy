package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Pharmacy;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public interface PharmacyService {
    Collection<Pharmacy> findAll();

    Optional<Pharmacy> findOne(Long id);

    Pharmacy create(Pharmacy pharmacy) throws Exception;

    Pharmacy update(Pharmacy pharmacy) throws Exception;

    void delete(Long id);

    Collection<Pharmacy> findPharmacyToScore(@Param("userId") long userId);

    Collection<Pharmacy> getFreePharmacies();

    Collection<Pharmacy> getFreePharmaciesByDateAndTime(Date date, Time from, Time to);
}
