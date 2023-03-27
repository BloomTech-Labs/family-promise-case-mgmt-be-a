package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.EmploymentHistory;
import com.bloomtechlabs.fp.services.EmploymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmploymentHistoryDataSeeder implements CommandLineRunner {
    @Autowired
    EmploymentHistoryService employmentHistoryService;

    @Override
    public void run(String... args) throws Exception {
        loadEmploymentHistoryData();
    }

    private void loadEmploymentHistoryData() {
        if(employmentHistoryService.count() == 0) {
            for(int i = 1; i <= 200; i++) {
                EmploymentHistory tempEmploymentHistory = EmploymentHistory.builder()
                                .withClientId(UUID.fromString(String.format("00000000-0000-0000-0000-%s", i)))
                                .withCurrentlyEmployed(i % 2 == 0)
                                .withSkillCertifications(String.format("Certification #%d", i))
                                .build();
                employmentHistoryService.createEmploymentHistory(tempEmploymentHistory);
            }

            EmploymentHistory employmentHistory1 = EmploymentHistory.builder()
                    .withClientId(UUID.randomUUID())
                    .withCurrentlyEmployed(true)
                    .withSkillCertifications("Forklift Certified")
                    .build();

            EmploymentHistory employmentHistory2 = EmploymentHistory.builder()
                    .withClientId(UUID.randomUUID())
                    .withCurrentlyEmployed(false)
                    .withSkillCertifications("Food Service Certified")
                    .build();

            EmploymentHistory employmentHistory3 = EmploymentHistory.builder()
                    .withClientId(UUID.randomUUID())
                    .withCurrentlyEmployed(true)
                    .withSkillCertifications("Certified Ethical Hacking Course")
                    .build();

            employmentHistoryService.createEmploymentHistory(employmentHistory1);
            employmentHistoryService.createEmploymentHistory(employmentHistory2);
            employmentHistoryService.createEmploymentHistory(employmentHistory3);

            System.out.println("added " + employmentHistoryService.count() +
                    " records to the employment_histories table.");
        } else {
            System.out.println("There are already " + employmentHistoryService.count() +
                    " records in the employment_histories table.");
        }
    }
}
