package com.bloomtechlabs.fp.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;


/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link com.bloomtechlabs.fp.repositories.FinancesRepository FinancesRepository},
 * {@link com.bloomtechlabs.fp.services.FinancesService FinancesService},
 * {@link com.bloomtechlabs.fp.dataseeders.FinancesDataSeeder FinancesDataSeeder}
 *
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
 * @see com.bloomtechlabs.fp.entities.ClientNotes
 * @see Client
 * @see com.bloomtechlabs.fp.entities.ContactPreferences
 * @see Disability
 * @see Document
 * @see EmailAddress
 * @see Ethnicity
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
@Table(name ="finances")
@JsonDeserialize(builder = Finances.Builder.class)
public class Finances {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "type_of_debt")
    private String typeOfDebt;

    @Column(name = "history_of_evictions")
    private Boolean historyOfEvictions;

    @Column(name = "history_of_landlord_debt")
    private Boolean historyOfLandlordDebt;

    @Column(name = "history_of_criminal_activity")
    private Boolean historyOfCriminalActivity;

    @Column(name = "history_of_poor_credit")
    private Boolean historyOfPoorCredit;

    @Column(name = "rental_history")
    private Boolean rentalHistory;

    @Column(name = "amount_of_student_debt")
    private BigDecimal amountOfStudentDebt;

    @Column(name = "amount_of_medical_debt")
    private BigDecimal amountOfMedicalDebt;

    @Column(name = "amount_of_credit_card_debt")
    private BigDecimal amountOfCreditCardDebt;

    @Column(name = "amount_of_auto_debt")
    private BigDecimal amountOfAutoDebt;

    public Finances() {}

    public Finances(Builder builder) {
        this.id = builder.id;
        this.clientId = builder.clientId;
        this.typeOfDebt = builder.typeOfDebt;
        this.historyOfEvictions = builder.historyOfEvictions;
        this.historyOfLandlordDebt = builder.historyOfLandlordDebt;
        this.historyOfCriminalActivity = builder.historyOfCriminalActivity;
        this.historyOfPoorCredit = builder.historyOfPoorCredit;
        this.rentalHistory = builder.rentalHistory;
        this.amountOfStudentDebt = builder.amountOfStudentDebt;
        this.amountOfMedicalDebt = builder.amountOfMedicalDebt;
        this.amountOfCreditCardDebt = builder.amountOfCreditCardDebt;
        this.amountOfAutoDebt = builder.amountOfAutoDebt;
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

    public String getTypeOfDebt() {
        return typeOfDebt;
    }

    public Boolean getHistoryOfEvictions() {
        return historyOfEvictions;
    }

    public Boolean getHistoryOfLandlordDebt() {
        return historyOfLandlordDebt;
    }

    public Boolean getHistoryOfCriminalActivity() {
        return historyOfCriminalActivity;
    }

    public Boolean getHistoryOfPoorCredit() {
        return historyOfPoorCredit;
    }

    public Boolean getRentalHistory() {
        return rentalHistory;
    }

    public BigDecimal getAmountOfStudentDebt() {
        return amountOfStudentDebt;
    }

    public BigDecimal getAmountOfMedicalDebt() {
        return amountOfMedicalDebt;
    }

    public BigDecimal getAmountOfCreditCardDebt() {
        return amountOfCreditCardDebt;
    }

    public BigDecimal getAmountOfAutoDebt() {
        return amountOfAutoDebt;
    }

    @Override
    public String toString() {
        return "Finances{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", typeOfDebt='" + typeOfDebt + '\'' +
                ", historyOfEvictions=" + historyOfEvictions +
                ", historyOfLandlordDebt=" + historyOfLandlordDebt +
                ", historyOfCriminalActivity=" + historyOfCriminalActivity +
                ", historyOfPoorCredit=" + historyOfPoorCredit +
                ", rentalHistory=" + rentalHistory +
                ", amountOfStudentDebt=" + amountOfStudentDebt +
                ", amountOfMedicalDebt=" + amountOfMedicalDebt +
                ", amountOfCreditCardDebt=" + amountOfCreditCardDebt +
                ", amountOfAutoDebt=" + amountOfAutoDebt +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {
        private UUID id;
        private UUID clientId;
        private String typeOfDebt;
        private Boolean historyOfEvictions;
        private Boolean historyOfLandlordDebt;
        private Boolean historyOfCriminalActivity;
        private Boolean historyOfPoorCredit;
        private Boolean rentalHistory;
        private BigDecimal amountOfStudentDebt;
        private BigDecimal amountOfMedicalDebt;
        private BigDecimal amountOfCreditCardDebt;
        private BigDecimal amountOfAutoDebt;

        private Builder() {}

        /**
         * toBuilder constructor.
         * @param finances The Old Object
         */
        private Builder(Finances finances) {
            this.id = finances.id;
            this.clientId = finances.clientId;
            this.typeOfDebt = finances.typeOfDebt;
            this.historyOfEvictions = finances.historyOfEvictions;
            this.historyOfLandlordDebt = finances.historyOfLandlordDebt;
            this.historyOfCriminalActivity = finances.historyOfCriminalActivity;
            this.historyOfPoorCredit = finances.historyOfPoorCredit;
            this.rentalHistory = finances.rentalHistory;
            this.amountOfStudentDebt = finances.amountOfStudentDebt;
            this.amountOfMedicalDebt = finances.amountOfMedicalDebt;
            this.amountOfCreditCardDebt = finances.amountOfCreditCardDebt;
            this.amountOfAutoDebt = finances.amountOfAutoDebt;
        }

        public Builder withId(UUID id) {
            if(this.id == null) {
                this.id = id;
            }
            return this;
        }

        public Builder withClientId(UUID clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withTypeOfDebt(String typeOfDebt) {
            this.typeOfDebt = typeOfDebt;
            return this;
        }

        public Builder withHistoryOfEvictions(Boolean historyOfEvictions) {
            this.historyOfEvictions = historyOfEvictions;
            return this;
        }

        public Builder withHistoryOfLandlordDebt(Boolean historyOfLandlordDebt) {
            this.historyOfLandlordDebt = historyOfLandlordDebt;
            return this;
        }

        public Builder withHistoryOfCriminalActivity(Boolean historyOfCriminalActivity) {
            this.historyOfCriminalActivity = historyOfCriminalActivity;
            return this;
        }

        public Builder withHistoryOfPoorCredit(Boolean historyOfPoorCredit) {
            this.historyOfPoorCredit = historyOfPoorCredit;
            return this;
        }

        public Builder withRentalHistory(Boolean rentalHistory) {
            this.rentalHistory = rentalHistory;
            return this;
        }

        public Builder withAmountOfStudentDebt(BigDecimal amountOfStudentDebt) {
            this.amountOfStudentDebt = amountOfStudentDebt;
            return this;
        }

        public Builder withAmountOfMedicalDebt(BigDecimal amountOfMedicalDebt) {
            this.amountOfMedicalDebt = amountOfMedicalDebt;
            return this;
        }

        public Builder withAmountOfCreditCardDebt(BigDecimal amountOfCreditCardDebt) {
            this.amountOfCreditCardDebt = amountOfCreditCardDebt;
            return this;
        }

        public Builder withAmountOfAutoDebt(BigDecimal amountOfAutoDebt) {
            this.amountOfAutoDebt = amountOfAutoDebt;
            return this;
        }

        public Finances build() {
            return new Finances(this);
        }
    }
}
