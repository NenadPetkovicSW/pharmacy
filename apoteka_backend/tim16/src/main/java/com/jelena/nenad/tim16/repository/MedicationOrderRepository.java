package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.MedicationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MedicationOrderRepository extends JpaRepository<MedicationOrder, Long> {
    @Query(value = "select * from medication_order where patient_id=:userId and medication_order.delivery_date>(NOW() + interval '1 day') order by delivery_date asc", nativeQuery = true)
    Collection<MedicationOrder> getOrderByUser(@Param("userId")  long userId);
}
