package com.bloomtechlabs.fp.repositories;

import com.bloomtechlabs.fp.entities.Finances;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface FinancesRepository extends JpaRepository<Finances, UUID> {
}
