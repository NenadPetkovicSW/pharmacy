package com.jelena.nenad.tim16.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class UserRoles {
    @Id
    private Long id;
    @Column(unique=true, nullable=false)
    private Long userId;
    @Enumerated(EnumType.ORDINAL)
    @Column(unique=false, nullable=false)
    private Roles role;
    @Column(unique=false, nullable=true)
    private Long pharmacyId; //for admin

    public UserRoles(Long id, Long userId, Roles role, Long pharmacyId) {
        this.id = id;
        this.userId = userId;
        this.role = role;
        this.pharmacyId = pharmacyId;
    }

    public UserRoles(Long id, Long userId, Roles role) {
        this.id = id;
        this.userId = userId;
        this.role = role;
    }

    public UserRoles() {
    }

    public void setPharmacyId(Long id){
        //ovo se koristi za admine
        pharmacyId = id;
    }

    public Long getPharmacyId(){
        //ovo se koristi za admine
        return pharmacyId;
    }

    public UserRoles(Long userId, Roles role) {
        this.userId = userId;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return Objects.equals(id, userRoles.id) && Objects.equals(userId, userRoles.userId) && role == userRoles.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, role, pharmacyId);
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", userId=" + userId +
                ", role=" + role +
                ", pharmacyId=" + pharmacyId +
                '}';
    }

    public void copyValues(UserRoles p) {
        this.role = p.getRole();
        this.userId = p.getUserId();
    }
}
