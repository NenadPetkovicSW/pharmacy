package com.jelena.nenad.tim16.service;


import com.jelena.nenad.tim16.domain.Action;

import java.util.Collection;
import java.util.Optional;

public interface ActionService {
    Collection<Action> findAll();

    Collection<Action> findAll(Long id);

    Optional<Action> findOne(Long id);

    Action create(Action action) throws Exception;

    Action update(Action action) throws Exception;

    void delete(Long id);
}
