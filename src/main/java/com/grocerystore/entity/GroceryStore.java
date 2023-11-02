package com.grocerystore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class GroceryStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groceryStoreId;
    private String storeName;
    private String streetAddress;
    private String city;
    private int zip;
    private String state;
    private String country;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "grocery_store_customer", joinColumns = @JoinColumn(name = "grocery_store_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    Set<Customer> customers = new HashSet<Customer>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "groceryStore", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Employee> employees = new HashSet<>();
}