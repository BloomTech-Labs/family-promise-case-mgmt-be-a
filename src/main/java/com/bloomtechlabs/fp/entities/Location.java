package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.LocationRepository;
import com.bloomtechlabs.fp.services.LocationService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link LocationRepository LocationsRepository},
 * {@link LocationService LocationsService},
 * {@link com.bloomtechlabs.fp.dataseeders.LocationsDataSeeder LocationsDataSeeder}
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
 * @see PhoneNumber
 * @see Profile
 * @see Race
 * @see Reference
 *
 */
@Deprecated
@Entity
@Table(name = "locations")
@JsonDeserialize(builder = Location.Builder.class)
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "household_id")
	private BigInteger householdId;

	@Column(name = "type")
	private String type;

	@Column(name = "name")
	private String name;

	@Column(name = "latlong")
	private String latlong;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip")
	private String zip;

	@Column(name = "deleted_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	public Location() {}
	private Location(Builder builder) {
		this.id = builder.id;
		this.householdId = builder.householdId;
		this.type = builder.type;
		this.name = builder.name;
		this.latlong = builder.latlong;
		this.address1 = builder.address1;
		this.address2 = builder.address2;
		this.city = builder.city;
		this.state = builder.state;
		this.zip = builder.zip;
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

	public BigInteger getHouseholdId() {
		return this.householdId;
	}

	public String getType() {
		return this.type;
	}

	public String getName() {
		return this.name;
	}

	public String getLatlong() {
		return this.latlong;
	}

	public String getAddress1() {
		return this.address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public String getZip() {
		return this.zip;
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
		private BigInteger householdId;
		private String type;
		private String name;
		private String latlong;
		private String address1;
		private String address2;
		private String city;
		private String state;
		private String zip;
		private Date deletedAt;
		private Date createdAt;

		private Builder() {}
		private Builder(Location location) {
			this.id = location.id;
			this.householdId = location.householdId;
			this.type = location.type;
			this.name = location.name;
			this.latlong = location.latlong;
			this.address1 = location.address1;
			this.address2 = location.address2;
			this.city = location.city;
			this.state = location.state;
			this.zip = location.zip;
			this.deletedAt = location.deletedAt;
			this.createdAt = location.createdAt;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withHouseholdId(BigInteger householdId) {
			this.householdId = householdId;
			return this;
		}

		public Builder withType(String type) {
			this.type = type;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withLatlong(String latlong) {
			this.latlong = latlong;
			return this;
		}

		public Builder withAddress1(String address1) {
			this.address1 = address1;
			return this;
		}

		public Builder withAddress2(String address2) {
			this.address2 = address2;
			return this;
		}

		public Builder withCity(String city) {
			this.city = city;
			return this;
		}

		public Builder withState(String state) {
			this.state = state;
			return this;
		}

		public Builder withZip(String zip) {
			this.zip = zip;
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

		public Location build() {
			return new Location(this);
		}
	}
}