package com.bloomtechlabs.fp.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "goals")
@JsonDeserialize(builder = Goal.Builder.class)
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "goal_statement")
    private String goalStatement;

    @Column(name = "goal_steps")
    private String goalSteps;

    @Column(name = "goal_target_date")
    @Temporal(TemporalType.DATE)
    private Date goalTargetDate;

    @Column(name = "cm_task")
    private String cmTask;

    @Column(name = "date_archived")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateArchived;

    @Column(name = "notes")
    private String notes;

    @Column(name = "client_strengths")
    private String clientStrengths;

    @Column(name = "client_obstacles")
    private String clientObstacles;

    @Column(name = "progress_summary")
    private String progressSummary;

    public Goal() {}
    private Goal(Builder builder) {
        this.id = builder.id;
        this.clientId = builder.clientId;
        this.goalStatement = builder.goalStatement;
        this.goalSteps = builder.goalSteps;
        this.goalTargetDate = builder.goalTargetDate;
        this.cmTask = builder.cmTask;
        this.dateArchived = builder.dateArchived;
        this.notes = builder.notes;
        this.clientStrengths = builder.clientStrengths;
        this.clientObstacles = builder.clientObstacles;
        this.progressSummary = builder.progressSummary;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public UUID getId() {
        return id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public String getGoalStatement() {
        return goalStatement;
    }

    public String getGoalSteps() {
        return goalSteps;
    }

    public Date getGoalTargetDate() {
        return goalTargetDate;
    }

    public String getCmTask() {
        return cmTask;
    }

    public Date getDateArchived() {
        return dateArchived;
    }

    public String getNotes() {
        return notes;
    }

    public String getClientStrengths() {
        return clientStrengths;
    }

    public String getClientObstacles() {
        return clientObstacles;
    }

    public String getProgressSummary() {
        return progressSummary;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", goalStatement='" + goalStatement + '\'' +
                ", goalSteps='" + goalSteps + '\'' +
                ", goalTargetDate=" + goalTargetDate +
                ", cmTask='" + cmTask + '\'' +
                ", dateArchived=" + dateArchived +
                ", notes='" + notes + '\'' +
                ", clientStrengths='" + clientStrengths + '\'' +
                ", clientObstacles='" + clientObstacles + '\'' +
                ", progressSummary='" + progressSummary + '\'' +
                '}';
    }

    @JsonPOJOBuilder
    public static class Builder {
        private UUID id;
        private UUID clientId;
        private String goalStatement;
        private String goalSteps;
        private Date goalTargetDate;
        private String cmTask;
        private Date dateArchived;
        private String notes;
        private String clientStrengths;
        private String clientObstacles;
        private String progressSummary;

        private Builder() {}

        /**
         * toBuilder Constructor.
         * @param goal The Old Object
         */
        private Builder(Goal goal) {
            this.id = goal.id;
            this.clientId = goal.clientId;
            this.goalStatement = goal.goalStatement;
            this.goalSteps = goal.goalSteps;
            this.goalTargetDate = goal.goalTargetDate;
            this.cmTask = goal.cmTask;
            this.dateArchived = goal.dateArchived;
            this.notes = goal.notes;
            this.clientStrengths = goal.clientStrengths;
            this.clientObstacles = goal.clientObstacles;
            this.progressSummary = goal.progressSummary;
        }

        public Builder withId(UUID id) {
            if(this.id == null) {
                this.id = id;
            }
            return this;
        }

        public Builder withClientId(UUID clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withGoalStatement(String goalStatement) {
            this.goalStatement = goalStatement;
            return this;
        }

        public Builder withGoalSteps(String goalSteps) {
            this.goalSteps = goalSteps;
            return this;
        }

        public Builder withGoalTargetDate(Date goalTargetDate) {
            this.goalTargetDate = goalTargetDate;
            return this;
        }

        public Builder withCmTask(String cmTask) {
            this.cmTask = cmTask;
            return this;
        }

        public Builder withDateArchived(Date dateArchived) {
            this.dateArchived = dateArchived;
            return this;
        }

        public Builder withNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder withClientStrengths(String clientStrengths) {
            this.clientStrengths = clientStrengths;
            return this;
        }

        public Builder withClientObstacles(String clientObstacles) {
            this.clientObstacles = clientObstacles;
            return this;
        }

        public Builder withProgressSummary(String progressSummary) {
            this.progressSummary = progressSummary;
            return this;
        }

        public Goal build() {
            return new Goal(this);
        }
    }
}
