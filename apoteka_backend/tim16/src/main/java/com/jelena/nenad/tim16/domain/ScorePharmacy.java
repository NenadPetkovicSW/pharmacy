package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ScorePharmacy {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique = false, nullable = false)
    private Long idPharmacy;
    @Column(unique = false, nullable = false)
    private Long idUser;
    @Column(unique = false, nullable = false)
    private float score;

    public ScorePharmacy() {
    }

    public ScorePharmacy(Long id, Long idMedication, Long idUser, float score) {
        this.id = id;
        this.idPharmacy = idMedication;
        this.idUser = idUser;
        this.score = score;
    }

    public ScorePharmacy(Long idMedication, Long idUser, float score) {
        this.idPharmacy = idMedication;
        this.idUser = idUser;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPharmacy() {
        return idPharmacy;
    }

    public void setIdPharmacy(Long idMedication) {
        this.idPharmacy = idMedication;
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
        ScorePharmacy that = (ScorePharmacy) o;
        return Float.compare(that.score, score) == 0 && Objects.equals(id, that.id) && Objects.equals(idPharmacy, that.idPharmacy) && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPharmacy, idUser, score);
    }

    @Override
    public String toString() {
        return "ScoreMedication{" +
                "id=" + id +
                ", idPharmacy=" + idPharmacy +
                ", idUser=" + idUser +
                ", score=" + score +
                '}';
    }

    //T ovo bi stajalo u DTO
    public void copyValues(ScorePharmacy p) {
        idPharmacy = p.getIdPharmacy();
        idUser = p.getIdUser();
        score = p.getScore();
    }

}
