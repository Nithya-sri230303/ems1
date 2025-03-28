package com.example.model;

import jakarta.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "employee_table")
public class EmployeeTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String designation;
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private AddressTable address;

    public EmployeeTable(Long id, String name, String designation, AddressTable address) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.address = address;
    }
    public EmployeeTable() {
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public AddressTable getAddress() {
        return address;
    }

    public void setAddress(AddressTable address) {
        this.address = address;
    }
}
