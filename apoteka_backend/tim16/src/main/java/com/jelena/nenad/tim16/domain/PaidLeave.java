package com.jelena.nenad.tim16.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PaidLeave {
    @Id
    private Long id;

    @Column(unique=false, nullable=false)
    private String employeeId;

    @Column(unique=false, nullable=false)
    private boolean isPharmacist;// false je dermatologist

    @Column(unique=false, nullable=false)
    private String reasoning;

    @Column(unique=false, nullable=false)
    private String response;

    @Column(unique=false, nullable=false)
    private boolean approved; //false je odbijen

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public PaidLeave(Long id, String employeeId, boolean isPharmacist, String reasoning, String response, boolean approved, boolean isNew) {
        this.id = id;
        this.employeeId = employeeId;
        this.isPharmacist = isPharmacist;
        this.reasoning = reasoning;
        this.response = response;
        this.approved = approved;
        this.isNew = isNew;
    }

    @Column(unique = false, nullable = false)
    private boolean isNew;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isPharmacist() {
        return isPharmacist;
    }

    public void setPharmacist(boolean pharmacist) {
        isPharmacist = pharmacist;
    }

    public String getReasoning() {
        return reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public PaidLeave(){}

    public void copyValues(PaidLeave p) {
        this.employeeId = p.getEmployeeId();
        this.isPharmacist = p.isPharmacist();
        this.reasoning = p.getReasoning();
        this.response = p.getResponse();
        this.approved = p.isApproved();
        this.isNew  = p.isNew();
    }

    public PaidLeave(Long id, String employeeId, boolean isPharmacist, String reasoning, String response, boolean approved) {
        this.id = id;
        this.employeeId = employeeId;
        this.isPharmacist = isPharmacist;
        this.reasoning = reasoning;
        this.response = response;
        this.approved = approved;
    }
}
