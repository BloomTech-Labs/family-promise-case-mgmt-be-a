package com.bloomtechlabs.fp.entities;

import com.bloomtechlabs.fp.repositories.DocumentRepository;
import com.bloomtechlabs.fp.services.DocumentService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.*;
import java.util.UUID;

/**
 * @deprecated Note: This class has no associated table and will not work.
 * Will be handled in ticket FP-69.
 * Classes that use this class are:
 * {@link DocumentRepository DocumentsRepository},
 * {@link DocumentService DocumentsService},
 * {@link com.bloomtechlabs.fp.dataseeders.DocumentsDataSeeder DocumentsDataSeeder}
 *
 * @see <a href="https://bloomtechlabs.atlassian.net/jira/software/c/projects/FP/boards/16?modal=detail&selectedIssue=FP-69">Ticket: FP-69</a>
 * @see com.bloomtechlabs.fp.entities.ClientNotes
 * @see Client
 * @see com.bloomtechlabs.fp.entities.ContactPreferences
 * @see Disability
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
@Table(name = "documents")
@JsonDeserialize(builder = Document.Builder.class)
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private UUID id;

	@Column(name = "client_id")
	private UUID clientID;

	@Column(name = "completed_hfca")
	private Boolean completedHFCA;

	@Column(name = "valid_driver")
	private Boolean validDriver;

	@Column(name = "valid_social")
	private Boolean validSocial;

	@Column(name = "dshs_wic_ranf_snap")
	private Boolean dshsWicTanfSnap;

	@Column(name = "responsible_renters_course")
	private Boolean responsibleRentersCourse;

	@Column(name = "birth_cert_for_children")
	private Boolean birthCertForChildren;

	@Column(name = "child_enrolled_school")
	private Boolean childEnrolledSchool;

	@Column(name = "childcare")
	private Boolean childcare;

	public Document() {}
	private Document(Builder builder) {
		this.id = builder.id;
		this.clientID = builder.clientID;
		this.completedHFCA = builder.completedHFCA;
		this.validDriver = builder.validDriver;
		this.validSocial = builder.validSocial;
		this.dshsWicTanfSnap = builder.dshsWicTanfSnap;
		this.responsibleRentersCourse = builder.responsibleRentersCourse;
		this.birthCertForChildren = builder.birthCertForChildren;
		this.childEnrolledSchool = builder.childEnrolledSchool;
		this.childcare = builder.childcare;
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

	public UUID getClientID() {
		return this.clientID;
	}

	public Boolean getCompletedHFCA() {
		return this.completedHFCA;
	}

	public Boolean getValidDriver() {
		return this.validDriver;
	}

	public Boolean getValidSocial() {
		return this.validSocial;
	}

	public Boolean getDshsWicTanfSnap() {
		return this.dshsWicTanfSnap;
	}

	public Boolean getResponsibleRentersCourse() {
		return this.responsibleRentersCourse;
	}

	public Boolean getBirthCertForChildren() {
		return this.birthCertForChildren;
	}

	public Boolean getChildEnrolledSchool() {
		return this.childEnrolledSchool;
	}

	public Boolean getChildcare() {
		return this.childcare;
	}

	@JsonPOJOBuilder
	public static class Builder {
		private UUID id;
		private UUID clientID;
		private Boolean completedHFCA;
		private Boolean validDriver;
		private Boolean validSocial;
		private Boolean dshsWicTanfSnap;
		private Boolean responsibleRentersCourse;
		private Boolean birthCertForChildren;
		private Boolean childEnrolledSchool;
		private Boolean childcare;

		private Builder() {}
		private Builder(Document document) {
			this.id = document.id;
			this.clientID = document.clientID;
			this.completedHFCA = document.completedHFCA;
			this.validDriver = document.validDriver;
			this.validSocial = document.validSocial;
			this.dshsWicTanfSnap = document.dshsWicTanfSnap;
			this.responsibleRentersCourse = document.responsibleRentersCourse;
			this.birthCertForChildren = document.birthCertForChildren;
			this.childEnrolledSchool = document.childEnrolledSchool;
			this.childcare = document.childcare;
		}

		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		public Builder withClientID(UUID clientID) {
			this.clientID = clientID;
			return this;
		}

		public Builder withCompletedHFCA(Boolean completedHFCA) {
			this.completedHFCA = completedHFCA;
			return this;
		}

		public Builder withValidDriver(Boolean validDriver) {
			this.validDriver = validDriver;
			return this;
		}

		public Builder withValidSocial(Boolean validSocial) {
			this.validSocial = validSocial;
			return this;
		}

		public Builder withDshsWicTanfSnap(Boolean dshsWicTanfSnap) {
			this.dshsWicTanfSnap = dshsWicTanfSnap;
			return this;
		}

		public Builder withResponsibleRentersCourse(Boolean responsibleRentersCourse) {
			this.responsibleRentersCourse = responsibleRentersCourse;
			return this;
		}

		public Builder withBirthCertForChildren(Boolean birthCertForChildren) {
			this.birthCertForChildren = birthCertForChildren;
			return this;
		}

		public Builder withChildEnrolledSchool(Boolean childEnrolledSchool) {
			this.childEnrolledSchool = childEnrolledSchool;
			return this;
		}

		public Builder withChildcare(Boolean childcare) {
			this.childcare = childcare;
			return this;
		}

		public Document build() {
			return new Document(this);
		}
	}
}