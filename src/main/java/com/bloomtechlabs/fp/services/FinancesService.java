package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Finances;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.FinancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public Finances createFinances(Finances finances) {
        if (Objects.isNull(finances)) {
            throw new IllegalArgumentException("Finances input cannot be null");
        }

        return financesRepository.save(finances);
    }

    public ResponseEntity<Finances> getFinancesById(UUID id) {
        Finances finances = findFinancesById(id);

        return ResponseEntity.ok(finances);
    }

    public Finances updateFinances(UUID id, Finances financesDetails) {
        if (financesDetails == null) {
            throw new IllegalArgumentException("Finances input cannot be null");
        }

        Finances finances = this.findFinancesById(id);

        Finances updatedFinances = this.financesRepository.save(finances);

        return ResponseEntity.ok(updatedFinances);
    }

    public ResponseEntity<String> deleteFinances(UUID id) {
        Finances financesToDelete = this.findFinancesById(id);
        financesRepository.delete(financesToDelete );

        return ResponseEntity.ok("Successfully delete finances ID " + id);
    }

    public long count() {
        return financesRepository.count();
    }

    private Finances findFinancesById(UUID id) {
        try {
            return financesRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Finances Does Not Exist with this Id: " + id));
        } catch(ResourceNotFoundException e) {
            return null;
        }
    }
}
