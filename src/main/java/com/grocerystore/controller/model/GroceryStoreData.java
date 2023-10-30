package com.grocerystore.controller.model;

import com.grocerystore.entity.Address;
import com.grocerystore.entity.Customer;
import com.grocerystore.entity.Employee;
import com.grocerystore.entity.GroceryStore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class GroceryStoreData {

    private Long groceryStoreId;
    private String storeName;
    private Address addresses;
    Set<EmployeeData> employees = new HashSet<>();
    Set<CustomerData> customers = new HashSet<>();


    public GroceryStoreData(GroceryStore groceryStore) {
        this.groceryStoreId = groceryStore.getGroceryStoreId();
        this.storeName = groceryStore.getStoreName();
        this.addresses = groceryStore.getAddress();
        for (Customer customer : groceryStore.getCustomers()) {
            customers.add(new CustomerData(customer));
        }
        for (Employee employee : groceryStore.getEmployees()) {
            employees.add(new EmployeeData(employee));
        }


    }


}
