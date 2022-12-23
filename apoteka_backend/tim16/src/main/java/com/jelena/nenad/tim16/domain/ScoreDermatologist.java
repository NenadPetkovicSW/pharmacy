package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ScoreDermatologist {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false, nullable = false)
    private Long idDermatologist;
    @Column(unique = false, nullable = false)
    private Long idUser;
    @Column(unique = false, nullable = false)
    private float score;

    public ScoreDermatologist() {
    }


    public ScoreDermatologist(Long id, Long idDermatologist, Long idUser, float averageScore) {
        this.id = id;
        this.idDermatologist = idDermatologist;
        this.idUser = idUser;
        this.score = averageScore;
    }

    public ScoreDermatologist(Long idDermatologist, Long idUser, float averageScore) {
        this.idDermatologist = idDermatologist;
        this.idUser = idUser;
        this.score = averageScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDermatologist() {
        return idDermatologist;
    }

    public void setIdDermatologist(Long idDermatologist) {
        this.idDermatologist = idDermatologist;
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
        ScoreDermatologist that = (ScoreDermatologist) o;
        return Float.compare(that.score, score) == 0 && Objects.equals(id, that.id) && Objects.equals(idDermatologist, that.idDermatologist) && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDermatologist, idUser, score);
    }

    @Override
    public String toString() {
        return "ScoreDermatologist{" +
                "id=" + id +
                ", idDermatologist=" + idDermatologist +
                ", idUser=" + idUser +
                ", averageScore=" + score +
                '}';
    }

    //T ovo bi stajalo u DTO
    public void copyValues(ScoreDermatologist p) {
        idDermatologist = p.getIdDermatologist();
        idUser = p.getIdUser();
        score = p.getScore();
    }

}
