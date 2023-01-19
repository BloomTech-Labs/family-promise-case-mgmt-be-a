package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class InsuranceDataSeeder implements CommandLineRunner {
    @Autowired
    InsuranceService insuranceService;

    @Override
    public void run(String... args) throws Exception {
        loadInsuranceData();
    }

    private void loadInsuranceData() {
        //if (insuranceService.)
    }
}
