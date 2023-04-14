package com.bloomtechlabs.fp.repositories;

import com.bloomtechlabs.fp.entities.ClientNotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientNotesRepository extends JpaRepository<ClientNotes, UUID> {}