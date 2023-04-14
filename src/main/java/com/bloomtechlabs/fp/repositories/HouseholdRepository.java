package com.bloomtechlabs.fp.repositories;

import com.bloomtechlabs.fp.entities.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, BigInteger> {
    @Query("SELECT h FROM Household h WHERE lower(h.name) LIKE lower(concat(:firstName, ' ', '%'))")
    List<Household> findByFirstName(String firstName);

    @Query("SELECT h FROM Household h WHERE lower(h.name) LIKE lower(concat('%', ' ', :lastName))")
    List<Household> findByLastName(String lastName);

    @Query("SELECT h FROM Household h WHERE lower(h.name) = lower(concat(:firstName, ' ', :lastName))")
    List<Household> findByFullName(String firstName, String lastName);

    @Query("SELECT h FROM Household h WHERE lower(h.dcyfContactName) LIKE lower(concat(:firstName, ' ', '%'))")
    List<Household> findDcyfContactNameByFirstName(String firstName);

    @Query("SELECT h FROM Household h WHERE lower(h.dcyfContactName) LIKE lower(concat('%', ' ', :lastName))")
    List<Household> findDcyfContactByLastName(String lastName);

    @Query("SELECT h FROM Household h WHERE lower(h.dcyfContactName) = lower(concat(:firstName, ' ', :lastName))")
    List<Household> findDcyfContactByFullName(String firstName, String lastName);

    @Query("SELECT h from Household h WHERE h.needsInterpreter = :isNeedsInterpreter")
    List<Household> findByInterpreterNeeds(boolean isNeedsInterpreter);
}
