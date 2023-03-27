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
 * {@link com.bloomtechlabs.fp.repositories.ClientNotesRepository ClientNotesRepository},
 * {@link com.bloomtechlabs.fp.services.ClientNotesService ClientNotesService},
 * {@link com.bloomtechlabs.fp.dataseeders.ClientNotesDataSeeder ClientsNotesDataSeeder}
 *
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
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
 * @see PhoneNumber
 * @see Profile
 * @see Race
 * @see Reference
 *
 */
@Deprecated
@Entity
@Table(name = "client_notes")
@JsonDeserialize(builder = ClientNotes.Builder.class)
public class ClientNotes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "client_id")
	private UUID clientId;

	@Column(name = "source_view")
	private String sourceView;

	@Column(name = "message")
	private String message;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "deleted_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;

	public ClientNotes() {}
	private ClientNotes(Builder builder) {
		this.id = builder.id;
		this.clientId = builder.clientId;
		this.sourceView = builder.sourceView;
		this.message = builder.message;
		this.createdBy = builder.createdBy;
		this.createdAt = builder.createdAt;
		this.deletedAt = builder.deletedAt;
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

	public String getSourceView() {
		return this.sourceView;
	}

	public String getMessage() {
		return this.message;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public Date getDeletedAt() {
		return this.deletedAt;
	}

	@JsonPOJOBuilder
	public static class Builder {
		private UUID id;
		private UUID clientId;
		private String sourceView;
		private String message;
		private String createdBy;
		private Date createdAt;
		private Date deletedAt;

		private Builder() {}
		private Builder(ClientNotes clientNotes) {
			this.id = clientNotes.id;
			this.clientId = clientNotes.clientId;
			this.sourceView = clientNotes.sourceView;
			this.message = clientNotes.message;
			this.createdBy = clientNotes.createdBy;
			this.createdAt = clientNotes.createdAt;
			this.deletedAt = clientNotes.deletedAt;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withClientId(UUID clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder withSourceView(String sourceView) {
			this.sourceView = sourceView;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder withCreatedBy(String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder withDeletedAt(Date deletedAt) {
			this.deletedAt = deletedAt;
			return this;
		}

		public ClientNotes build() {
			return new ClientNotes(this);
		}
	}
}