package com.jelena.nenad.tim16.service;

import com.jelena.nenad.tim16.domain.User;
import com.jelena.nenad.tim16.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> findAll() {
        Collection<User> user = userRepository.findAll();
        return user;
    }

    @Override
    public Optional<User> findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public User create(User user) throws Exception {
        if (user.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        user.setId(Long.valueOf(findAll().size())+1);
        User pharmacySaved = userRepository.save(user);
        return pharmacySaved;
    }

    @Override
    public User update(User user) throws Exception {
        Optional<User> userToUpdate = findOne(user.getId());
        if (!userToUpdate.isPresent()) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        userToUpdate.get().setEmail(user.getEmail());
        userToUpdate.get().setFirstName(user.getFirstName());
        userToUpdate.get().setLastName(user.getLastName());
        userToUpdate.get().setPassword(user.getPassword());
        userToUpdate.get().setUsername(user.getUsername());
        userToUpdate.get().setAlergiesIds(user.getAlergiesIds());

        User userSaved = userRepository.save(userToUpdate.get());
        return userSaved;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User validateUser(String username, String password) {
        return userRepository.validateUser(username,password);
    }
}
