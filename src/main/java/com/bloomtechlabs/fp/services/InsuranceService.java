package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Insurance;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepository;

    public List<Insurance> getAllInsurances() {
        return this.insuranceRepository.findAll();
    }

    public Page<Insurance> getAllInsurancesPaginated(int offset, int limit) {
        return this.insuranceRepository.findAll(PageRequest.of(offset, limit));
    }

    public Insurance getInsuranceById(UUID id) throws ResourceNotFoundException {
        return this.findInsuranceById(id);
    }

    public Insurance createInsurance(Insurance insurance) throws IllegalArgumentException {
        if(insurance == null) {
            throw new IllegalArgumentException("Insurance input cannot be null");
        }

        return this.insuranceRepository.save(insurance);
    }

    public Insurance updateInsurance(Insurance updatedInsurance) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedInsurance == null) {
            throw new IllegalArgumentException("Insurance input cannot be null");
        }

        Insurance currentInsurance = this.findInsuranceById(updatedInsurance.getId());

        currentInsurance = currentInsurance.toBuilder()
                .withMedicareNumber(updatedInsurance.getMedicareNumber())
                .withMedicareEffectiveDate(updatedInsurance.getMedicareEffectiveDate())
                .withMedicaidNumber(updatedInsurance.getMedicaidNumber())
                .withMedicaidEffectiveDate(updatedInsurance.getMedicaidEffectiveDate())
                .withMedicaidExpirationDate(updatedInsurance.getMedicaidExpirationDate())
                .withPrivateInsuranceCompany(updatedInsurance.getPrivateInsuranceCompany())
                .withPrivateInsuranceGroupNumber(updatedInsurance.getPrivateInsuranceGroupNumber())
                .withPrivateInsuranceSubscriberNumber(updatedInsurance.getPrivateInsuranceSubscriberNumber())
                .withPrivateInsuranceEffectiveDate(updatedInsurance.getPrivateInsuranceEffectiveDate())
                .withPrivateInsuranceExpirationDate(updatedInsurance.getPrivateInsuranceExpirationDate())
                .withOtherCoverage(updatedInsurance.getOtherCoverage())
                .withOtherAgencies(updatedInsurance.getOtherAgencies())
                .build();

        return this.insuranceRepository.save(currentInsurance);
    }

    public boolean deleteInsuranceById(UUID id) throws IllegalArgumentException {
        if(!this.insuranceRepository.existsById(id)) {
            throw new IllegalArgumentException("Insurance does not exist with this Id: " + id);
        }

        this.insuranceRepository.deleteById(id);

        return this.insuranceRepository.existsById(id);
    }


    private Insurance findInsuranceById(UUID id) throws ResourceNotFoundException {
        return this.insuranceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance does not exist with this Id: " + id));
    }

}