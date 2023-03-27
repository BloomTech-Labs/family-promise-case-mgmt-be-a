package com.bloomtechlabs.fp.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link com.bloomtechlabs.fp.repositories.ContactPreferencesRepository ContactPreferencesRepository},
 * {@link com.bloomtechlabs.fp.services.ContactPreferencesService ContactPreferencesService},
 * {@link com.bloomtechlabs.fp.dataseeders.ContactPreferencesDataSeeder ContactPreferencesDataSeeder}
 *
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
 * @see com.bloomtechlabs.fp.entities.ClientNotes
 * @see Client
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
@Table(name = "contact_preferences")
@JsonDeserialize(builder = ContactPreferences.Builder.class)
public class ContactPreferences {

	// Not in table schema
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "client_id")
	private UUID clientId;

	@Column(name = "food_assistance")
	private Boolean FoodAssistance;

	@Column(name = "clothing_assistance")
	private Boolean clothingAssistance;

	@Column(name = "counceling_services")
	private Boolean councelingServices;

	@Column(name = "addiction_resources")
	private Boolean addictionResources;

	@Column(name = "mentor_programs")
	private Boolean mentorPrograms;

	@Column(name = "youth_services")
	private Boolean youthServices;

	@Column(name = "budgeting")
	private Boolean budgeting;

	@Column(name = "can_text_employment_opportunities")
	private Boolean canTextEmploymentOpportunities;

	@Column(name = "can_text_apartment_listings")
	private Boolean canTextApartmentListings;

	@Column(name = "can_text_career_fairs")
	private Boolean canTextCareerFairs;

	@Column(name = "can_refer_to_partners")
	private Boolean canReferToPartners;

	public ContactPreferences() {}
	private ContactPreferences(Builder builder) {
		this.clientId = builder.clientId;
		this.FoodAssistance = builder.FoodAssistance;
		this.clothingAssistance = builder.clothingAssistance;
		this.councelingServices = builder.councelingServices;
		this.addictionResources = builder.addictionResources;
		this.mentorPrograms = builder.mentorPrograms;
		this.youthServices = builder.youthServices;
		this.budgeting = builder.budgeting;
		this.canTextEmploymentOpportunities = builder.canTextEmploymentOpportunities;
		this.canTextApartmentListings = builder.canTextApartmentListings;
		this.canTextCareerFairs = builder.canTextCareerFairs;
		this.canReferToPartners = builder.canReferToPartners;
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

	public Boolean getFoodAssistance() {
		return this.FoodAssistance;
	}

	public Boolean getClothingAssistance() {
		return this.clothingAssistance;
	}

	public Boolean getCouncelingServices() {
		return this.councelingServices;
	}

	public Boolean getAddictionResources() {
		return this.addictionResources;
	}

	public Boolean getMentorPrograms() {
		return this.mentorPrograms;
	}

	public Boolean getYouthServices() {
		return this.youthServices;
	}

	public Boolean getBudgeting() {
		return this.budgeting;
	}

	public Boolean getCanTextEmploymentOpportunities() {
		return this.canTextEmploymentOpportunities;
	}

	public Boolean getCanTextApartmentListings() {
		return this.canTextApartmentListings;
	}

	public Boolean getCanTextCareerFairs() {
		return this.canTextCareerFairs;
	}

	public Boolean getCanReferToPartners() {
		return this.canReferToPartners;
	}

	@JsonPOJOBuilder
	public static class Builder {
		private UUID clientId;
		private Boolean FoodAssistance;
		private Boolean clothingAssistance;
		private Boolean councelingServices;
		private Boolean addictionResources;
		private Boolean mentorPrograms;
		private Boolean youthServices;
		private Boolean budgeting;
		private Boolean canTextEmploymentOpportunities;
		private Boolean canTextApartmentListings;
		private Boolean canTextCareerFairs;
		private Boolean canReferToPartners;

		private Builder() {}
		private Builder(ContactPreferences contactPreferences) {
			this.clientId = contactPreferences.clientId;
			this.FoodAssistance = contactPreferences.FoodAssistance;
			this.clothingAssistance = contactPreferences.clothingAssistance;
			this.councelingServices = contactPreferences.councelingServices;
			this.addictionResources = contactPreferences.addictionResources;
			this.mentorPrograms = contactPreferences.mentorPrograms;
			this.youthServices = contactPreferences.youthServices;
			this.budgeting = contactPreferences.budgeting;
			this.canTextEmploymentOpportunities = contactPreferences.canTextEmploymentOpportunities;
			this.canTextApartmentListings = contactPreferences.canTextApartmentListings;
			this.canTextCareerFairs = contactPreferences.canTextCareerFairs;
			this.canReferToPartners = contactPreferences.canReferToPartners;
		}

		public Builder withClientId(UUID clientId) {
			this.clientId = clientId;
			return this;
		}

		public Builder withFoodAssistance(Boolean FoodAssistance) {
			this.FoodAssistance = FoodAssistance;
			return this;
		}

		public Builder withClothingAssistance(Boolean clothingAssistance) {
			this.clothingAssistance = clothingAssistance;
			return this;
		}

		public Builder withCouncelingServices(Boolean councelingServices) {
			this.councelingServices = councelingServices;
			return this;
		}

		public Builder withAddictionResources(Boolean addictionResources) {
			this.addictionResources = addictionResources;
			return this;
		}

		public Builder withMentorPrograms(Boolean mentorPrograms) {
			this.mentorPrograms = mentorPrograms;
			return this;
		}

		public Builder withYouthServices(Boolean youthServices) {
			this.youthServices = youthServices;
			return this;
		}

		public Builder withBudgeting(Boolean budgeting) {
			this.budgeting = budgeting;
			return this;
		}

		public Builder withCanTextEmploymentOpportunities(Boolean canTextEmploymentOpportunities) {
			this.canTextEmploymentOpportunities = canTextEmploymentOpportunities;
			return this;
		}

		public Builder withCanTextApartmentListings(Boolean canTextApartmentListings) {
			this.canTextApartmentListings = canTextApartmentListings;
			return this;
		}

		public Builder withCanTextCareerFairs(Boolean canTextCareerFairs) {
			this.canTextCareerFairs = canTextCareerFairs;
			return this;
		}

		public Builder withCanReferToPartners(Boolean canReferToPartners) {
			this.canReferToPartners = canReferToPartners;
			return this;
		}

		public ContactPreferences build() {
			return new ContactPreferences(this);
		}
	}
}