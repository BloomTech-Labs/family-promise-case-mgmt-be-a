package com.bloomtechlabs.fp.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name ="finances")
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

    public Finances(UUID clientId, String typeOfDebt, Boolean historyOfEvictions, Boolean historyOfLandlordDebt, Boolean historyOfCriminalActivity, Boolean historyOfPoorCredit, Boolean rentalHistory, BigDecimal amountOfStudentDebt, BigDecimal amountOfMedicalDebt, BigDecimal amountOfCreditCardDebt, BigDecimal amountOfAutoDebt) {
        this.id = UUID.randomUUID();
        this.clientId = clientId;
        this.typeOfDebt = typeOfDebt;
        this.historyOfEvictions = historyOfEvictions;
        this.historyOfLandlordDebt = historyOfLandlordDebt;
        this.historyOfCriminalActivity = historyOfCriminalActivity;
        this.historyOfPoorCredit = historyOfPoorCredit;
        this.rentalHistory = rentalHistory;
        this.amountOfStudentDebt = amountOfStudentDebt;
        this.amountOfMedicalDebt = amountOfMedicalDebt;
        this.amountOfCreditCardDebt = amountOfCreditCardDebt;
        this.amountOfAutoDebt = amountOfAutoDebt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getTypeOfDebt() {
        return typeOfDebt;
    }

    public void setTypeOfDebt(String typeOfDebt) {
        this.typeOfDebt = typeOfDebt;
    }

    public Boolean getHistoryOfEvictions() {
        return historyOfEvictions;
    }

    public void setHistoryOfEvictions(Boolean historyOfEvictions) {
        this.historyOfEvictions = historyOfEvictions;
    }

    public Boolean getHistoryOfLandlordDebt() {
        return historyOfLandlordDebt;
    }

    public void setHistoryOfLandlordDebt(Boolean historyOfLandlordDebt) {
        this.historyOfLandlordDebt = historyOfLandlordDebt;
    }

    public Boolean getHistoryOfCriminalActivity() {
        return historyOfCriminalActivity;
    }

    public void setHistoryOfCriminalActivity(Boolean historyOfCriminalActivity) {
        this.historyOfCriminalActivity = historyOfCriminalActivity;
    }

    public Boolean getHistoryOfPoorCredit() {
        return historyOfPoorCredit;
    }

    public void setHistoryOfPoorCredit(Boolean historyOfPoorCredit) {
        this.historyOfPoorCredit = historyOfPoorCredit;
    }

    public Boolean getRentalHistory() {
        return rentalHistory;
    }

    public void setRentalHistory(Boolean rentalHistory) {
        this.rentalHistory = rentalHistory;
    }

    public BigDecimal getAmountOfStudentDebt() {
        return amountOfStudentDebt;
    }

    public void setAmountOfStudentDebt(BigDecimal amountOfStudentDebt) {
        this.amountOfStudentDebt = amountOfStudentDebt;
    }

    public BigDecimal getAmountOfMedicalDebt() {
        return amountOfMedicalDebt;
    }

    public void setAmountOfMedicalDebt(BigDecimal amountOfMedicalDebt) {
        this.amountOfMedicalDebt = amountOfMedicalDebt;
    }

    public BigDecimal getAmountOfCreditCardDebt() {
        return amountOfCreditCardDebt;
    }

    public void setAmountOfCreditCardDebt(BigDecimal amountOfCreditCardDebt) {
        this.amountOfCreditCardDebt = amountOfCreditCardDebt;
    }

    public BigDecimal getAmountOfAutoDebt() {
        return amountOfAutoDebt;
    }

    public void setAmountOfAutoDebt(BigDecimal amountOfAutoDebt) {
        this.amountOfAutoDebt = amountOfAutoDebt;
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
}
