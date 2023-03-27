package com.bloomtechlabs.fp.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link com.bloomtechlabs.fp.repositories.InsuranceRepository InsuranceRepository},
 * {@link com.bloomtechlabs.fp.services.InsuranceService InsuranceService},
 * {@link com.bloomtechlabs.fp.dataseeders.InsuranceDataSeeder InsuranceDataSeeder}
 *
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
 * @see com.bloomtechlabs.fp.entities.ClientNotes
 * @see Client
 * @see com.bloomtechlabs.fp.entities.ContactPreferences
 * @see Disability
 * @see Document
 * @see EmailAddress
 * @see Ethnicity
 * @see com.bloomtechlabs.fp.entities.Finances
 * @see Gender
 * @see Location
 * @see PhoneNumber
 * @see Profile
 * @see Race
 * @see Reference
 *
 */
@Deprecated
@Entity
@Table(name = "insurance")
@JsonDeserialize(builder = Insurance.Builder.class)
public class Insurance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "client_id")
	private UUID clientId;

	@Column(name = "medicare_number")
	private String medicareNumber;

	@Column(name = "medicare_effective_date")
	private Date medicareEffectiveDate;

	@Column(name = "medicaid_number")
	private String medicaidNumber;

	@Column(name = "medicaid_effective_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date medicaidEffectiveDate;

	@Column(name = "medicaid_expiration_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date medicaidExpirationDate;

	@Column(name = "private_insurance_company")
	private String privateInsuranceCompany;

	@Column(name = "private_insurance_group_number")
	private Long privateInsuranceGroupNumber;

	@Column(name = "private_insurance_subscriber_number")
	private String privateInsuranceSubscriberNumber;

	@Column(name = "private_insurance_effective_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date privateInsuranceEffectiveDate;

	@Column(name = "private_insurance_expiration_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date privateInsuranceExpirationDate;

	@Column(name = "other_coverage")
	private String otherCoverage;

	@Column(name = "other_agencies")
	private String otherAgencies;

	public Insurance() {}
	private Insurance(Builder builder) {
		this.id = builder.id;
		this.clientId = builder.clientId;
		this.medicareNumber = builder.medicareNumber;
		this.medicareEffectiveDate = builder.medicareEffectiveDate;
		this.medicaidNumber = builder.medicaidNumber;
		this.medicaidEffectiveDate = builder.medicaidEffectiveDate;
		this.medicaidExpirationDate = builder.medicaidExpirationDate;
		this.privateInsuranceCompany = builder.privateInsuranceCompany;
		this.privateInsuranceGroupNumber = builder.privateInsuranceGroupNumber;
		this.privateInsuranceSubscriberNumber = builder.privateInsuranceSubscriberNumber;
		this.privateInsuranceEffectiveDate = builder.privateInsuranceEffectiveDate;
		this.privateInsuranceExpirationDate = builder.privateInsuranceExpirationDate;
		this.otherCoverage = builder.otherCoverage;
		this.otherAgencies = builder.otherAgencies;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public UUID getId() {
		return this.id;
	}

	public UUID getClientId() {
		return this.clientId;
	}

	public String getMedicareNumber() {
		return this.medicareNumber;
	}

	public Date getMedicareEffectiveDate() {
		return this.medicareEffectiveDate;
	}

	public String getMedicaidNumber() {
		return this.medicaidNumber;
	}

	public Date getMedicaidEffectiveDate() {
		return this.medicaidEffectiveDate;
	}

	public Date getMedicaidExpirationDate() {
		return this.medicaidExpirationDate;
	}

	public String getPrivateInsuranceCompany() {
		return this.privateInsuranceCompany;
	}

	public Long getPrivateInsuranceGroupNumber() {
		return this.privateInsuranceGroupNumber;
	}

	public String getPrivateInsuranceSubscriberNumber() {
		return this.privateInsuranceSubscriberNumber;
	}

	public Date getPrivateInsuranceEffectiveDate() {
		return this.privateInsuranceEffectiveDate;
	}

	public Date getPrivateInsuranceExpirationDate() {
		return this.privateInsuranceExpirationDate;
	}

	public String getOtherCoverage() {
		return this.otherCoverage;
	}

	public String getOtherAgencies() {
		return this.otherAgencies;
	}

	@JsonPOJOBuilder
	public static class Builder {
		private UUID id;
		private UUID clientId;
		private String medicareNumber;
		private Date medicareEffectiveDate;
		private String medicaidNumber;
		private Date medicaidEffectiveDate;
		private Date medicaidExpirationDate;
		private String privateInsuranceCompany;
		private Long privateInsuranceGroupNumber;
		private String privateInsuranceSubscriberNumber;
		private Date privateInsuranceEffectiveDate;
		private Date privateInsuranceExpirationDate;
		private String otherCoverage;
		private String otherAgencies;

		private Builder() {}
		private Builder(Insurance insurance) {
			this.id = insurance.id;
			this.clientId = insurance.clientId;
			this.medicareNumber = insurance.medicareNumber;
			this.medicareEffectiveDate = insurance.medicareEffectiveDate;
			this.medicaidNumber = insurance.medicaidNumber;
			this.medicaidEffectiveDate = insurance.medicaidEffectiveDate;
			this.medicaidExpirationDate = insurance.medicaidExpirationDate;
			this.privateInsuranceCompany = insurance.privateInsuranceCompany;
			this.privateInsuranceGroupNumber = insurance.privateInsuranceGroupNumber;
			this.privateInsuranceSubscriberNumber = insurance.privateInsuranceSubscriberNumber;
			this.privateInsuranceEffectiveDate = insurance.privateInsuranceEffectiveDate;
			this.privateInsuranceExpirationDate = insurance.privateInsuranceExpirationDate;
			this.otherCoverage = insurance.otherCoverage;
			this.otherAgencies = insurance.otherAgencies;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withClientId(UUID clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder withMedicareNumber(String medicareNumber) {
			this.medicareNumber = medicareNumber;
			return this;
		}

		public Builder withMedicareEffectiveDate(Date medicareEffectiveDate) {
			this.medicareEffectiveDate = medicareEffectiveDate;
			return this;
		}

		public Builder withMedicaidNumber(String medicaidNumber) {
			this.medicaidNumber = medicaidNumber;
			return this;
		}

		public Builder withMedicaidEffectiveDate(Date medicaidEffectiveDate) {
			this.medicaidEffectiveDate = medicaidEffectiveDate;
			return this;
		}

		public Builder withMedicaidExpirationDate(Date medicaidExpirationDate) {
			this.medicaidExpirationDate = medicaidExpirationDate;
			return this;
		}

		public Builder withPrivateInsuranceCompany(String privateInsuranceCompany) {
			this.privateInsuranceCompany = privateInsuranceCompany;
			return this;
		}

		public Builder withPrivateInsuranceGroupNumber(Long privateInsuranceGroupNumber) {
			this.privateInsuranceGroupNumber = privateInsuranceGroupNumber;
			return this;
		}

		public Builder withPrivateInsuranceSubscriberNumber(String privateInsuranceSubscriberNumber) {
			this.privateInsuranceSubscriberNumber = privateInsuranceSubscriberNumber;
			return this;
		}

		public Builder withPrivateInsuranceEffectiveDate(Date privateInsuranceEffectiveDate) {
			this.privateInsuranceEffectiveDate = privateInsuranceEffectiveDate;
			return this;
		}

		public Builder withPrivateInsuranceExpirationDate(Date privateInsuranceExpirationDate) {
			this.privateInsuranceExpirationDate = privateInsuranceExpirationDate;
			return this;
		}

		public Builder withOtherCoverage(String otherCoverage) {
			this.otherCoverage = otherCoverage;
			return this;
		}

		public Builder withOtherAgencies(String otherAgencies) {
			this.otherAgencies = otherAgencies;
			return this;
		}

		public Insurance build() {
			return new Insurance(this);
		}
	}
}