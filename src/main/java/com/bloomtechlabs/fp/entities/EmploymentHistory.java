package com.bloomtechlabs.fp.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name ="employment_histories")
@JsonDeserialize(builder = EmploymentHistory.Builder.class)
public class EmploymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "currently_employed")
    private Boolean currentlyEmployed;

    @Column(name = "skill_certifications")
    private String skillCertifications;

    public EmploymentHistory() {}

    private EmploymentHistory(Builder builder) {
        this.id = builder.id;
        this.clientId = builder.clientId;
        this.currentlyEmployed = builder.currentlyEmployed;
        this.skillCertifications = builder.skillCertifications;
    }


    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public Boolean getCurrentlyEmployed() {
        return currentlyEmployed;
    }

    public String getSkillCertifications() {
        return skillCertifications;
    }

    @JsonPOJOBuilder
    public static class Builder {
        private UUID id;
        private UUID clientId;
        private Boolean currentlyEmployed;
        private String skillCertifications;

        private Builder() {}

        private Builder(EmploymentHistory history) {
            this.id = history.id;
            this.clientId = history.clientId;
            this.currentlyEmployed = history.currentlyEmployed;
            this.skillCertifications = history.skillCertifications;
        }

        /**
         * @deprecated only used for setting data
         * @param id
         * @return
         */
        @Deprecated
        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withClientId(UUID clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withCurrentlyEmployed(Boolean currentlyEmployed) {
            this.currentlyEmployed = currentlyEmployed;
            return this;
        }

        public Builder withSkillCertifications(String skillCertifications) {
            this.skillCertifications = skillCertifications;
            return this;
        }

        public EmploymentHistory build() {
            return new EmploymentHistory(this);
        }
    }
}
