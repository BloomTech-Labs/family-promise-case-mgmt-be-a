package com.bloomtechlabs.fp.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name ="clients")
public class Clients {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name="id", insertable = false, updatable = false, nullable = false)
        private UUID id;

        //why is this a biginteger?
        @Column(name = "household_id")
        private UUID householdId;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;


        //schema specifies that this variable is a
        // "varchar(9)"
        @Column(name = "ssn")
        private String ssn;

        @Column(name = "is_hoh")
        private boolean isHoh;

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

        public Clients() {
        }

        public Clients(UUID id, UUID householdId, String firstName, String lastName,
                String ssn, boolean isHoh, String relation, String educationLevel, UUID genderId,
                UUID raceId, UUID ethnicityId, UUID financesId, UUID insuranceId, UUID documentsId,
                UUID goalsId, Date createdAt, UUID disabilitiesId) {

            this.id = id;
            this.householdId = householdId;
            this.lastName = lastName;
            this.ssn = ssn;
            this.isHoh = isHoh;
            this.relation = relation;
            this.educationLevel = educationLevel;
            this.genderId = genderId;
            this.raceId = raceId;
            this.ethnicityId = ethnicityId;
            this.financesId = financesId;
            this.insuranceId = insuranceId;
            this.documentsId = documentsId;
            this.goalsId = goalsId;
            this.createdAt = createdAt;
            this.disabilitiesId = disabilitiesId;

        }

        //Getters and Setter for every field

        public UUID getId() {
                return id;
        }

        public void setId(UUID id) {
                this.id = id;
        }

        public UUID getHouseholdId() {
                return householdId;
        }

        public void setHouseholdId(UUID householdId) {
                this.householdId = householdId;
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

        public String getSsn() {
                return ssn;
        }

        public void setSsn(String ssn) {
                this.ssn = ssn;
        }

        public boolean getHoh() {
                return isHoh;
        }

        public void setHoh(boolean hoh) {
                isHoh = hoh;
        }

        public String getRelation() {
                return relation;
        }

        public void setRelation(String relation) {
                this.relation = relation;
        }

        public String getEducationLevel() {
                return educationLevel;
        }

        public void setEducationLevel(String educationLevel) {
                this.educationLevel = educationLevel;
        }

        public UUID getGenderId() {
                return genderId;
        }

        public void setGenderId(UUID genderId) {
                this.genderId = genderId;
        }

        public UUID getRaceId() {
                return raceId;
        }

        public void setRaceId(UUID raceId) {
                this.raceId = raceId;
        }

        public UUID getEthnicityId() {
                return ethnicityId;
        }

        public void setEthnicityId(UUID ethnicityId) {
                this.ethnicityId = ethnicityId;
        }

        public UUID getFinancesId() {
                return financesId;
        }

        public void setFinancesId(UUID financesId) {
                this.financesId = financesId;
        }

        public UUID getInsuranceId() {
                return insuranceId;
        }

        public void setInsuranceId(UUID insuranceId) {
                this.insuranceId = insuranceId;
        }

        public UUID getDocumentsId() {
                return documentsId;
        }

        public void setDocumentsId(UUID documentsId) {
                this.documentsId = documentsId;
        }

        public UUID getGoalsId() {
                return goalsId;
        }

        public void setGoalsId(UUID goalsId) {
                this.goalsId = goalsId;
        }

        public Date getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
                this.createdAt = createdAt;
        }

        public UUID getDisabilitiesId() {
                return disabilitiesId;
        }

        public void setDisabilitiesId(UUID disabilitiesId) {
                this.disabilitiesId = disabilitiesId;
        }
}
