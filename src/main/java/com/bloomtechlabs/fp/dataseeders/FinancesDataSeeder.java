package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.Finances;
import com.bloomtechlabs.fp.services.FinancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FinancesDataSeeder implements CommandLineRunner {
    @Autowired
    FinancesService financesService;

    @Override
    public void run(String... args) throws Exception {
        loadFinancesData();
    }

    private void loadFinancesData() {
        if(financesService.count() == 0) {

            UUID uuid1 = UUID.randomUUID();

            Finances finances1 = new Finances(
                    uuid1,
                    );

            financesService.createFinances(finances1);

            UUID uuid2 = UUID.randomUUID();

            Finances finances2 = new Finances(
                    uuid2,
                    );

            financesService.createFinances(finances2);

            UUID uuid3 = UUID.randomUUID();

            Finances finances3 = new Finances(
                    uuid3,
                    );

            financesService.createFinances(finances3);

            System.out.println("added " + financesService.count() +
                    " records to the finances table.");

        } else {
            System.out.println("There are already " + financesService.count() +
                    " records in the finances table.");
        }


    }
}
