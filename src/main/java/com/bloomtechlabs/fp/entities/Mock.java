package com.bloomtechlabs.fp.entities;

import javax.persistence.*;

/**
 * Mock postgresql table for migration, POC, and testing purposes.
 */
@Entity
@Table(name = "mock_table")
public class Mock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "address")
    private String address;
    
    public Mock() {}

    public Mock(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    private Long getId() { return id; }
    private Mock setId(Long id) {
        this.id = id;
        return this;
    }
    
    private String getName() { return name; }
    private Mock setName(String name) {
        this.name = name;
        return this;
    }
    
    private int getAge() { return age; }
    private Mock setAge(int age) {
        this.age = age;
        return this;
    }
    
    private String getAddress() { return address; }
    private Mock setAddress(String address) {
        this.address = address;
        return this;
    }
}
