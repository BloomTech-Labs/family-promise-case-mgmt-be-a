package com.bloomtechlabs.fp.entities;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

/**
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
 * @see com.bloomtechlabs.fp.entities.Profiles
 * @see com.bloomtechlabs.fp.entities.Locations
 * @see com.bloomtechlabs.fp.entities.Finances
 * @see com.bloomtechlabs.fp.entities.ClientNotes
 * @see com.bloomtechlabs.fp.entities.EmailAddresses
 * @see com.bloomtechlabs.fp.entities.Genders
 * @see com.bloomtechlabs.fp.entities.PhoneNumbers
 * @see com.bloomtechlabs.fp.entities.Ethnicities
 * @see com.bloomtechlabs.fp.entities.Documents
 * @see com.bloomtechlabs.fp.entities.Races
 * @see com.bloomtechlabs.fp.entities.Insurance
 * @see com.bloomtechlabs.fp.entities.ContactPreferences
 * @see com.bloomtechlabs.fp.entities.Disabilities
 * @see com.bloomtechlabs.fp.entities.References
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link com.bloomtechlabs.fp.repositories.ClientsRepository ClientsRepository},
 * {@link com.bloomtechlabs.fp.services.ClientsService ClientsService},
 * {@link com.bloomtechlabs.fp.dataseeders.ClientsDataSeeder ClientsDataSeeder}
 */
@Deprecated
@Entity
@Table(name = "clients")
@JsonDeserialize(builder = Clients.Builder.class)
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    //why is this a biginteger?
    @Column(name = "household_id")
    private BigInteger householdId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    //schema specifies that this variable is a
    // "varchar(9)"
    @Column(name = "ssn")
    private String ssn;

    @Column(name = "is_hoh")
    private Boolean isHoh;

    @Column(name = "relation")
    private String relation;

    @Column(name = "education_level")
    private String educationLevel;

    @Column(name = "gender_id")
    private UUID genderId;

    @Column(name = "race_id")
    private UUID raceId;

    @Column(name = "ethnicity_id")
    private UUID ethnicityId;

    @Column(name = "finances_id")
    private UUID financesId;

    @Column(name = "insurance_id")
    private UUID insuranceId;

    @Column(name = "documents_id")
    private UUID documentsId;

    @Column(name = "goals_id")
    private UUID goalsId;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "disabilities_id")
    private UUID disabilitiesId;

    public Clients() {}
    private Clients(Builder builder) {
        this.id = builder.id;
        this.householdId = builder.householdId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.ssn = builder.ssn;
        this.isHoh = builder.isHoh;
        this.relation = builder.relation;
        this.educationLevel = builder.educationLevel;
        this.genderId = builder.genderId;
        this.raceId = builder.raceId;
        this.ethnicityId = builder.ethnicityId;
        this.financesId = builder.financesId;
        this.insuranceId = builder.insuranceId;
        this.documentsId = builder.documentsId;
        this.goalsId = builder.goalsId;
        this.createdAt = builder.createdAt;
        this.disabilitiesId = builder.disabilitiesId;
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

    public BigInteger getHouseholdId() {
        return householdId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public Boolean getHoh() {
        return isHoh;
    }

    public String getRelation() {
        return relation;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public UUID getGenderId() {
        return genderId;
    }

    public UUID getRaceId() {
        return raceId;
    }

    public UUID getEthnicityId() {
        return ethnicityId;
    }

    public UUID getFinancesId() {
        return financesId;
    }

    public UUID getInsuranceId() {
        return insuranceId;
    }

    public UUID getDocumentsId() {
        return documentsId;
    }

    public UUID getGoalsId() {
        return goalsId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public UUID getDisabilitiesId() {
        return disabilitiesId;
    }

    @JsonPOJOBuilder
    public static class Builder {
        private UUID id;
        private BigInteger householdId;
        private String firstName;
        private String lastName;
        private String ssn;
        private Boolean isHoh;
        private String relation;
        private String educationLevel;
        private UUID genderId;
        private UUID raceId;
        private UUID ethnicityId;
        private UUID financesId;
        private UUID insuranceId;
        private UUID documentsId;
        private UUID goalsId;
        private Date createdAt;
        private UUID disabilitiesId;

        private Builder() {}
        private Builder(Clients clients) {
            this.id = clients.id;
            this.householdId = clients.householdId;
            this.firstName = clients.firstName;
            this.lastName = clients.lastName;
            this.ssn = clients.ssn;
            this.isHoh = clients.isHoh;
            this.relation = clients.relation;
            this.educationLevel = clients.educationLevel;
            this.genderId = clients.genderId;
            this.raceId = clients.raceId;
            this.ethnicityId = clients.ethnicityId;
            this.financesId = clients.financesId;
            this.insuranceId = clients.insuranceId;
            this.documentsId = clients.documentsId;
            this.goalsId = clients.goalsId;
            this.createdAt = clients.createdAt;
            this.disabilitiesId = clients.disabilitiesId;
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withHouseholdId(BigInteger householdId) {
            this.householdId = householdId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withSsn(String ssn) {
            this.ssn = ssn;
            return this;
        }

        public Builder withIsHoh(Boolean isHoh) {
            this.isHoh = isHoh;
            return this;
        }

        public Builder withRelation(String relation) {
            this.relation = relation;
            return this;
        }

        public Builder withEducationLevel(String educationLevel) {
            this.educationLevel = educationLevel;
            return this;
        }

        public Builder withGenderId(UUID genderId) {
            this.genderId = genderId;
            return this;
        }

        public Builder withRaceId(UUID raceId) {
            this.raceId = raceId;
            return this;
        }

        public Builder withEthnicityId(UUID ethnicityId) {
            this.ethnicityId = ethnicityId;
            return this;
        }

        public Builder withFinancesId(UUID financesId) {
            this.financesId = financesId;
            return this;
        }

        public Builder withInsuranceId(UUID insuranceId) {
            this.insuranceId = insuranceId;
            return this;
        }

        public Builder withDocumentsId(UUID documentsId) {
            this.documentsId = documentsId;
            return this;
        }

        public Builder withGoalsId(UUID goalsId) {
            this.goalsId = goalsId;
            return this;
        }

        public Builder withCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withDisabilitiesId(UUID disabilitiesId) {
            this.disabilitiesId = disabilitiesId;
            return this;
        }

        public Clients build() {
            return new Clients(this);
        }
    }
}
