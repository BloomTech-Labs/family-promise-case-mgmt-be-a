package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Household;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ResponseEntity<Household> getHouseholdById(BigInteger id) {
        Household household = findHouseholdById(id);

        return ResponseEntity.ok(household);
    }

    private Household findHouseholdById(BigInteger id) {
        return householdRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Household does not exist for Id: " + id.toString()));
    }

    public Household saveHousehold(Household household) {
        if (Objects.isNull(household)) {
            throw new IllegalArgumentException("Household input cannot be null");
        }
        return householdRepository.save(household);
    }

    public Household editHouseholdById(BigInteger id, Household household) {
        if (Objects.isNull(household)) {
            throw new IllegalArgumentException("Household input cannot be null");
        }
        Household householdToEdit = findHouseholdById(id);

        householdToEdit.setName(household.getName());
        householdToEdit.setTimesHomelessInThreeYears(household.getTimesHomelessInThreeYears());
        householdToEdit.setTotalTimeHomelessThreeYears(household.getTotalTimeHomelessThreeYears());
        householdToEdit.setTotalTimeHomelessPastYear(household.getTotalTimeHomelessPastYear());
        householdToEdit.setPreviouslyLivingInNonHumanHabitation(household.isPreviously_living_in_non_human_habitation());
        householdToEdit.setPreviouslyLivingInEmergencyShelter(household.isPreviously_living_in_emergency_shelter());
        householdToEdit.setPreviouslyUnsheltered(household.isPreviously_unsheltered());
        householdToEdit.setPreviousStayLength(household.getPreviousStayLength());
        householdToEdit.setNeedsInterpreter(household.isNeeds_interpreter());
        householdToEdit.setAccessToPrivateTransportation(household.isAccess_to_private_transportation());
        householdToEdit.setClientOrFamilyPhysicalIllnessHistory(household.getClientOrFamilyPhysicalIllnessHistory());
        householdToEdit.setClientOrFamilyMentalIllnessHistory(household.getClientOrFamilyMentalIllnessHistory());
        householdToEdit.setClientOrFamilyPersonalViolenceHistory(household.getClientOrFamilyPersonalViolenceHistory());
        householdToEdit.setClientOrFamilySubstanceDependencyHistory(household.getClientOrFamilySubstanceDependencyHistory());
        householdToEdit.setCpsInvolvement(household.isCps_involvement());
        householdToEdit.setCpsInvolvementActive(household.isCps_involvement_active());
        householdToEdit.setDcyfContactName(household.getDcyfContactName());
        householdToEdit.setDcyfContactEmail(household.getDcyfContactEmail());
        householdToEdit.setDcyfContactPhoneNumber(household.getDcyfContactPhoneNumber());
        householdToEdit.setSection8VoucherLost(household.isSection_8_voucher_lost());

        return saveHousehold(householdToEdit);
    }

    public ResponseEntity<String> deleteHouseholdById(BigInteger id) {
        Household householdToDelete = findHouseholdById(id);
        householdRepository.delete(householdToDelete);

        return ResponseEntity.ok("Successfully deleted Household ID: " + id);
    }

    public long count() {
        return householdRepository.count();
    }
}
