package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.EmploymentHistory;
import com.bloomtechlabs.fp.services.EmploymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
            EmploymentHistory employmentHistory1 =
                    new EmploymentHistory(1l, true, "Forklift Certified");
            EmploymentHistory employmentHistory2 =
                    new EmploymentHistory(1l, false, "Food Service Certified");
            EmploymentHistory employmentHistory3 =
                    new EmploymentHistory(1l, true, "Certified Ethical Hacking Course");
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
