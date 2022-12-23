package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.DermatologistWorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DermatologistWorkTimeRepository extends JpaRepository<DermatologistWorkTime, Long> {

    @Query(value = "SELECT * FROM dermatologist_work_time WHERE dermatologist_id = ?1 AND pharmacy_id = ?2", nativeQuery = true)
    DermatologistWorkTime findWorkTime(long dermatologist_id, long pharmacy_id);
}
