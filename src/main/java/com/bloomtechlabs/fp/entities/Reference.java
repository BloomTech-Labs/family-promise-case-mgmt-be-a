package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.ReferenceRepository;
import com.bloomtechlabs.fp.services.ReferenceService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link ReferenceRepository ReferencesRepository},
 * {@link ReferenceService ReferencesService},
 * {@link com.bloomtechlabs.fp.dataseeders.ReferencesDataSeeder ReferencesDataSeeder}
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
 * @see PhoneNumber
 * @see Profile
 * @see Race
 *
 */
@Deprecated
@Entity
@Table(name = "references")
@JsonDeserialize(builder = Reference.Builder.class)
public class Reference {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "client_id")
	private UUID clientId;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "cell")
	private String cell;

	@Column(name = "home")
	private String home;

	@Column(name = "work")
	private String work;

	@Column(name = "email")
	private String email;

	@Column(name = "first_meeting_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date firstMeetingDate;

	public Reference() {}
	private Reference(Builder builder) {
		this.id = builder.id;
		this.clientId = builder.clientId;
		this.name = builder.name;
		this.address = builder.address;
		this.cell = builder.cell;
		this.home = builder.home;
		this.work = builder.work;
		this.email = builder.email;
		this.firstMeetingDate = builder.firstMeetingDate;
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

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public String getCell() {
		return this.cell;
	}

	public String getHome() {
		return this.home;
	}

	public String getWork() {
		return this.work;
	}

	public String getEmail() {
		return this.email;
	}

	public Date getFirstMeetingDate() {
		return this.firstMeetingDate;
	}

	@JsonPOJOBuilder
	public static class Builder {
		private UUID id;
		private UUID clientId;
		private String name;
		private String address;
		private String cell;
		private String home;
		private String work;
		private String email;
		private Date firstMeetingDate;

		private Builder() {}
		private Builder(Reference reference) {
			this.id = reference.id;
			this.clientId = reference.clientId;
			this.name = reference.name;
			this.address = reference.address;
			this.cell = reference.cell;
			this.home = reference.home;
			this.work = reference.work;
			this.email = reference.email;
			this.firstMeetingDate = reference.firstMeetingDate;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withClientId(UUID clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withAddress(String address) {
			this.address = address;
			return this;
		}

		public Builder withCell(String cell) {
			this.cell = cell;
			return this;
		}

		public Builder withHome(String home) {
			this.home = home;
			return this;
		}

		public Builder withWork(String work) {
			this.work = work;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withFirstMeetingDate(Date firstMeetingDate) {
			this.firstMeetingDate = firstMeetingDate;
			return this;
		}

		public Reference build() {
			return new Reference(this);
		}
	}
}