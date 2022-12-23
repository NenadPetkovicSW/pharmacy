package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Collection<User> findAll();

    Optional<User> findOne(Long id);

    User create(User user) throws Exception;

    User update(User user) throws Exception;

    void delete(Long id);

    User validateUser(String username, String password);
}
