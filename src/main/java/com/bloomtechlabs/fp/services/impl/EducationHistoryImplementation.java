package com.bloomtechlabs.fp.services.impl;
import com.bloomtechlabs.fp.entities.EducationHistory;
import com.bloomtechlabs.fp.repositories.EducationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/*
------- DOCUMENTATION -------

This is a Spring @Service implementation class called EducationHistoryImplementation.
It provides an implementation of the EducationHistoryService, which is a service layer class for managing education history data.
Unlike the traditional approach of defining an interface and then providing an implementation of that interface,
this implementation class directly provides the implementation of the service layer logic without using an interface.
This implementation class uses Spring's caching functionality to cache frequently accessed data, which can help to improve application performance.

The class is declared with the @Service annotation, which marks it as a Spring-managed service bean.
It also uses the @CacheConfig annotation to specify the name of the cache to use for caching the EducationHistory objects.

*** METHODS ***
The class has several methods, each of which is annotated with caching annotations for caching purposes. These methods are:
* getAll() method: This method returns a list of all EducationHistory objects. It is annotated with the @Cacheable annotation, which indicates that the result of this method should be cached with the specified cache name.
The method waits for 3 seconds using the waitSomeTime() method, which is for demo purposes to simulate a long-running operation.

* add() method: This method adds a new EducationHistory object to the database.
It is annotated with the @CacheEvict annotation, which indicates that the cache with the specified name should be evicted after the method call.

* update() method: This method updates an existing EducationHistory object in the database.
It is annotated with the @CacheEvict annotation, which indicates that the cache with the specified name should be evicted after the method call.

* delete() method: This method deletes an existing EducationHistory object from the database.
It is annotated with the @Caching annotation, which combines two @CacheEvict annotations. One annotation specifies that the cache with the specified key should be evicted, and the other annotation specifies that the entire cache should be evicted after the method call.

* getCustomerById() method: This method retrieves an EducationHistory object from the database by ID.
It is annotated with the @Cacheable annotation, which indicates that the result of this method should be cached with the specified cache name and key.
The unless attribute specifies that the result should not be cached if it is null.

It also has a private method called waitSomeTime(), which is used to simulate a long-running operation.

By A.M., Feb 25 2023
 */

@Service
@CacheConfig(cacheNames = "educationHistoryCache")

public class EducationHistoryImplementation  {

    @Autowired
    private EducationHistoryRepository educationHistoryRepository;

    @Cacheable(cacheNames = "educationHistoryCache")
    public List<EducationHistory> getAll() {
        waitSomeTime();
        return this.educationHistoryRepository.findAll();
    }

    @CacheEvict(cacheNames = "educationHistoryCache", allEntries = true)
    public EducationHistory add(EducationHistory educationHistory) {
        return this.educationHistoryRepository.save(educationHistory);
    }

    @CacheEvict(cacheNames = "educationHistoryCache", allEntries = true)
    public EducationHistory update(EducationHistory educationHistory) {

        Optional<EducationHistory> optionalEducationHistory = this.educationHistoryRepository.findById(educationHistory.getId().getMostSignificantBits());
        // getMostSignificantBits method is to convert UUID to long. If there are issues consider changing return type of method getId from EducationHistory to long
        if (!optionalEducationHistory.isPresent())
            return null;
        EducationHistory repEducationHistory = optionalEducationHistory.get();
        repEducationHistory.setClientId(educationHistory.getClientId());
        repEducationHistory.setLevel(educationHistory.getLevel());
        repEducationHistory.setStartDate(educationHistory.getStartDate());
        repEducationHistory.setEndDate(educationHistory.getEndDate());
        repEducationHistory.setSchoolName(educationHistory.getSchoolName());
        return this.educationHistoryRepository.save(repEducationHistory);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "educationHistoryCache", key = "#id"),
            @CacheEvict(cacheNames = "educationHistoryCache", allEntries = true)})
    public void delete(long id){
        this.educationHistoryRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "educationHistoryCache", key = "#id", unless = "#result == null")
    public EducationHistory getCustomerById (long id) {
        waitSomeTime();
        return this.educationHistoryRepository.findById(id).orElse(null);
    }

    private void waitSomeTime() {
        System.out.println("Long Wait Begin");
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Long Wait End");
    }
}
