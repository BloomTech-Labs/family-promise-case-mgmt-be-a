package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Finances;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.FinancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FinancesService {
    @Autowired
    private FinancesRepository financesRepository;

    public List<Finances> getAllFinances() { return financesRepository.findAll();}

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list.
     */
    public Page<Finances> getAllFinancesPaginated(int offset, int limit) {
        return financesRepository.findAll(PageRequest.of(offset, limit));
    }

    public Finances createFinances(Finances finances) throws IllegalArgumentException {
        if (finances == null) {
            throw new IllegalArgumentException("Finances input cannot be null");
        }

        return this.financesRepository.save(finances);
    }

    public Finances getFinancesById(UUID id) throws ResourceNotFoundException {
        return this.findFinancesById(id);
    }

    public Finances updateFinances(Finances updatedFinances) throws IllegalArgumentException, ResourceNotFoundException {
        if (updatedFinances == null) {
            throw new IllegalArgumentException("Finances input cannot be null");
        }

        Finances currentFinances = this.findFinancesById(updatedFinances.getId());

        currentFinances = currentFinances.toBuilder()
                .withClientId(updatedFinances.getClientId())
                .withTypeOfDebt(updatedFinances.getTypeOfDebt())
                .withHistoryOfEvictions(updatedFinances.getHistoryOfEvictions())
                .withHistoryOfLandlordDebt(updatedFinances.getHistoryOfLandlordDebt())
                .withHistoryOfCriminalActivity(updatedFinances.getHistoryOfCriminalActivity())
                .withHistoryOfPoorCredit(updatedFinances.getHistoryOfPoorCredit())
                .withRentalHistory(updatedFinances.getRentalHistory())
                .withAmountOfStudentDebt(updatedFinances.getAmountOfStudentDebt())
                .withAmountOfMedicalDebt(updatedFinances.getAmountOfMedicalDebt())
                .withAmountOfCreditCardDebt(updatedFinances.getAmountOfCreditCardDebt())
                .withAmountOfAutoDebt(updatedFinances.getAmountOfAutoDebt())
                .build();

        return this.financesRepository.save(currentFinances);
    }

    public boolean deleteFinancesById(UUID id) throws ResourceNotFoundException {
        if(!this.financesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Finances Does Not Exist with this Id: " + id);
        }

        this.financesRepository.deleteById(id);

        return this.financesRepository.existsById(id);
    }

    public long count() {
        return financesRepository.count();
    }

    private Finances findFinancesById(UUID id) throws ResourceNotFoundException {
        return financesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Finances Does Not Exist with this Id: " + id));
    }
}
