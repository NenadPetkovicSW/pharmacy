package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.DermatologistWorkTime;
import com.jelena.nenad.tim16.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM profile WHERE username = ?1 AND password = ?2", nativeQuery = true)
    User validateUser(String username, String password);

}
