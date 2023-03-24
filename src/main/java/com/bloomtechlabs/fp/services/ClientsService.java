package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Clients;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientsService {



        @Autowired
        ClientsRepository clientsRepository;

        public List<Clients> getAllClients() {
            return clientsRepository.findAll();
        }

        /**
         * @param offset page index to return results from.
         * @param limit number of results to include per page.
         * @return returns a paginated list.
         */
        public Page<Clients> getAllClientsPaginated(int offset, int limit) {
            return clientsRepository.findAll(PageRequest.of(offset, limit));
        }

        public Clients createClients(Clients clients) {
            return clientsRepository.save(clients);
        }

        public ResponseEntity<Clients> getClientsById(UUID id) {
            Clients clients = findClientsById(id);

            return ResponseEntity.ok(clients);
        }

        public ResponseEntity<Clients> updateClients(UUID id, Clients clientsDetails) {
            Clients clients = findClientsById(id);

            clients.setHouseholdId(   clientsDetails.getHouseholdId());
            clients.setFirstName( clientsDetails.getFirstName());
            clients.setLastName(      clientsDetails.getLastName());
            clients.setSsn(  clientsDetails.getSsn());
            clients.setHoh(    clientsDetails.getHoh());
            clients.setRelation(    clientsDetails.getRelation());
            clients.setEducationLevel(    clientsDetails.getEducationLevel());
            clients.setGenderId(    clientsDetails.getGenderId());
            clients.setEthnicityId(    clientsDetails.getEthnicityId());
            clients.setFinancesId(    clientsDetails.getFinancesId());
            clients.setInsuranceId(    clientsDetails.getInsuranceId());
            clients.setDocumentsId(    clientsDetails.getDocumentsId());
            clients.setGoalsId(    clientsDetails.getGoalsId());
            clients.setCreatedAt(    clientsDetails.getCreatedAt());
            clients.setDisabilitiesId(    clientsDetails.getDisabilitiesId());



            Clients updateClients = clientsRepository.save(clients);
            return ResponseEntity.ok(updateClients);
        }

        public ResponseEntity<String> deleteClients(UUID id) {
            Clients clients = findClientsById(id);

            clientsRepository.delete(clients);

            return ResponseEntity.ok("Successfully Deleted Clients: " + id);
        }

        public long count() {
            return clientsRepository.count();
        }

        private Clients findClientsById(UUID id) {
            try {
                return clientsRepository.findById(id.getMostSignificantBits())
                        .orElseThrow(() -> new ResourceNotFoundException("Clients Does Not Exist with this Id: " + id));
            } catch(ResourceNotFoundException e) {
                return null;
            }
        }
    }

