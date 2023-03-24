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

    @PutMapping
    public ResponseEntity<Household> updateHouseholdById(@RequestBody Household household) {
        Household updatedHousehold;
        try {
            updatedHousehold = householdService.updateHousehold(household)
        } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body();
        }

        return ResponseEntity.ok();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteHouseholdById(@PathVariable BigInteger id) {
        return householdService.deleteHouseholdById(id);
    }
}
