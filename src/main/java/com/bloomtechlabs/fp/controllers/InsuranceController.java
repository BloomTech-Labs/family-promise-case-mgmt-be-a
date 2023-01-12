package com.bloomtechlabs.fp.controllers;

import com.bloomtechlabs.fp.entities.Insurance;
import com.bloomtechlabs.fp.services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @GetMapping("/{id}")
    public Insurance get(@RequestParam Long id) {
        return insuranceService.findInsuranceById(id);
    }

    @PostMapping
    public Insurance post(@RequestBody Insurance insurance) {
        return insuranceService.save(insurance);
    }

    @PutMapping("/{id}")
    public Insurance put(@RequestBody Insurance insurance) {
        return insuranceService.save(insurance);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        insuranceService.delete(id);
    }

}
