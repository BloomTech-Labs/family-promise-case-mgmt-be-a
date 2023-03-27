package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.EthnicityRepository;
import com.bloomtechlabs.fp.services.EthnicityService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link EthnicityRepository EthnicitiesRepository},
 * {@link EthnicityService EthnicitiesService},
 * {@link com.bloomtechlabs.fp.dataseeders.EthnicitiesDataSeeder EthnicitiesDataSeeder}
 *
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
 * @see com.bloomtechlabs.fp.entities.ClientNotes
 * @see Client
 * @see com.bloomtechlabs.fp.entities.ContactPreferences
 * @see Disability
 * @see Document
 * @see EmailAddress
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
@Table(name = "ethnicities")
@JsonDeserialize(builder = Ethnicity.Builder.class)
public class Ethnicity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "name")
	private String name;

	public Ethnicity() {}
	private Ethnicity(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
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

	public String getName() {
		return this.name;
	}

	@JsonPOJOBuilder
	public static class Builder {
		private UUID id;
		private String name;

		private Builder() {}
		private Builder(Ethnicity ethnicity) {
			this.id = ethnicity.id;
			this.name = ethnicity.name;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Ethnicity build() {
			return new Ethnicity(this);
		}
	}
}