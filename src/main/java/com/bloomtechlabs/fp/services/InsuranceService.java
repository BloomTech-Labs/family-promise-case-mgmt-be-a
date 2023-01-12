package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Insurance;
import com.bloomtechlabs.fp.repositories.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    public List<Insurance> findAllInsurances() {
        return insuranceRepository.findAll();
    }

    public Insurance findInsuranceById(Long id) {
        return insuranceRepository.getReferenceById(id);
    }

    public Insurance save(Insurance insurance) {
        if (insurance == null) {

        }
       return insuranceRepository.save(insurance);
    }

    public Insurance saveOrUpdate(Insurance insurance) {
        if (insuranceRepository.existsById(insurance.getId())) {
          return insuranceRepository.save(insurance);
        } else {
            return null; //???
        }
    }

    public void delete(Long id) {
        insuranceRepository.deleteById(id);
    }


}
