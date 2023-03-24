package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.Goal;
import com.bloomtechlabs.fp.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class GoalDataSeeder implements CommandLineRunner {
    @Autowired
    GoalService goalService;

    @Override
    public void run(String... args) throws Exception {
        loadGoalData();
    }

    private void loadGoalData() {
        if(goalService.count() == 0) {
            Calendar goalTargetDate1 = Calendar.getInstance();
            goalTargetDate1.set(2000, Calendar.JANUARY, 1);
            Date targetDate1 = goalTargetDate1.getTime();

            Calendar dateArchived1 = Calendar.getInstance();
            dateArchived1.set(2004, Calendar.DECEMBER, 31);
            Date archiveDate1 = dateArchived1.getTime();

            Goal goal1 = Goal.builder()
                    .withClientId(UUID.randomUUID())
                    .withGoalStatement("do 1 push up, do more push ups")
                    .withGoalSteps("do 1 push up, do more push ups")
                    .withGoalTargetDate(targetDate1)
                    .withCmTask("cmTask A")
                    .withDateArchived(archiveDate1)
                    .withNotes("get in shape with push ups")
                    .withClientStrengths("legs, never skip leg day")
                    .withClientObstacles("one arm")
                    .withProgressSummary("10 consecutive push ups")
                    .build();


            Calendar goalTargetDate2 = Calendar.getInstance();
            goalTargetDate2.set(1984, Calendar.AUGUST, 27);
            Date targetDate2 = goalTargetDate2.getTime();

            Calendar dateArchived2 = Calendar.getInstance();
            dateArchived2.set(1980, Calendar.DECEMBER, 10);
            Date archiveDate2 = dateArchived2.getTime();

            Goal goal2 = Goal.builder()
                    .withClientId(UUID.randomUUID())
                    .withGoalStatement("Promotion to McDonald's Senior French Fryer")
                    .withGoalSteps("practice with the fry machine")
                    .withGoalTargetDate(targetDate2)
                    .withCmTask("cmTask B")
                    .withDateArchived(archiveDate2)
                    .withNotes("be careful with the hot oil")
                    .withClientStrengths("3 years of experience")
                    .withClientObstacles("walking to work")
                    .withProgressSummary("1/2 way there")
                    .build();

            Calendar goalTargetDate3 = Calendar.getInstance();
            goalTargetDate3.set(2009, Calendar.JANUARY, 5);
            Date targetDate3 = goalTargetDate3.getTime();

            Calendar dateArchived3 = Calendar.getInstance();
            dateArchived3.set(2012, Calendar.SEPTEMBER, 18);
            Date archiveDate3 = dateArchived3.getTime();

            Goal goal3 = Goal.builder()
                    .withClientId(UUID.randomUUID())
                    .withGoalStatement("Start a house painting business")
                    .withGoalSteps("buying paint, posting flyers")
                    .withGoalTargetDate(targetDate3)
                    .withCmTask("cmTask C")
                    .withDateArchived(archiveDate3)
                    .withNotes("paint is pricey")
                    .withClientStrengths("wall painting")
                    .withClientObstacles("no brushes, height; need ladders")
                    .withProgressSummary("finger painted one house")
                    .build();

            this.goalService.createGoal(goal1);
            this.goalService.createGoal(goal2);
            this.goalService.createGoal(goal3);

            System.out.println("added " + this.goalService.count() +
                    " records to the goals table.");

        } else {
            System.out.println("There are already " + this.goalService.count() +
                    " records in the goals table.");
        }


    }
}
