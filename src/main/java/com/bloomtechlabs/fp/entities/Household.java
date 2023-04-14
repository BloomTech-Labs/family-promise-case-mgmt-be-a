package com.bloomtechlabs.fp.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Postgresql table for households entity.
 * Attributes id & created_at are annotated to automatically generate. No setters are provided so that they cannot be changed.
 */
@Entity
@Table(name = "households")
@JsonDeserialize(builder = Household.Builder.class)
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "name")
    private String name;
    @Column(name = "times_homeless_in_three_years")
    private Integer timesHomelessInThreeYears;
    @Column(name = "total_time_homeless_three_years")
    private Integer totalTimeHomelessThreeYears;
    @Column(name = "total_time_homeless_past_year")
    private Integer totalTimeHomelessPastYear;
    @Column(name = "previously_living_in_non_human_habitation")
    private Boolean previouslyLivingInNonHumanHabitation;
    @Column(name = "previously_living_in_emergency_shelter")
    private Boolean previouslyLivingInEmergencyShelter;
    @Column(name = "previously_unsheltered")
    private Boolean previouslyUnsheltered;
    @Column(name = "previous_stay_length")
    private Integer previousStayLength;
    @Column(name = "needs_interpreter")
    private Boolean needsInterpreter;
    @Column(name = "access_to_private_transportation")
    private Boolean accessToPrivateTransportation;
    @Column(name = "client_or_family_physical_illness_history")
    private String clientOrFamilyPhysicalIllnessHistory;
    @Column(name = "client_or_family_mental_illness_history")
    private String clientOrFamilyMentalIllnessHistory;
    @Column(name = "client_or_family_personal_violence_history")
    private String clientOrFamilyPersonalViolenceHistory;
    @Column(name = "client_or_family_substance_dependency_history")
    private String clientOrFamilySubstanceDependencyHistory;
    @Column(name = "cps_involvement")
    private Boolean cpsInvolvement;
    @Column(name = "cps_involvement_active")
    private Boolean cpsInvolvementActive;
    @Column(name = "dcyf_contact_name")
    private String dcyfContactName;
    @Column(name = "dcyf_contact_email")
    private String dcyfContactEmail;
    @Column(name = "dcyf_contact_phone_number")
    private String dcyfContactPhoneNumber;
    @Column(name = "section_8_voucher_lost")
    private Boolean section8VoucherLost;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Household() {}
    private Household(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.timesHomelessInThreeYears = builder.timesHomelessInThreeYears;
        this.totalTimeHomelessThreeYears = builder.totalTimeHomelessThreeYears;
        this.totalTimeHomelessPastYear = builder.totalTimeHomelessPastYear;
        this.previouslyLivingInNonHumanHabitation = builder.previouslyLivingInNonHumanHabitation;
        this.previouslyLivingInEmergencyShelter = builder.previouslyLivingInEmergencyShelter;
        this.previouslyUnsheltered = builder.previouslyUnsheltered;
        this.previousStayLength = builder.previousStayLength;
        this.needsInterpreter = builder.needsInterpreter;
        this.accessToPrivateTransportation = builder.accessToPrivateTransportation;
        this.clientOrFamilyPhysicalIllnessHistory = builder.clientOrFamilyPhysicalIllnessHistory;
        this.clientOrFamilyMentalIllnessHistory = builder.clientOrFamilyMentalIllnessHistory;
        this.clientOrFamilyPersonalViolenceHistory = builder.clientOrFamilyPersonalViolenceHistory;
        this.clientOrFamilySubstanceDependencyHistory = builder.clientOrFamilySubstanceDependencyHistory;
        this.cpsInvolvement = builder.cpsInvolvement;
        this.cpsInvolvementActive = builder.cpsInvolvementActive;
        this.dcyfContactName = builder.dcyfContactName;
        this.dcyfContactEmail = builder.dcyfContactEmail;
        this.dcyfContactPhoneNumber = builder.dcyfContactPhoneNumber;
        this.section8VoucherLost = builder.section8VoucherLost;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTimesHomelessInThreeYears() {
        return timesHomelessInThreeYears;
    }

    public Integer getTotalTimeHomelessThreeYears() {
        return totalTimeHomelessThreeYears;
    }

    public Integer getTotalTimeHomelessPastYear() {
        return totalTimeHomelessPastYear;
    }

    public Boolean getPreviouslyLivingInNonHumanHabitation() {
        return previouslyLivingInNonHumanHabitation;
    }

    public Boolean getPreviouslyLivingInEmergencyShelter() {
        return previouslyLivingInEmergencyShelter;
    }

    public Boolean getPreviouslyUnsheltered() {
        return previouslyUnsheltered;
    }

    public Integer getPreviousStayLength() {
        return previousStayLength;
    }

    public Boolean getNeedsInterpreter() {
        return needsInterpreter;
    }

    public Boolean getAccessToPrivateTransportation() {
        return accessToPrivateTransportation;
    }

    public String getClientOrFamilyPhysicalIllnessHistory() {
        return clientOrFamilyPhysicalIllnessHistory;
    }

    public String getClientOrFamilyMentalIllnessHistory() {
        return clientOrFamilyMentalIllnessHistory;
    }

    public String getClientOrFamilyPersonalViolenceHistory() {
        return clientOrFamilyPersonalViolenceHistory;
    }

    public String getClientOrFamilySubstanceDependencyHistory() {
        return clientOrFamilySubstanceDependencyHistory;
    }

    public Boolean getCpsInvolvement() {
        return cpsInvolvement;
    }

    public Boolean getCpsInvolvementActive() {
        return cpsInvolvementActive;
    }

    public String getDcyfContactName() {
        return dcyfContactName;
    }

    public String getDcyfContactEmail() {
        return dcyfContactEmail;
    }

    public String getDcyfContactPhoneNumber() {
        return dcyfContactPhoneNumber;
    }

    public Boolean getSection8VoucherLost() {
        return section8VoucherLost;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Household household = (Household) o;
        return Objects.equals(id, household.id)
                && Objects.equals(name, household.name)
                && Objects.equals(timesHomelessInThreeYears, household.timesHomelessInThreeYears)
                && Objects.equals(totalTimeHomelessThreeYears, household.totalTimeHomelessThreeYears)
                && Objects.equals(totalTimeHomelessPastYear, household.totalTimeHomelessPastYear)
                && Objects.equals(previouslyLivingInNonHumanHabitation, household.previouslyLivingInNonHumanHabitation)
                && Objects.equals(previouslyLivingInEmergencyShelter, household.previouslyLivingInEmergencyShelter)
                && Objects.equals(previouslyUnsheltered, household.previouslyUnsheltered)
                && Objects.equals(previousStayLength, household.previousStayLength)
                && Objects.equals(needsInterpreter, household.needsInterpreter)
                && Objects.equals(accessToPrivateTransportation, household.accessToPrivateTransportation)
                && Objects.equals(clientOrFamilyPhysicalIllnessHistory, household.clientOrFamilyPhysicalIllnessHistory)
                && Objects.equals(clientOrFamilyMentalIllnessHistory, household.clientOrFamilyMentalIllnessHistory)
                && Objects.equals(clientOrFamilyPersonalViolenceHistory, household.clientOrFamilyPersonalViolenceHistory)
                && Objects.equals(clientOrFamilySubstanceDependencyHistory, household.clientOrFamilySubstanceDependencyHistory)
                && Objects.equals(cpsInvolvement, household.cpsInvolvement)
                && Objects.equals(cpsInvolvementActive, household.cpsInvolvementActive)
                && Objects.equals(dcyfContactName, household.dcyfContactName)
                && Objects.equals(dcyfContactEmail, household.dcyfContactEmail)
                && Objects.equals(dcyfContactPhoneNumber, household.dcyfContactPhoneNumber)
                && Objects.equals(section8VoucherLost, household.section8VoucherLost)
                && Objects.equals(createdAt, household.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                name,
                timesHomelessInThreeYears,
                totalTimeHomelessThreeYears,
                totalTimeHomelessPastYear,
                previouslyLivingInNonHumanHabitation,
                previouslyLivingInEmergencyShelter,
                previouslyUnsheltered,
                previousStayLength,
                needsInterpreter,
                accessToPrivateTransportation,
                clientOrFamilyPhysicalIllnessHistory,
                clientOrFamilyMentalIllnessHistory,
                clientOrFamilyPersonalViolenceHistory,
                clientOrFamilySubstanceDependencyHistory,
                cpsInvolvement,
                cpsInvolvementActive,
                dcyfContactName,
                dcyfContactEmail,
                dcyfContactPhoneNumber,
                section8VoucherLost,
                createdAt);
    }

    @Override
    public String toString() {
        return "Household{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", times_homeless_in_three_years=" + timesHomelessInThreeYears +
                ", total_time_homeless_three_years=" + totalTimeHomelessThreeYears +
                ", total_time_homeless_past_year=" + totalTimeHomelessPastYear +
                ", previously_living_in_non_human_habitation=" + previouslyLivingInNonHumanHabitation +
                ", previously_living_in_emergency_shelter=" + previouslyLivingInEmergencyShelter +
                ", previously_unsheltered=" + previouslyUnsheltered +
                ", previous_stay_length=" + previousStayLength +
                ", needs_interpreter=" + needsInterpreter +
                ", access_to_private_transportation=" + accessToPrivateTransportation +
                ", client_or_family_physical_illness_history='" + clientOrFamilyPhysicalIllnessHistory + '\'' +
                ", client_or_family_mental_illness_history='" + clientOrFamilyMentalIllnessHistory + '\'' +
                ", client_or_family_personal_violence_history='" + clientOrFamilyPersonalViolenceHistory + '\'' +
                ", client_or_family_substance_dependency_history='" + clientOrFamilySubstanceDependencyHistory + '\'' +
                ", cps_involvement=" + cpsInvolvement +
                ", cps_involvement_active=" + cpsInvolvementActive +
                ", dcyf_contact_name='" + dcyfContactName + '\'' +
                ", dcyf_contact_email='" + dcyfContactEmail + '\'' +
                ", dcyf_contact_phone_number='" + dcyfContactPhoneNumber + '\'' +
                ", section_8_voucher_lost=" + section8VoucherLost +
                ", created_at='" + createdAt + '\'' +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {
        private BigInteger id;
        private String name;
        private Integer timesHomelessInThreeYears;
        private Integer totalTimeHomelessThreeYears;
        private Integer totalTimeHomelessPastYear;
        private Boolean previouslyLivingInNonHumanHabitation;
        private Boolean previouslyLivingInEmergencyShelter;
        private Boolean previouslyUnsheltered;
        private Integer previousStayLength;
        private Boolean needsInterpreter;
        private Boolean accessToPrivateTransportation;
        private String clientOrFamilyPhysicalIllnessHistory;
        private String clientOrFamilyMentalIllnessHistory;
        private String clientOrFamilyPersonalViolenceHistory;
        private String clientOrFamilySubstanceDependencyHistory;
        private Boolean cpsInvolvement;
        private Boolean cpsInvolvementActive;
        private String dcyfContactName;
        private String dcyfContactEmail;
        private String dcyfContactPhoneNumber;
        private Boolean section8VoucherLost;

        private Builder() {}

        public Builder(Household household) {
            this.id = household.id;
            this.name = household.name;
            this.timesHomelessInThreeYears = household.timesHomelessInThreeYears;
            this.totalTimeHomelessThreeYears = household.totalTimeHomelessThreeYears;
            this.totalTimeHomelessPastYear = household.totalTimeHomelessPastYear;
            this.previouslyLivingInNonHumanHabitation = household.previouslyLivingInNonHumanHabitation;
            this.previouslyLivingInEmergencyShelter = household.previouslyLivingInEmergencyShelter;
            this.previouslyUnsheltered = household.previouslyUnsheltered;
            this.previousStayLength = household.previousStayLength;
            this.needsInterpreter = household.needsInterpreter;
            this.accessToPrivateTransportation = household.accessToPrivateTransportation;
            this.clientOrFamilyPhysicalIllnessHistory = household.clientOrFamilyPhysicalIllnessHistory;
            this.clientOrFamilyMentalIllnessHistory = household.clientOrFamilyMentalIllnessHistory;
            this.clientOrFamilyPersonalViolenceHistory = household.clientOrFamilyPersonalViolenceHistory;
            this.clientOrFamilySubstanceDependencyHistory = household.clientOrFamilySubstanceDependencyHistory;
            this.cpsInvolvement = household.cpsInvolvement;
            this.cpsInvolvementActive = household.cpsInvolvementActive;
            this.dcyfContactName = household.dcyfContactName;
            this.dcyfContactEmail = household.dcyfContactEmail;
            this.dcyfContactPhoneNumber = household.dcyfContactPhoneNumber;
            this.section8VoucherLost = household.section8VoucherLost;
        }

        /**
         * Do not include when creating a new household.
         * The id field is auto-incremented
         * @param id ID field <code>BigInteger</code>
         * @return The Builder Object
         */
        public Builder withId(BigInteger id) {
            // cannot update id field if id field is there
            if(this.id == null) {
                this.id = id;
            }
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withTimesHomelessInThreeYears(Integer timesHomelessInThreeYears) {
            this.timesHomelessInThreeYears = timesHomelessInThreeYears;
            return this;
        }

        public Builder withTotalTimeHomelessThreeYears(Integer totalTimeHomelessThreeYears) {
            this.totalTimeHomelessThreeYears = totalTimeHomelessThreeYears;
            return this;
        }

        public Builder withTotalTimeHomelessPastYear(Integer totalTimeHomelessPastYear) {
            this.totalTimeHomelessPastYear = totalTimeHomelessPastYear;
            return this;
        }

        public Builder withPreviouslyLivingInNonHumanHabitation(Boolean previouslyLivingInNonHumanHabitation) {
            this.previouslyLivingInNonHumanHabitation = previouslyLivingInNonHumanHabitation;
            return this;
        }

        public Builder withPreviouslyLivingInEmergencyShelter(Boolean previouslyLivingInEmergencyShelter) {
            this.previouslyLivingInEmergencyShelter = previouslyLivingInEmergencyShelter;
            return this;
        }

        public Builder withPreviouslyUnsheltered(Boolean previouslyUnsheltered) {
            this.previouslyUnsheltered = previouslyUnsheltered;
            return this;
        }

        public Builder withPreviousStayLength(Integer previousStayLength) {
            this.previousStayLength = previousStayLength;
            return this;
        }

        public Builder withNeedsInterpreter(Boolean needsInterpreter) {
            this.needsInterpreter = needsInterpreter;
            return this;
        }

        public Builder withAccessToPrivateTransportation(Boolean accessToPrivateTransportation) {
            this.accessToPrivateTransportation = accessToPrivateTransportation;
            return this;
        }

        public Builder withClientOrFamilyPhysicalIllnessHistory(String clientOrFamilyPhysicalIllnessHistory) {
            this.clientOrFamilyPhysicalIllnessHistory = clientOrFamilyPhysicalIllnessHistory;
            return this;
        }

        public Builder withClientOrFamilyMentalIllnessHistory(String clientOrFamilyMentalIllnessHistory) {
            this.clientOrFamilyMentalIllnessHistory = clientOrFamilyMentalIllnessHistory;
            return this;
        }

        public Builder withClientOrFamilyPersonalViolenceHistory(String clientOrFamilyPersonalViolenceHistory) {
            this.clientOrFamilyPersonalViolenceHistory = clientOrFamilyPersonalViolenceHistory;
            return this;
        }

        public Builder withClientOrFamilySubstanceDependencyHistory(String clientOrFamilySubstanceDependencyHistory) {
            this.clientOrFamilySubstanceDependencyHistory = clientOrFamilySubstanceDependencyHistory;
            return this;
        }

        public Builder withCpsInvolvement(Boolean cpsInvolvement) {
            this.cpsInvolvement = cpsInvolvement;
            return this;
        }

        public Builder withCpsInvolvementActive(Boolean cpsInvolvementActive) {
            this.cpsInvolvementActive = cpsInvolvementActive;
            return this;
        }

        public Builder withDcyfContactName(String dcyfContactName) {
            this.dcyfContactName = dcyfContactName;
            return this;
        }

        public Builder withDcyfContactEmail(String dcyfContactEmail) {
            this.dcyfContactEmail = dcyfContactEmail;
            return this;
        }

        public Builder withDcyfContactPhoneNumber(String dcyfContactPhoneNumber) {
            this.dcyfContactPhoneNumber = dcyfContactPhoneNumber;
            return this;
        }

        public Builder withSection8VoucherLost(Boolean section8VoucherLost) {
            this.section8VoucherLost = section8VoucherLost;
            return this;
        }

        public Household build() {
            return new Household(this);
        }
    }
}
