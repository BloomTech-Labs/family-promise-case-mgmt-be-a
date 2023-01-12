package com.bloomtechlabs.fp.entities;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Insurance")
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicareNumber")
    private String medicareNumber;

    @Column(name = "medicareEffectiveDate")
    private Date medicareEffectiveDate;

    @Column(name = "medicaidNumber")
    private String medicaidNumber;

    @Column(name = "medicaidEffectiveDate")
    private Date medicaidEffectiveDate;

    @Column(name = "privateInsuranceCompany")
    private String privateInsuranceCompany;

    @Column(name = "privateInsuranceGroupNumber")
    private int privateInsuranceGroupNumber;

    @Column(name = "privateInsuranceSubscriberNumber")
    private String privateInsuranceSubscriberNumber;

    @Column(name = "privateInsuranceEffectiveDate")
    private Date privateInsuranceEffectiveDate;

    @Column(name = "privateInsuranceExpirationDate")
    private Date privateInsuranceExpirationDate;

    @Column(name = "employerName")
    private String employerName;

    @Column(name = "employeeOccupation")
    private String employeeOccupation;

    @Column(name = "otherCoverage")
    private String otherCoverage;

    @Column(name = "otherAgencies")
    private String otherAgencies;

    public Insurance(String medicareNumber, Date medicareEffectiveDate, String medicaidNumber,
                     Date medicaidEffectiveDate, String privateInsuranceCompany, int privateInsuranceGroupNumber,
                     String privateInsuranceSubscriberNumber, Date privateInsuranceEffectiveDate,
                     Date privateInsuranceExpirationDate, String employerName, String employeeOccupation,
                     String otherCoverage, String otherAgencies) {
        this.medicareNumber = medicareNumber;
        this.medicareEffectiveDate = medicareEffectiveDate;
        this.medicaidNumber = medicaidNumber;
        this.medicaidEffectiveDate = medicaidEffectiveDate;
        this.privateInsuranceCompany = privateInsuranceCompany;
        this.privateInsuranceGroupNumber = privateInsuranceGroupNumber;
        this.privateInsuranceSubscriberNumber = privateInsuranceSubscriberNumber;
        this.privateInsuranceEffectiveDate = privateInsuranceEffectiveDate;
        this.privateInsuranceExpirationDate = privateInsuranceExpirationDate;
        this.employerName = employerName;
        this.employeeOccupation = employeeOccupation;
        this.otherCoverage = otherCoverage;
        this.otherAgencies = otherAgencies;
    }

    public Long getId() {
        return id;
    }
}
