package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.ScoreMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface ScoreMedcaationRepository extends JpaRepository<ScoreMedication, Long> {
    @Query(value = "SELECT * FROM score_medication WHERE score_medication.id_user=?1 and score_medication.id_medication = ?2", nativeQuery = true)
    Collection<ScoreMedication> getScoresByUserAndPMedcatio(long idUser, long idMedication);

}
