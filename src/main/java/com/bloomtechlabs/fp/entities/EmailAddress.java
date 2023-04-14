package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.EmailAddressRepository;
import com.bloomtechlabs.fp.services.EmailAddressService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link EmailAddressRepository EmailAddressesRepository},
 * {@link EmailAddressService EmailAddressesService},
 * {@link com.bloomtechlabs.fp.dataseeders.EmailAddressesDataSeeder EmailAddressesDataSeeder}
 *
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
 * @see com.bloomtechlabs.fp.entities.ClientNotes
 * @see Client
 * @see com.bloomtechlabs.fp.entities.ContactPreferences
 * @see Disability
 * @see Document
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
@Table(name = "email_addresses")
@JsonDeserialize(builder = EmailAddress.Builder.class)
public class EmailAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "client_id")
	private UUID clientId;

	@Column(name = "email")
	private String email;

	@Column(name = "email_type")
	private String emailType;

	@Column(name = "allow_sms")
	private Boolean allowSMS;

	@Column(name = "deleted_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public EmailAddress() {}
	private EmailAddress(Builder builder) {
		this.id = builder.id;
		this.clientId = builder.clientId;
		this.email = builder.email;
		this.emailType = builder.emailType;
		this.allowSMS = builder.allowSMS;
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

	public String getEmail() {
		return this.email;
	}

	public String getEmailType() {
		return this.emailType;
	}

	public Boolean getAllowSMS() {
		return this.allowSMS;
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
		private String email;
		private String emailType;
		private Boolean allowSMS;
		private Date deletedAt;
		private Date createdAt;

		private Builder() {}
		private Builder(EmailAddress emailAddress) {
			this.id = emailAddress.id;
			this.clientId = emailAddress.clientId;
			this.email = emailAddress.email;
			this.emailType = emailAddress.emailType;
			this.allowSMS = emailAddress.allowSMS;
			this.deletedAt = emailAddress.deletedAt;
			this.createdAt = emailAddress.createdAt;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withClientId(UUID clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withEmailType(String emailType) {
			this.emailType = emailType;
			return this;
		}

		public Builder withAllowSMS(Boolean allowSMS) {
			this.allowSMS = allowSMS;
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

		public EmailAddress build() {
			return new EmailAddress(this);
		}
	}
}