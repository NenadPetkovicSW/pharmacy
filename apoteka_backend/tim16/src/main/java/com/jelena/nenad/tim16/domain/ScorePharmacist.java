package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ScorePharmacist {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false, nullable = false)
    private Long idPharmacist;
    @Column(unique = false, nullable = false)
    private Long idUser;
    @Column(unique = false, nullable = false)
    private float score;

    public ScorePharmacist() {
    }


    public ScorePharmacist(Long id, Long idDermatologist, Long idUser, float averageScore) {
        this.id = id;
        this.idPharmacist = idDermatologist;
        this.idUser = idUser;
        this.score = averageScore;
    }

    public ScorePharmacist(Long idDermatologist, Long idUser, float averageScore) {
        this.idPharmacist = idDermatologist;
        this.idUser = idUser;
        this.score = averageScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPharmacist() {
        return idPharmacist;
    }

    public void setIdPharmacist(Long idDermatologist) {
        this.idPharmacist = idDermatologist;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float averageScore) {
        this.score = averageScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScorePharmacist that = (ScorePharmacist) o;
        return Float.compare(that.score, score) == 0 && Objects.equals(id, that.id) && Objects.equals(idPharmacist, that.idPharmacist) && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPharmacist, idUser, score);
    }

    @Override
    public String toString() {
        return "ScoreDermatologist{" +
                "id=" + id +
                ", idDermatologist=" + idPharmacist +
                ", idUser=" + idUser +
                ", averageScore=" + score +
                '}';
    }

    //T ovo bi stajalo u DTO
    public void copyValues(ScorePharmacist p) {
        idPharmacist = p.getIdPharmacist();
        idUser = p.getIdUser();
        score = p.getScore();
    }

}
