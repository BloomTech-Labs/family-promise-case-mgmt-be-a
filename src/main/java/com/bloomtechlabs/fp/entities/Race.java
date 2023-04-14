package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.RaceRepository;
import com.bloomtechlabs.fp.services.RaceService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link RaceRepository RacesRepository},
 * {@link RaceService RacesService},
 * {@link com.bloomtechlabs.fp.dataseeders.RacesDataSeeder RacesDataSeeder}
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
 * @see Reference
 *
 */
@Deprecated
@Entity
@Table(name = "races")
@JsonDeserialize(builder = Race.Builder.class)
public class Race {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "name")
	private String name;

	public Race() {}
	private Race(Builder builder) {
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
		private Builder(Race race) {
			this.id = race.id;
			this.name = race.name;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Race build() {
			return new Race(this);
		}
	}
}