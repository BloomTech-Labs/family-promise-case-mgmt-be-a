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
            Finances finances1 = Finances.builder()
                    .withClientId(UUID.randomUUID())
                    .withTypeOfDebt("Credit card")
                    .withHistoryOfEvictions(true)
                    .withHistoryOfLandlordDebt(true)
                    .withHistoryOfCriminalActivity(false)
                    .withHistoryOfPoorCredit(true)
                    .withRentalHistory(true)
                    .withAmountOfStudentDebt(BigDecimal.valueOf(10000L))
                    .withAmountOfMedicalDebt(BigDecimal.valueOf(1000L))
                    .withAmountOfCreditCardDebt(BigDecimal.valueOf(20000L))
                    .withAmountOfAutoDebt(BigDecimal.valueOf(1000))
                    .build();

            Finances finances2 = Finances.builder()
                    .withClientId(UUID.randomUUID())
                    .withTypeOfDebt("Personal Loans")
                    .withHistoryOfEvictions(false)
                    .withHistoryOfLandlordDebt(true)
                    .withHistoryOfCriminalActivity(true)
                    .withHistoryOfPoorCredit(true)
                    .withRentalHistory(false)
                    .withAmountOfStudentDebt(BigDecimal.valueOf(0L))
                    .withAmountOfMedicalDebt(BigDecimal.valueOf(10000L))
                    .withAmountOfCreditCardDebt(BigDecimal.valueOf(5000L))
                    .withAmountOfAutoDebt(BigDecimal.valueOf(7000L))
                    .build();

            Finances finances3 = Finances.builder()
                    .withClientId(UUID.randomUUID())
                    .withTypeOfDebt("Payday Loans")
                    .withHistoryOfEvictions(true)
                    .withHistoryOfLandlordDebt(false)
                    .withHistoryOfCriminalActivity(true)
                    .withHistoryOfPoorCredit(false)
                    .withRentalHistory(false)
                    .withAmountOfStudentDebt(BigDecimal.valueOf(500L))
                    .withAmountOfMedicalDebt(BigDecimal.valueOf(700L))
                    .withAmountOfCreditCardDebt(BigDecimal.valueOf(1000L))
                    .withAmountOfAutoDebt(BigDecimal.valueOf(5500L))
                    .build();

            financesService.createFinances(finances1);
            financesService.createFinances(finances2);
            financesService.createFinances(finances3);

            System.out.println("added " + financesService.count() +
                    " records to the finances table.");

        } else {
            System.out.println("There are already " + financesService.count() +
                    " records in the finances table.");
        }


    }
}
