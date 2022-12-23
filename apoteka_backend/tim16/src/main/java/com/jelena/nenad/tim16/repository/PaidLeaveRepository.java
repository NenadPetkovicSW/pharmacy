package com.jelena.nenad.tim16.repository;


import com.jelena.nenad.tim16.domain.Action;
import com.jelena.nenad.tim16.domain.PaidLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaidLeaveRepository extends JpaRepository<PaidLeave, Long> {


}
