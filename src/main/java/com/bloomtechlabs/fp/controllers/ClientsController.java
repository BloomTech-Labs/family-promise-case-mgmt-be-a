package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Clients;
import com.bloomtechlabs.fp.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientsController {


        @Autowired
        ClientsService clientsService;

        @GetMapping
        List<Clients> getAllClients() {
            return clientsService.getAllClients();
        }

        /**
         * @param offset page index to return results from.
         * @param limit number of results to include per page.
         * @return returns a paginated list.
         */
        @GetMapping("{offset}/{limit}")
        public ResponseEntity<Page<Clients>> getAllClientsPaginated(@PathVariable int offset, @PathVariable int limit) {
            return ResponseEntity.ok(clientsService.getAllClientsPaginated(offset, limit));
        }

        @PostMapping
        Clients createClients(@RequestBody Clients clients) {
            return clientsService.createClients(clients);
        }

        @GetMapping("{id}")
        public ResponseEntity<Clients> getClients(@PathVariable UUID id) {
            return clientsService.getClientsById(id);
        }

        @PutMapping("{id}")
        public ResponseEntity<Clients> updateClients(@PathVariable UUID id,
                                                                       @RequestBody Clients clients) {
            return clientsService.updateClients(id,clients);
        }

        @DeleteMapping("{id}")
        public ResponseEntity<String> deleteClients(@PathVariable UUID id) {
            return clientsService.deleteClients(id);
        }
}

