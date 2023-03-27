package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.PhoneNumberRepository;
import com.bloomtechlabs.fp.services.PhoneNumberService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link PhoneNumberRepository PhoneNumbersRepository},
 * {@link PhoneNumberService PhoneNumbersService},
 * {@link com.bloomtechlabs.fp.dataseeders.PhoneNumbersDataSeeder PhoneNumbersDataSeeder}
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
 * @see com.bloomtechlabs.fp.entities.Insurance
 * @see Location
 * @see Profile
 * @see Race
 * @see Reference
 *
 */
@Deprecated
@Entity
@Table(name = "phone_numbers")
@JsonDeserialize(builder = PhoneNumber.Builder.class)
public class PhoneNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "client_id")
	private UUID clientId;

	@Column(name = "number")
	private String number;

	@Column(name = "phone_type")
	private String phoneType;

	@Column(name = "deleted_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;

	@Column(name = "created_at")
	private Date createdAt;

	public PhoneNumber() {}
	private PhoneNumber(Builder builder) {
		this.id = builder.id;
		this.clientId = builder.clientId;
		this.number = builder.number;
		this.phoneType = builder.phoneType;
		this.deletedAt = builder.deletedAt;
		this.createdAt = builder.createdAt;
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

	public String getNumber() {
		return this.number;
	}

	public String getPhoneType() {
		return this.phoneType;
	}

	public Date getDeletedAt() {
		return this.deletedAt;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	@JsonPOJOBuilder
	public static class Builder {
		private UUID id;
		private UUID clientId;
		private String number;
		private String phoneType;
		private Date deletedAt;
		private Date createdAt;

		private Builder() {}
		private Builder(PhoneNumber phoneNumber) {
			this.id = phoneNumber.id;
			this.clientId = phoneNumber.clientId;
			this.number = phoneNumber.number;
			this.phoneType = phoneNumber.phoneType;
			this.deletedAt = phoneNumber.deletedAt;
			this.createdAt = phoneNumber.createdAt;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withClientId(UUID clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder withNumber(String number) {
			this.number = number;
			return this;
		}

		public Builder withPhoneType(String phoneType) {
			this.phoneType = phoneType;
			return this;
		}

		public Builder withDeletedAt(Date deletedAt) {
			this.deletedAt = deletedAt;
			return this;
		}

		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public PhoneNumber build() {
			return new PhoneNumber(this);
		}
	}
}