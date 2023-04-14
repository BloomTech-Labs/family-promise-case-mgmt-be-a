package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Household;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Service
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    public List<Household> findAllHouseholds() {
        return householdRepository.findAll();
    }

    /**
     * @param offset page index to return results from.
     * @param limit number of results to include per page.
     * @return returns a paginated list.
     */
    public Page<Household> findAllHouseholdsPaginated(int offset, int limit) {
        return householdRepository.findAll(PageRequest.of(offset, limit));
    }

    public Household getHouseholdById(BigInteger id) throws ResourceNotFoundException {
        return this.findHouseholdById(id);
    }

    public Household createHousehold(Household household) throws IllegalArgumentException {
        if (household == null) {
            throw new IllegalArgumentException("Household input cannot be null");
        }
        return householdRepository.save(household);
    }

    public Household updateHousehold(Household updatedHousehold) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedHousehold == null) {
            throw new IllegalArgumentException("Household input cannot be null");
        }

        Household currentHousehold = this.findHouseholdById(updatedHousehold.getId());

        // update household information
        currentHousehold = currentHousehold.toBuilder()
                .withName(updatedHousehold.getName())
                .withTimesHomelessInThreeYears(updatedHousehold.getTimesHomelessInThreeYears())
                .withTotalTimeHomelessThreeYears(updatedHousehold.getTotalTimeHomelessThreeYears())
                .withTotalTimeHomelessPastYear(updatedHousehold.getTotalTimeHomelessPastYear())
                .withPreviouslyLivingInNonHumanHabitation(updatedHousehold.getPreviouslyLivingInNonHumanHabitation())
                .withPreviouslyLivingInEmergencyShelter(updatedHousehold.getPreviouslyLivingInEmergencyShelter())
                .withPreviouslyUnsheltered(updatedHousehold.getPreviouslyUnsheltered())
                .withPreviousStayLength(updatedHousehold.getPreviousStayLength())
                .withNeedsInterpreter(updatedHousehold.getNeedsInterpreter())
                .withAccessToPrivateTransportation(updatedHousehold.getAccessToPrivateTransportation())
                .withClientOrFamilyPhysicalIllnessHistory(updatedHousehold.getClientOrFamilyPhysicalIllnessHistory())
                .withClientOrFamilyMentalIllnessHistory(updatedHousehold.getClientOrFamilyMentalIllnessHistory())
                .withClientOrFamilyPersonalViolenceHistory(updatedHousehold.getClientOrFamilyPersonalViolenceHistory())
                .withClientOrFamilySubstanceDependencyHistory(updatedHousehold.getClientOrFamilySubstanceDependencyHistory())
                .withCpsInvolvement(updatedHousehold.getCpsInvolvement())
                .withCpsInvolvementActive(updatedHousehold.getCpsInvolvementActive())
                .withDcyfContactName(updatedHousehold.getDcyfContactName())
                .withDcyfContactEmail(updatedHousehold.getDcyfContactEmail())
                .withDcyfContactPhoneNumber(updatedHousehold.getDcyfContactPhoneNumber())
                .withSection8VoucherLost(updatedHousehold.getSection8VoucherLost())
                .build();

        return this.householdRepository.save(currentHousehold);
    }

    public boolean deleteHouseholdById(BigInteger id) {
        if(!this.householdRepository.existsById(id)) {
            throw new IllegalArgumentException("Household Does Not Exist with this Id: " + id);
        }

        householdRepository.deleteById(id);

        return this.householdRepository.existsById(id);
    }

    public long count() {
        return householdRepository.count();
    }

    private Household findHouseholdById(BigInteger id) throws ResourceNotFoundException {
        return this.householdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find household by id: " + id));
    }

    public List<Household> getHouseholdsByFirstName(String firstName) {
        return householdRepository.findByFirstName(firstName);
    }
    public List<Household> getHouseholdsByLastName(String lastName) {
        return householdRepository.findByLastName(lastName);
    }

    public List<Household> getHouseholdsByInterpreterNeeds (Boolean isNeedsInterpreter) {
        return householdRepository.findByInterpreterNeeds(isNeedsInterpreter);
    }
}
