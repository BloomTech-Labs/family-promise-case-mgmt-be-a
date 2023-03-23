package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.Clients;
import com.bloomtechlabs.fp.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
                Calendar startCal1 = Calendar.getInstance();
                startCal1.set(2000, Calendar.JANUARY, 1);
                Date startDate1 = startCal1.getTime();

                Calendar endCal1 = Calendar.getInstance();
                endCal1.set(2004, Calendar.DECEMBER, 31);
                Date endDate1 = endCal1.getTime();

                UUID uuid1 = UUID.randomUUID();


                //Construct a sample client
                Calendar cal1 = Calendar.getInstance();
                endCal1.set(2004, Calendar.DECEMBER, 31);
                Date date1 = cal1.getTime();

                Clients client1 = new Clients(UUID.randomUUID(), UUID.randomUUID(), "Tim", "Hill", "111231234",
                    false, "Example", "GED", UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
                        UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(),
                        UUID.randomUUID(), date1, UUID.randomUUID());

                Calendar startCal2 = Calendar.getInstance();
                startCal2.set(2003, Calendar.JANUARY, 1);
                Date startDate2 = startCal2.getTime();

                Calendar endCal2 = Calendar.getInstance();
                endCal2.set(2009, Calendar.DECEMBER, 31);
                Date endDate2 = endCal2.getTime();

                UUID uuid2 = UUID.randomUUID();

                //Construct a sample client

                Clients cleint2 = new Clients( );

                Calendar startCal3 = Calendar.getInstance();
                startCal3.set(2008, Calendar.JANUARY, 1);
                Date startDate3 = startCal3.getTime();

                Calendar endCal3 = Calendar.getInstance();
                endCal3.set(2010, Calendar.DECEMBER, 31);
                Date endDate3 = endCal3.getTime();

                //Construct a sample client

                UUID uuid3 = UUID.randomUUID();

                Clients cleint3 = new Clients( );

                clientsService.createClients(client1);

                System.out.println("added " + clientsService.count() +
                        " records to the clients table.");
            } else {
                System.out.println("There are already " + clientsService.count() +
                        " records in the clients table.");
            }
        }
    }

