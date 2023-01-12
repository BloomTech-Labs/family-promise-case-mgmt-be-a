package com.bloomtechlabs.fp.entities;

import javax.persistence.*;

@Entity
@Table(name ="employment_histories")
public class EmploymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "currently_employed")
    private Boolean currentlyEmployed;

    @Column(name = "skill_certifications")
    private String skillCertifications;

    public EmploymentHistory() {
    }

    public EmploymentHistory(Long clientId, Boolean currentlyEmployed, String skillCertifications) {
        this.clientId            = clientId;
        this.currentlyEmployed   = currentlyEmployed;
        this.skillCertifications = skillCertifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Boolean getCurrentlyEmployed() {
        return currentlyEmployed;
    }

    public void setCurrentlyEmployed(Boolean currentlyEmployed) {
        this.currentlyEmployed = currentlyEmployed;
    }

    public String getSkillCertifications() {
        return skillCertifications;
    }

    public void setSkillCertifications(String skillCertifications) {
        this.skillCertifications = skillCertifications;
    }
}
