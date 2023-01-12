package com.bloomtechlabs.fp.repositories;

import com.bloomtechlabs.fp.entities.EducationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationHistoryRepository extends JpaRepository<EducationHistory, Long> {
}
