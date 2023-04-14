package com.bloomtechlabs.fp.repositories;

import com.bloomtechlabs.fp.entities.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailAddressRepository extends JpaRepository<EmailAddress, UUID> {}