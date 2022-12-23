package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.User;
import com.jelena.nenad.tim16.domain.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    @Query(value = "SELECT * FROM user_roles WHERE user_id = ?1", nativeQuery = true)
    UserRoles getUserRole(long userId);
}
