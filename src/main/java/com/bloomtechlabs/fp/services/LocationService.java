package com.bloomtechlabs.fp.services;

import com.bloomtechlabs.fp.entities.Location;
import com.bloomtechlabs.fp.exceptions.ResourceNotFoundException;
import com.bloomtechlabs.fp.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return this.locationRepository.findAll();
    }

    public Page<Location> getAllLocationsPaginated(int offset, int limit) {
        return this.locationRepository.findAll(PageRequest.of(offset, limit));
    }

    public Location getLocationById(UUID id) throws ResourceNotFoundException {
        Location location = this.findLocationById(id);

        if(location.getDeletedAt() != null) {
            throw new ResourceNotFoundException("Location does not exist with Id: " + id);
        }

        return location;
    }

    public Location createLocation(Location location) throws IllegalArgumentException {
        if(location == null) {
            throw new IllegalArgumentException("Location input cannot be null");
        }

        return this.locationRepository.save(location);
    }

    public Location updateLocation(Location updatedLocation) throws IllegalArgumentException, ResourceNotFoundException {
        if(updatedLocation == null) {
            throw new IllegalArgumentException("Location input cannot be null");
        }

        Location currentLocation = this.findLocationById(updatedLocation.getId());

        currentLocation = currentLocation.toBuilder()
                .withType(updatedLocation.getType())
                .withName(updatedLocation.getName())
                .withLatlong(updatedLocation.getLatlong())
                .withAddress1(updatedLocation.getAddress1())
                .withAddress2(updatedLocation.getAddress2())
                .withCity(updatedLocation.getCity())
                .withState(updatedLocation.getState())
                .withZip(updatedLocation.getZip())
                .build();

        return this.locationRepository.save(currentLocation);
    }

    public boolean deleteLocationById(UUID id) throws IllegalArgumentException, ResourceNotFoundException {
        if(!this.locationRepository.existsById(id)) {
            throw new IllegalArgumentException("Location does not exist with this Id: " + id);
        }

        Location location = this.findLocationById(id);

        location = location.toBuilder()
                .withDeletedAt(Date.from(Instant.now()))
                .build();

        location = this.locationRepository.save(location);

        return location.getDeletedAt() != null;
    }


    private Location findLocationById(UUID id) throws ResourceNotFoundException {
        return this.locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location does not exist with this Id: " + id));
    }
}