package com.bloomtechlabs.fp.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name ="education_histories")
@JsonDeserialize(builder = EducationHistory.Builder.class)
public class EducationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "level")
    private String level;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    public EducationHistory() {
    }

    private EducationHistory(Builder builder) {
        this.id = builder.id;
        this.clientId = builder.clientId;
        this.schoolName = builder.schoolName;
        this.level = builder.level;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
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

    public String getSchoolName() {
        return schoolName;
    }

    public String getLevel() {
        return level;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @JsonPOJOBuilder
    public static class Builder {
        private UUID id;
        private UUID clientId;
        private String schoolName;
        private String level;
        private Date startDate;
        private Date endDate;

        private Builder() {}
        private Builder(EducationHistory history) {
            this.id = history.id;
            this.clientId = history.clientId;
            this.schoolName = history.schoolName;
            this.level = history.level;
            this.startDate = history.startDate;
            this.endDate = history.endDate;
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withClientId(UUID clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withSchoolName(String schoolName) {
            this.schoolName = schoolName;
            return this;
        }

        public Builder withLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder withStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public EducationHistory build() {
            return new EducationHistory(this);
        }
    }
}
