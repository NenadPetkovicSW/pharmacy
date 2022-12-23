package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ScoreMedication {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false, nullable = false)
    private Long idMedication;
    @Column(unique = false, nullable = false)
    private Long idUser;
    @Column(unique = false, nullable = false)
    private float score;

    public ScoreMedication() {
    }

    public ScoreMedication(Long id, Long idMedication, Long idUser, float score) {
        this.id = id;
        this.idMedication = idMedication;
        this.idUser = idUser;
        this.score = score;
    }

    public ScoreMedication(Long idMedication, Long idUser, float score) {
        this.idMedication = idMedication;
        this.idUser = idUser;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMedication() {
        return idMedication;
    }

    public void setIdMedication(Long idMedication) {
        this.idMedication = idMedication;
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

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreMedication that = (ScoreMedication) o;
        return Float.compare(that.score, score) == 0 && Objects.equals(id, that.id) && Objects.equals(idMedication, that.idMedication) && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMedication, idUser, score);
    }

    @Override
    public String toString() {
        return "ScoreMedication{" +
                "id=" + id +
                ", idMedication=" + idMedication +
                ", idUser=" + idUser +
                ", score=" + score +
                '}';
    }

    //T ovo bi stajalo u DTO
    public void copyValues(ScoreMedication p) {
        idMedication = p.getIdMedication();
        idUser = p.getIdUser();
        score = p.getScore();
    }

}
