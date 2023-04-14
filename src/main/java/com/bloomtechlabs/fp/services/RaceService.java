package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Race;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RaceService {
    @Autowired
    private RaceRepository raceRepository;

    public List<Race> getAllRaces() {
        return this.raceRepository.findAll();
    }

    public Page<Race> getAllRacesPaginated(int offset, int limit) {
        return this.raceRepository.findAll(PageRequest.of(offset, limit));
    }

    public Race getRaceById(UUID id) throws ResourceNotFoundException {
        return this.findRaceById(id);
    }

    public Race createRace(Race race) throws IllegalArgumentException {
        if(race == null) {
            throw new IllegalArgumentException("Race input cannot be null");
        }

        return this.raceRepository.save(race);
    }

    public Race updateRace(Race updatedRace) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedRace == null) {
            throw new IllegalArgumentException("Race input cannot be null");
        }

        Race currentRace = this.findRaceById(updatedRace.getId());

        currentRace = currentRace.toBuilder()
                .withName(updatedRace.getName())
                .build();

        return this.raceRepository.save(currentRace);
    }

    public boolean deleteRaceById(UUID id) throws IllegalArgumentException {
        if(!this.raceRepository.existsById(id)) {
            throw new IllegalArgumentException("Race does not exist with this Id: " + id);
        }

        this.raceRepository.deleteById(id);

        return this.raceRepository.existsById(id);
    }


    private Race findRaceById(UUID id) throws ResourceNotFoundException {
        return this.raceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Race does not exist with this Id: " + id));
    }
}