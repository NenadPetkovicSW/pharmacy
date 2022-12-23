package com.jelena.nenad.tim16.repository;


import com.jelena.nenad.tim16.domain.ScorePharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface ScorePharmacyRepository extends JpaRepository<ScorePharmacy, Long> {
    @Query(value = "SELECT * FROM score_pharmacy WHERE score_pharmacy.id_user=?1 and score_pharmacy.id_pharmacy = ?2", nativeQuery = true)
    Collection<ScorePharmacy> getScoresByUserAndPharmacy(long idUser, long idPharmacy);

}
