package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.DisabilityRepository;
import com.bloomtechlabs.fp.services.DisabilityService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link DisabilityRepository DisabilitiesRepository},
 * {@link DisabilityService DisabilitiesService},
 * {@link com.bloomtechlabs.fp.dataseeders.DisabilitiesDataSeeder DisabilitiesDataSeeder}
 *
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
 * @see com.bloomtechlabs.fp.entities.ClientNotes
 * @see Client
 * @see com.bloomtechlabs.fp.entities.ContactPreferences
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
@Table(name = "disabilities")
@JsonDeserialize(builder = Disability.Builder.class)
public class Disability {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "client_id")
	private UUID clientId;

	@Column(name = "disablity")
	private String disability;

	public Disability() {}
	private Disability(Builder builder) {
		this.id = builder.id;
		this.clientId = builder.clientId;
		this.disability = builder.disability;
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

	public String getDisability() {
		return this.disability;
	}

	@JsonPOJOBuilder
	public static class Builder {
		private UUID id;
		private UUID clientId;
		private String disability;

		private Builder() {}
		private Builder(Disability disability) {
			this.id = disability.id;
			this.clientId = disability.clientId;
			this.disability = disability.disability;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withClientId(UUID clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder withDisability(String disability) {
			this.disability = disability;
			return this;
		}

		public Disability build() {
			return new Disability(this);
		}
	}
}