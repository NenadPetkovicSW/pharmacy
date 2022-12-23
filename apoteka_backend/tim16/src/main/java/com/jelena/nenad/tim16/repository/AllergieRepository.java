package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.Allergie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergieRepository extends JpaRepository<Allergie, Long> {

    
}
