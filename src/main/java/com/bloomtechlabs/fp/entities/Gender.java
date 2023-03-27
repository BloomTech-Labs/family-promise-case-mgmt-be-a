package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.GenderRepository;
import com.bloomtechlabs.fp.services.GenderService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.UUID;


/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link GenderRepository GendersRepository},
 * {@link GenderService GendersService},
 * {@link com.bloomtechlabs.fp.dataseeders.GendersDataSeeder GendersDataSeeder}
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
@Table(name = "genders")
@JsonDeserialize(builder = Gender.Builder.class)
public class Gender {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "name")
	private String name;

	public Gender() {}
	private Gender(Builder builder) {
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
		private Builder(Gender gender) {
			this.id = gender.id;
			this.name = gender.name;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Gender build() {
			return new Gender(this);
		}
	}
}