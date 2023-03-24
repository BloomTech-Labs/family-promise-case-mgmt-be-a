package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Clients;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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

        public Clients getClientById(UUID id) throws ResourceNotFoundException {
            return this.findClientById(id);
        }

        public Clients createClient(Clients client) throws IllegalArgumentException {
            if(client == null) {
                throw new IllegalArgumentException("Client input cannot be null");
            }

            return this.clientsRepository.save(client);
        }

        public Clients updateClient(Clients updatedClient) throws IllegalArgumentException, ResourceNotFoundException {
            if(updatedClient == null) {
                throw new IllegalArgumentException("Client input cannot be null");
            }

            Clients currentClient = this.findClientById(updatedClient.getId());

            currentClient = currentClient.toBuilder().withHouseholdId(updatedClient.getHouseholdId())
                    .withFirstName(updatedClient.getFirstName()).withLastName(updatedClient.getLastName())
                    .withSsn(updatedClient.getSsn()).withIsHoh(updatedClient.getHoh())
                    .withRelation(updatedClient.getRelation()).withEducationLevel(updatedClient.getEducationLevel())
                    .withGenderId(updatedClient.getGenderId()).withRaceId(updatedClient.getRaceId())
                    .withEthnicityId(updatedClient.getEthnicityId()).withFinancesId(updatedClient.getFinancesId())
                    .withInsuranceId(updatedClient.getInsuranceId()).withDocumentsId(updatedClient.getDocumentsId())
                    .withGoalsId(updatedClient.getGoalsId()).withCreatedAt(updatedClient.getCreatedAt())
                    .withDisabilitiesId(updatedClient.getDisabilitiesId())
                    .build();


            return this.clientsRepository.save(currentClient);
        }

        public boolean deleteClient(UUID id) throws IllegalArgumentException {
            if(!this.clientsRepository.existsById(id)) {
                throw new IllegalArgumentException("Client does Not Exist with this Id: " + id);
            }

            this.clientsRepository.deleteById(id);

            return this.clientsRepository.existsById(id);
        }

        public long count() {
            return clientsRepository.count();
        }

        private Clients findClientById(UUID id) throws ResourceNotFoundException {
            return clientsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Clients Does Not Exist with this Id: " + id));
        }
    }

