package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.UserRoles;

import java.util.Collection;
import java.util.Optional;

public interface UserRolesService {
    Collection<UserRoles> findAll();

    Optional<UserRoles> findOne(Long id);

    UserRoles create(UserRoles userRoles) throws Exception;

    UserRoles update(UserRoles userRoles) throws Exception;

    UserRoles getUserRole(Long userId);

    void delete(Long id);

}
