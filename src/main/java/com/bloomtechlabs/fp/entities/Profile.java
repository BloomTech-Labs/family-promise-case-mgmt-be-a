package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.ProfileRepository;
import com.bloomtechlabs.fp.services.ProfileService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.Date;


/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link ProfileRepository ProfilesRepository},
 * {@link ProfileService ProfilesService},
 * {@link com.bloomtechlabs.fp.dataseeders.ProfilesDataSeeder ProfilesDataSeeder}
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
 * @see Race
 * @see Reference
 *
 */
@Deprecated
@Entity
@Table(name = "profiles")
@JsonDeserialize(builder = Profile.Builder.class)
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private String role;

	@Column(name = "created_at")
	private Date createdAt;

	public Profile() {}
	private Profile(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.email = builder.email;
		this.role = builder.role;
		this.createdAt = builder.createdAt;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder(this);
	}

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getRole() {
		return this.role;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	@JsonPOJOBuilder
	public static class Builder {
		private String id;
		private String name;
		private String email;
		private String role;
		private Date createdAt;

		private Builder() {}
		private Builder(Profile profile) {
			this.id = profile.id;
			this.name = profile.name;
			this.email = profile.email;
			this.role = profile.role;
			this.createdAt = profile.createdAt;
		}

		public Builder withId(String id) {
			this.id = id;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withRole(String role) {
			this.role = role;
			return this;
		}

		public Builder withCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Profile build() {
			return new Profile(this);
		}
	}
}