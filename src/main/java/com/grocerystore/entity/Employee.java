package com.grocerystore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeSocialSecurityNumber;
    private String employeePhoneNumber;
    private String employeeEmail;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grocery_store_id")
    private GroceryStore groceryStore;
}
