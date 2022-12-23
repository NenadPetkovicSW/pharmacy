package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.UserRoles;
import com.jelena.nenad.tim16.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserRolesServiceImpl implements UserRolesService{

    @Autowired
    private UserRolesRepository userRepository;

    @Override
    public Collection<UserRoles> findAll() {
        Collection<UserRoles> user = userRepository.findAll();
        return user;
    }

    @Override
    public Optional<UserRoles> findOne(Long id) {
        Optional<UserRoles> user = userRepository.findById(id);
        return user;
    }

    @Override
    public UserRoles create(UserRoles user) throws Exception {
        if (user.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        user.setId(Long.valueOf(findAll().size())+1);
        UserRoles pharmacySaved = userRepository.save(user);
        return pharmacySaved;
    }

    @Override
    public UserRoles update(UserRoles user) throws Exception {
        Optional<UserRoles> userToUpdate = findOne(user.getId());
        if (!userToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        userToUpdate.get().setUserId(user.getUserId());
        userToUpdate.get().setRole(user.getRole());


        UserRoles userSaved = userRepository.save(userToUpdate.get());
        return userSaved;
    }

    @Override
    public UserRoles getUserRole(Long userId) {
        return userRepository.getUserRole(userId);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
