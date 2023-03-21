package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Finances;
import com.bloomtechlabs.fp.services.FinancesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/finances")
public class FinancesController {

    @Autowired
    FinancesService financesService;

    @GetMapping
    public List<Finances> findAllFinances() {
        return financesService.getAllFinances();
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list.
     */
    @GetMapping("{offset}/{limit}")
    public ResponseEntity<Page<Finances>> findAllFinancesPaginated(@PathVariable int offset, @PathVariable int limit) {
        return ResponseEntity.ok(financesService.getAllFinancesPaginated(offset, limit));
    }

    @GetMapping("{id}")
    public ResponseEntity<Finances> getFinancesById(@PathVariable UUID id) {
        return financesService.getFinancesById(id);
    }

    @PostMapping
    public Finances createFinances(@RequestBody Finances finances) {
        return financesService.createFinances(finances);
    }

    @PutMapping("{id}")
    public ResponseEntity<Finances> updateFinances(@PathVariable UUID id, @RequestBody Finances finances) {
        return financesService.updateFinances(id, finances);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFinances(@PathVariable UUID id) {
        return financesService.deleteFinances(id);
    }

}
