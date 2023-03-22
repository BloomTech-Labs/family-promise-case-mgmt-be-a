package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.Finances;
import com.bloomtechlabs.fp.services.FinancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

            UUID uuidClient1 = UUID.randomUUID();

            Finances finances1 = new Finances(
                    uuidClient1,
                    "Credit card",
                    true,
                    true,
                    false,
                    true,
                    true,
                    BigDecimal.valueOf(10000),
                    BigDecimal.valueOf(1000),
                    BigDecimal.valueOf(20000),
                    BigDecimal.valueOf(1000)
                    );

            financesService.createFinances(finances1);

            UUID uuidClient2 = UUID.randomUUID();

            Finances finances2 = new Finances(
                    uuidClient2,
                    "Personal Loans",
                    false,
                    true,
                    true,
                    true,
                    false,
                    BigDecimal.valueOf(0),
                    BigDecimal.valueOf(10000),
                    BigDecimal.valueOf(5000),
                    BigDecimal.valueOf(7000)
                    );

            financesService.createFinances(finances2);

            UUID uuidClient3 = UUID.randomUUID();

            Finances finances3 = new Finances(
                    uuidClient3,
                    "Payday Loans",
                    true,
                    false,
                    true,
                    false,
                    false,
                    BigDecimal.valueOf(500),
                    BigDecimal.valueOf(700),
                    BigDecimal.valueOf(1000),
                    BigDecimal.valueOf(5500)
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
