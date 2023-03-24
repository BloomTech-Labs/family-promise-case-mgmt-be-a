package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.Clients;
import com.bloomtechlabs.fp.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class ClientsDataSeeder implements CommandLineRunner{
    @Autowired
    ClientsService clientsService;


        @Override
        public void run(String... args) throws Exception {
            loadClientsData();
        }

        private void loadClientsData() {
            if(clientsService.count() == 0) {
                //Construct a sample client
                Calendar cal1 = Calendar.getInstance();
                cal1.set(2004, Calendar.DECEMBER, 31);
                Date date1 = cal1.getTime();

                Clients client1 = Clients.builder()
                        .withId(UUID.randomUUID()).withHouseholdId(BigInteger.ONE).withFirstName("Tim")
                        .withLastName("Hill").withSsn("111231234").withIsHoh(false).withRelation("Example")
                        .withEducationLevel("GED").withGenderId(UUID.randomUUID()).withRaceId(UUID.randomUUID())
                        .withEthnicityId(UUID.randomUUID()).withFinancesId(UUID.randomUUID())
                        .withInsuranceId(UUID.randomUUID()).withDocumentsId(UUID.randomUUID())
                        .withGoalsId(UUID.randomUUID()).withCreatedAt(date1).withDisabilitiesId(UUID.randomUUID())
                        .build();

                clientsService.createClient(client1);

                System.out.println("added " + clientsService.count() +
                        " records to the clients table.");
            } else {
                System.out.println("There are already " + clientsService.count() +
                        " records in the clients table.");
            }
        }
    }

