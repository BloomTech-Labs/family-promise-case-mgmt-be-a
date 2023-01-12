package com.bloomtechlabs.fp.repositories;

import com.bloomtechlabs.fp.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

}
