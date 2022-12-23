package com.jelena.nenad.tim16.repository;

import com.jelena.nenad.tim16.domain.ScoreDermatologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;


@Repository
public interface ScoreDermatologistRepository extends JpaRepository<ScoreDermatologist, Long> {

    @Query(value = "SELECT * FROM score_dermatologist WHERE score_dermatologist.id_user=?1", nativeQuery = true)
    Collection<ScoreDermatologist> getUserScores(long idUser);

    @Query(value = "SELECT * FROM score_dermatologist WHERE score_dermatologist.id_user=?1 and score_dermatologist.id_dermatologist = ?2", nativeQuery = true)
    Collection<ScoreDermatologist> getScoresByUserAndDermatologist(long idUser, long idDermatologist);
}
