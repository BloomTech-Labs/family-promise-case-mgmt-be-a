package com.bloomtechlabs.fp.dataseeders;

import com.bloomtechlabs.fp.entities.Household;
import com.bloomtechlabs.fp.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HouseholdDataSeeder implements CommandLineRunner {
    @Autowired
    HouseholdService householdService;

    @Override
    public void run(String... args) throws Exception  {
        loadHouseholdData();
    }

    private void loadHouseholdData() {
        if (householdService.count() == 0) {
            Household household1 = Household.builder()
                    .withName("Griffin Household")
                    .withTimesHomelessInThreeYears(1)
                    .withTotalTimeHomelessThreeYears(100)
                    .withTotalTimeHomelessPastYear(20)
                    .withPreviouslyLivingInNonHumanHabitation(false)
                    .withPreviouslyLivingInEmergencyShelter(true)
                    .withPreviouslyUnsheltered(true)
                    .withPreviousStayLength(20)
                    .withNeedsInterpreter(true)
                    .withAccessToPrivateTransportation(true)
                    .withClientOrFamilyPhysicalIllnessHistory("sausage fingers")
                    .withClientOrFamilyMentalIllnessHistory("disruptive behavior")
                    .withClientOrFamilyPersonalViolenceHistory("bird assault")
                    .withClientOrFamilySubstanceDependencyHistory("none")
                    .withCpsInvolvement(false)
                    .withCpsInvolvementActive(false)
                    .withDcyfContactName("Peter Griffin")
                    .withDcyfContactEmail("peter.griffin@quahog.org")
                    .withDcyfContactPhoneNumber("555-444-3333")
                    .withSection8VoucherLost(false)
                    .build();

            Household household2 = Household.builder()
                    .withName("Reynolds Household")
                    .withTimesHomelessInThreeYears(3)
                    .withTotalTimeHomelessThreeYears(200)
                    .withTotalTimeHomelessPastYear(60)
                    .withPreviouslyLivingInNonHumanHabitation(true)
                    .withPreviouslyLivingInEmergencyShelter(false)
                    .withPreviouslyUnsheltered(true)
                    .withPreviousStayLength(60)
                    .withNeedsInterpreter(false)
                    .withAccessToPrivateTransportation(false)
                    .withClientOrFamilyPhysicalIllnessHistory("eyesight, high cholesterol, high blood pressure")
                    .withClientOrFamilyMentalIllnessHistory("bipolar, sociopath, schizophrenia")
                    .withClientOrFamilyPersonalViolenceHistory("gun violence")
                    .withClientOrFamilySubstanceDependencyHistory("alcohol, narcotics")
                    .withCpsInvolvement(true)
                    .withCpsInvolvementActive(false)
                    .withDcyfContactName("Frank Reynolds")
                    .withDcyfContactEmail("frank.reynolds@paddyspub.com")
                    .withDcyfContactPhoneNumber("555-111-2222")
                    .withSection8VoucherLost(true)
                    .build();

            Household household3 = Household.builder()
                    .withName("White Household")
                    .withTimesHomelessInThreeYears(2)
                    .withTotalTimeHomelessThreeYears(90)
                    .withTotalTimeHomelessPastYear(30)
                    .withPreviouslyLivingInNonHumanHabitation(true)
                    .withPreviouslyLivingInEmergencyShelter(false)
                    .withPreviouslyUnsheltered(true)
                    .withPreviousStayLength(7)
                    .withNeedsInterpreter(false)
                    .withAccessToPrivateTransportation(true)
                    .withClientOrFamilyPhysicalIllnessHistory("cancer, cerebral palsy")
                    .withClientOrFamilyMentalIllnessHistory("narcissistic personality disorder")
                    .withClientOrFamilyPersonalViolenceHistory("murder, gun violence, arson")
                    .withClientOrFamilySubstanceDependencyHistory("alcohol, tobacco")
                    .withCpsInvolvement(true)
                    .withCpsInvolvementActive(true)
                    .withDcyfContactName("Walter White")
                    .withDcyfContactEmail("walter.white@breakingbad.org")
                    .withDcyfContactPhoneNumber("555-999-9999")
                    .withSection8VoucherLost(false)
                    .build();

            Household householdEmpty = Household.builder()
                    .withName("Empty Household")
                    .build();

            householdService.createHousehold(household1);
            householdService.createHousehold(household2);
            householdService.createHousehold(household3);
            householdService.createHousehold(householdEmpty);

            System.out.println("Added " + householdService.count() + " records to the household table.");
       } else {
            System.out.println("There are already " + householdService.count() +
                    " records in the households table.");
        }
    }

}
