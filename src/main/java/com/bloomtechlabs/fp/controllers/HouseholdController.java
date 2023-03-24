package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Household;
import com.bloomtechlabs.fp.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @GetMapping
    public List<Household> findAllHouseholds() {
        return householdService.findAllHouseholds();
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list.
     */
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<Page<Household>> findAllHouseholdsPaginated(@PathVariable int offset, @PathVariable int limit) {
        return ResponseEntity.ok(householdService.findAllHouseholdsPaginated(offset, limit));
    }

    @GetMapping("{id}")
    public ResponseEntity<Household> getHouseholdById(@PathVariable BigInteger id) {
        return householdService.getHouseholdById(id);
    }

    @PostMapping
    public Household createHousehold(@RequestBody Household household) {
        return householdService.saveHousehold(household);
    }

    @PutMapping("{id}")
    public ResponseEntity<Household> updateHouseholdById(@PathVariable BigInteger id, @RequestBody Household household) {
        return ResponseEntity.ok(householdService.editHouseholdById(id, household));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHouseholdById(@PathVariable BigInteger id) {
        return householdService.deleteHouseholdById(id);
    }


    @GetMapping("/firstname/{firstname}")
    public ResponseEntity<List<Household>> getHouseHoldByFirstName(@PathVariable ("firstname") String firstname) {
        List<Household> households = householdService.getHouseholdsByFirstName(firstname);
        return new ResponseEntity<>(households, HttpStatus.OK);
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<Household>> getHouseHoldByLastName(@PathVariable ("lastname") String lastName) {
        List<Household> households = householdService.getHouseholdsByLastName(lastName);
        return new ResponseEntity<>(households, HttpStatus.OK);
    }

    @GetMapping("/needsInterpreter/{interpreterNeeds}")
    public ResponseEntity<List<Household>> getHouseholdsByInterpreterNeeds(@PathVariable("interpreterNeeds") String interpreterNeeds) {
        Boolean isNeedsInterpreter = Boolean.valueOf(interpreterNeeds);
        List<Household> households = householdService.getHouseholdsByInterpreterNeeds(isNeedsInterpreter);
        return new ResponseEntity<>(households, HttpStatus.OK);
    }




}
