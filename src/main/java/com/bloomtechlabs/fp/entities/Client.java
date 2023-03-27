package com.bloomtechlabs.fp.entities;


import com.bloomtechlabs.fp.repositories.ClientRepository;
import com.bloomtechlabs.fp.services.ClientService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link ClientRepository ClientsRepository},
 * {@link ClientService ClientsService},
 * {@link com.bloomtechlabs.fp.dataseeders.ClientsDataSeeder ClientsDataSeeder}
 *
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
 * @see com.bloomtechlabs.fp.entities.ClientNotes
 * @see com.bloomtechlabs.fp.entities.ContactPreferences
 * @see Disability
 * @see Document
 * @see EmailAddress
 * @see Ethnicity
 * @see com.bloomtechlabs.fp.entities.Finances
 * @see Gender
 * @see com.bloomtechlabs.fp.entities.Insurance
 * @see Location
 * @see PhoneNumber
 * @see Profile
 * @see Race
 * @see Reference
 *
 */
@Deprecated
@Entity
@Table(name = "clients")
@JsonDeserialize(builder = Client.Builder.class)
public class Client {

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

    public Client() {}
    private Client(Builder builder) {
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
        private Builder(Client client) {
            this.id = client.id;
            this.householdId = client.householdId;
            this.firstName = client.firstName;
            this.lastName = client.lastName;
            this.ssn = client.ssn;
            this.isHoh = client.isHoh;
            this.relation = client.relation;
            this.educationLevel = client.educationLevel;
            this.genderId = client.genderId;
            this.raceId = client.raceId;
            this.ethnicityId = client.ethnicityId;
            this.financesId = client.financesId;
            this.insuranceId = client.insuranceId;
            this.documentsId = client.documentsId;
            this.goalsId = client.goalsId;
            this.createdAt = client.createdAt;
            this.disabilitiesId = client.disabilitiesId;
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

        public Client build() {
            return new Client(this);
        }
    }
}
