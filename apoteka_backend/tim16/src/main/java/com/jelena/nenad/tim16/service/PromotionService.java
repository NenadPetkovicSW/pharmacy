package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.Promotion;

import java.util.Collection;
import java.util.Optional;

public interface PromotionService {
    Collection<Promotion> findAll();

    Collection<Promotion> findAll(Long id);

    Optional<Promotion> findOne(Long id);

    Promotion create(Promotion promotion) throws Exception;

    Promotion update(Promotion promotion) throws Exception;

    void delete(Long id);
}
