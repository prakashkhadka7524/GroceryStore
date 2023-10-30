package com.grocerystore.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String streetAddress;
    private String city;
    private int zip;
    private String state;
    private String country;

//    @OneToOne(mappedBy = "address")
//    private GroceryStore groceryStore;

}
