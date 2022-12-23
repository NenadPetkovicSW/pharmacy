package com.jelena.nenad.tim16.domain;
import com.jelena.nenad.tim16.service.PromotionService;

import javax.persistence.*;
import java.util.Set;

/*
 * Prednosti strategije nasledjivanja gde se koristi po jedna tabela za svaki entitet:
 * - sve kolone su relevantne za svaku torku u tabeli, lakse su za razumevanje i nema bacanja prostora
 * - mapiranje modela na bazu je skoro 1 na 1 (svaka klasa ima svoju tabelu, svaki atribut ima svoju kolonu)
 * Mane strategije:
 * - da bi se ucitao objekat mora se koristiti vise tabela, sto znaci neizbeznu upotrebu JOINova
 * - roditeljska klasa moze biti usko grlo jer joj se precesto pristupa
 */

@Entity(name = "Profile")
public class User {

    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(unique=true, nullable=false)
    private String email;

    @Column(unique=false, nullable=false)
    private String password;

    @Column(unique=false, nullable=false)
    private String firstName;

    @Column(unique=false, nullable=false)
    private String lastName;

    @Column(unique=false, nullable=false)
    private String alergiesIds;

    @Column(unique = false, nullable = true)
    private String promotionsIds;

    @Column(unique = false, nullable = true)
    private String actionsIds;

    public String getPromotionsIds() {
        return promotionsIds;
    }

    public void setPromotionsIds(String promotionsIds) {
        this.promotionsIds = promotionsIds;
    }

    public void addPromotions(String promotionId) {
        if (this.promotionsIds == null) {
            this.promotionsIds = promotionId;
        } else {
            this.promotionsIds += "," + promotionId;
        }
    }

    public String getActionsIds() {
        return actionsIds;
    }

    public void setActionsIds(String actionsIds) {
        this.actionsIds = actionsIds;
    }

    public void addActions(String actionsId) {
        if (this.actionsIds == null) {
            this.actionsIds = actionsId;
        } else {
            this.actionsIds += "," + actionsId;
        }
    }

    public User() {

    }

    public User(User u) {
        this.email = u.getEmail();
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.alergiesIds = u.getAlergiesIds();
        this.actionsIds = u.getAlergiesIds();
    }

    public User(String username, String email, String password, String firstName, String lastName, String alergiesIds,
                String actionsIds, String promotionsIds) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.alergiesIds = alergiesIds;
        this.actionsIds =actionsIds;
        this.promotionsIds = promotionsIds;
    }

    public User(Long id, String username, String email, String password, String firstName,
                String actionsIds, String promotionsIds,String lastName, String alergiesIds) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.alergiesIds = alergiesIds;
        this.actionsIds =actionsIds;
        this.promotionsIds = promotionsIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAlergiesIds() {
        return alergiesIds;
    }

    public void setAlergiesIds(String alergiesIds) {
        this.alergiesIds = alergiesIds;
    }

    // ovo bi stajalo u DTO
    public void copyValues(User p) {
        this.email = p.getEmail();
        this.username = p.getUsername();
        this.password = p.getPassword();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.alergiesIds = p.getAlergiesIds();
    }

    public void copyValuesNoEmail(User p) {
        this.username = p.getUsername();
        this.password = p.getPassword();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.alergiesIds = p.getAlergiesIds();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", alergiesIds='" + alergiesIds + '\'' +
                '}';
    }
}
