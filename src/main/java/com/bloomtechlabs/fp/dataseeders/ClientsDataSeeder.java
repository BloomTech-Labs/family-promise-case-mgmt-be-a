package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.Client;
import com.bloomtechlabs.fp.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class ClientsDataSeeder implements CommandLineRunner {
    @Autowired
    ClientService clientService;


    @Override
    public void run(String... args) throws Exception {
        loadClientsData();
    }

    private void loadClientsData() {
        if(clientService.count() == 0) {
            //Construct a sample client
            Calendar cal1 = Calendar.getInstance();
            cal1.set(2004, Calendar.DECEMBER, 31);
            Date date1 = cal1.getTime();

            Client client1 = Client.builder()
                    .withId(UUID.randomUUID()).withHouseholdId(BigInteger.ONE).withFirstName("Tim")
                    .withLastName("Hill").withSsn("111231234").withIsHoh(false).withRelation("Example")
                    .withEducationLevel("GED").withGenderId(UUID.randomUUID()).withRaceId(UUID.randomUUID())
                    .withEthnicityId(UUID.randomUUID()).withFinancesId(UUID.randomUUID())
                    .withInsuranceId(UUID.randomUUID()).withDocumentsId(UUID.randomUUID())
                    .withGoalsId(UUID.randomUUID()).withCreatedAt(date1).withDisabilitiesId(UUID.randomUUID())
                    .build();

            clientService.createClient(client1);

            System.out.println("added " + clientService.count() +
                    " records to the clients table.");
        } else {
            System.out.println("There are already " + clientService.count() +
                    " records in the clients table.");
        }
    }
}

