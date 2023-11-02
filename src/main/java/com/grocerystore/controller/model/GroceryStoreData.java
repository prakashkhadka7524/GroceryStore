package com.grocerystore.controller.model;

import com.grocerystore.entity.Customer;
import com.grocerystore.entity.Employee;
import com.grocerystore.entity.GroceryStore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class GroceryStoreData {

    private Long groceryStoreId;
    private String storeName;
    private String streetAddress;
    private String city;
    private int zip;
    private String state;
    private String country;
    Set<EmployeeData> employees = new HashSet<>();
    Set<CustomerData> customers = new HashSet<>();


    public GroceryStoreData(GroceryStore groceryStore) {
        this.groceryStoreId = groceryStore.getGroceryStoreId();
        this.storeName = groceryStore.getStoreName();
        this.streetAddress=groceryStore.getStreetAddress();
        this.city=groceryStore.getCity();
        this.state=groceryStore.getState();
        this.zip= groceryStore.getZip();
        this.country=groceryStore.getCountry();
        for (Customer customer : groceryStore.getCustomers()) {
            customers.add(new CustomerData(customer));
        }
        for (Employee employee : groceryStore.getEmployees()) {
            employees.add(new EmployeeData(employee));
        }


    }


}