package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Clients;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.services.ClientsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    @Autowired
    private ClientsService clientsService;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public List<Clients> getAllClients() {
        return clientsService.getAllClients();
    }

    /**
     * @param offset page index to return results from.
     * @param limit  number of results to include per page.
     * @return returns a paginated list.
     */
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<Page<Clients>> getAllClientsPaginated(@PathVariable int offset, @PathVariable int limit) {
        return ResponseEntity.ok(clientsService.getAllClientsPaginated(offset, limit));
    }

    @GetMapping("{id}")
    public ResponseEntity<ObjectNode> getClientById(@PathVariable UUID id) {
        ObjectNode json;
        Clients client;

        try {
            client = this.clientsService.getClientById(id);
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
    public ResponseEntity<ObjectNode> createClient(@RequestBody Clients client) {
        try {
            this.clientsService.createClient(client);
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
    public ResponseEntity<ObjectNode> updateClient(@RequestBody Clients client) {
        ObjectNode json = this.mapper.createObjectNode();
        Clients updatedClient;

        try {
            updatedClient = this.clientsService.updateClient(client);
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
    public ResponseEntity<ObjectNode> deleteClient(@PathVariable UUID id) {
        ObjectNode json = this.mapper.createObjectNode();
        boolean isFailure;

        try {
            isFailure = this.clientsService.deleteClient(id);
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

