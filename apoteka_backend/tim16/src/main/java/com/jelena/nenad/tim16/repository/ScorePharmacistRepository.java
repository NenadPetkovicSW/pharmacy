package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.ScoreDermatologist;
import com.jelena.nenad.tim16.domain.ScorePharmacist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface ScorePharmacistRepository extends JpaRepository<ScorePharmacist, Long> {
    @Query(value = "SELECT * FROM score_pharmacist WHERE score_pharmacist.id_user=?1 and score_pharmacist.id_pharmacist = ?2", nativeQuery = true)
    Collection<ScorePharmacist> getScoresByUserAndPharmacist(long idUser, long idPharmacist);

}
