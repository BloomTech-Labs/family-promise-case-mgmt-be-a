package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.EducationHistory;
import com.bloomtechlabs.fp.services.EducationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class EducationHistoryDataSeeder implements CommandLineRunner {
    @Autowired
    EducationHistoryService educationHistoryService;

    @Override
    public void run(String... args) throws Exception {
        loadEducationHistoryData();
    }

    private void loadEducationHistoryData() {
        if(educationHistoryService.count() == 0) {
            Calendar startCal1 = Calendar.getInstance();
            startCal1.set(2000, Calendar.JANUARY, 1);
            Date startDate1 = startCal1.getTime();

            Calendar endCal1 = Calendar.getInstance();
            endCal1.set(2004, Calendar.DECEMBER, 31);
            Date endDate1 = endCal1.getTime();

            EducationHistory edu1 = EducationHistory.builder()
                    .withClientId(UUID.randomUUID())
                    .withSchoolName("Seeder College of Higher Education")
                    .withLevel("Bachelor's Degree")
                    .withStartDate(startDate1)
                    .withEndDate(endDate1)
                    .build();


            Calendar startCal2 = Calendar.getInstance();
            startCal2.set(2003, Calendar.JANUARY, 1);
            Date startDate2 = startCal2.getTime();

            Calendar endCal2 = Calendar.getInstance();
            endCal2.set(2009, Calendar.DECEMBER, 31);
            Date endDate2 = endCal2.getTime();

            EducationHistory edu2 = EducationHistory.builder()
                    .withClientId(UUID.randomUUID())
                    .withSchoolName("University of Mocking Bird")
                    .withLevel("Master's Degree")
                    .withStartDate(startDate2)
                    .withEndDate(endDate2)
                    .build();


            Calendar startCal3 = Calendar.getInstance();
            startCal3.set(2008, Calendar.JANUARY, 1);
            Date startDate3 = startCal3.getTime();

            Calendar endCal3 = Calendar.getInstance();
            endCal3.set(2010, Calendar.DECEMBER, 31);
            Date endDate3 = endCal3.getTime();

            EducationHistory edu3 = EducationHistory.builder()
                    .withClientId(UUID.randomUUID())
                    .withSchoolName("Mock University of Advanced Learning")
                    .withLevel("Bachelor's Degree")
                    .withStartDate(startDate3)
                    .withEndDate(endDate3)
                    .build();


            educationHistoryService.createEducationHistory(edu1);
            educationHistoryService.createEducationHistory(edu2);
            educationHistoryService.createEducationHistory(edu3);
            System.out.println("added " + educationHistoryService.count() +
                    " records to the employment_histories table.");
        } else {
            System.out.println("There are already " + educationHistoryService.count() +
                    " records in the education_histories table.");
        }
    }
}
