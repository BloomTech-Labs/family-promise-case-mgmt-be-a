package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Client;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    /**
     * @param offset page index to return results from.
     * @param limit  number of results to include per page.
     * @return returns a paginated list.
     */
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<Page<Client>> getAllClientsPaginated(@PathVariable int offset, @PathVariable int limit) {
        return ResponseEntity.ok(clientService.getAllClientsPaginated(offset, limit));
    }

    @GetMapping("{id}")
    public ResponseEntity<ObjectNode> getClientById(@PathVariable UUID id) {
        ObjectNode json;
        Client client;

        try {
            client = this.clientService.getClientById(id);
        } catch(ResourceNotFoundException e) {
            json = this.mapper.createObjectNode();
            ObjectNode errors = json.putObject("error");

            errors.put("userResponse", "Could not update Client!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        json = this.mapper.convertValue(client, ObjectNode.class);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PostMapping
    public ResponseEntity<ObjectNode> createClient(@RequestBody Client client) {
        try {
            this.clientService.createClient(client);
        } catch(IllegalArgumentException e) {
            ObjectNode json = this.mapper.createObjectNode();
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not create Client!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<ObjectNode> updateClient(@RequestBody Client client) {
        ObjectNode json = this.mapper.createObjectNode();
        Client updatedClient;

        try {
            updatedClient = this.clientService.updateClient(client);
        } catch(IllegalArgumentException e) {
            ObjectNode errors = json.putObject("errors");
            errors.put("userResponse", "Could not update Household!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        } catch(ResourceNotFoundException e) {
            ObjectNode errors = json.putObject("errors");
            errors.put("userResponse", "Could not update client!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }

        json.putPOJO("updatedClient", updatedClient);

        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ObjectNode> deleteClientById(@PathVariable UUID id) {
        ObjectNode json = this.mapper.createObjectNode();
        boolean isFailure;

        try {
            isFailure = this.clientService.deleteClient(id);
        } catch(IllegalArgumentException e) {
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not delete Client!");
            errors.put("errorMessage", e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        }
        if(isFailure) {
            ObjectNode errors = json.putObject("error");
            errors.put("userResponse", "Could not delete Client!");
            errors.put("errorMessage", "Something went wrong with this request");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
        }

        json.put("response", "Client with Id " + id + " has been successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(json);
    }
}

