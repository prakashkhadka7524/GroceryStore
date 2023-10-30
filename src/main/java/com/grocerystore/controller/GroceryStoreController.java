package com.grocerystore.controller;

import com.grocerystore.controller.model.AddressData;
import com.grocerystore.controller.model.CustomerData;
import com.grocerystore.controller.model.EmployeeData;
import com.grocerystore.controller.model.GroceryStoreData;
import com.grocerystore.entity.Employee;
import com.grocerystore.entity.GroceryStore;
import com.grocerystore.service.GroceryStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grocery_store")
@Slf4j
public class GroceryStoreController {
    @Autowired
    private GroceryStoreService groceryStoreService;

    @PostMapping("/create")
    public GroceryStoreData insertGroceryData(@RequestBody GroceryStoreData groceryStoreData) {
        log.info("Creating Grocery Store []", groceryStoreData);
        return groceryStoreService.saveGroceryData(groceryStoreData);
    }

    @PostMapping("{groceryStoreId}/employee")
    public EmployeeData insertEmployee(@PathVariable Long groceryStoreId, @RequestBody EmployeeData employeeData) {
        return groceryStoreService.saveEmployee(groceryStoreId, employeeData);
    }

   // @GetMapping("{groceryStoreId}")
    public GroceryStore findById(@PathVariable Long groceryStoreId) {
        return groceryStoreService.findGroceryStoreById(groceryStoreId);
    }

    @PostMapping("{groceryStoreId}/customer")
    public CustomerData insertCustomer(@PathVariable Long groceryStoreId, @RequestBody CustomerData customerData) {
        return groceryStoreService.saveCustomer(groceryStoreId, customerData);
    }
    @PostMapping("{groceryStoreId}/address")
    public AddressData insertAddress(@PathVariable Long groceryStoreId, @RequestBody AddressData addressData){
        return groceryStoreService.saveAddress(groceryStoreId, addressData);
    }
    @GetMapping("/all")
    public List<GroceryStoreData> allGroceryStore(){
        return groceryStoreService.retriveAllGroceryStores();
    }
    @GetMapping("/{groceryStoreId}")
    public GroceryStoreData groceryStoreById(@PathVariable Long groceryStoreId){
        return groceryStoreService.retriveGroceryStoreById(groceryStoreId);
    }
    @PutMapping("{groceryStoreId}/update")
    public GroceryStoreData updateGroceryStore(@PathVariable Long groceryStoreId, @RequestBody GroceryStoreData groceryStoreData){
        groceryStoreData.setGroceryStoreId(groceryStoreId);
        return groceryStoreService.updateGroceryStore(groceryStoreId,groceryStoreData);
    }
    @PutMapping("employee-update/{employeeId}")
    public EmployeeData updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeData employeeData){
        employeeData.setEmployeeId(employeeId);
        return groceryStoreService.saveEmployee(employeeId, employeeData);
    }
    @PutMapping("customer-update/{customerId}")
    public CustomerData updateCustomer(@PathVariable Long customerId, @RequestBody CustomerData customerData){
        customerData.setCustomerId(customerId);
        return groceryStoreService.saveCustomer(customerId,customerData);
    }
    @PutMapping("address-update/{addressId}")
    public AddressData updateAddress(@PathVariable Long addressId, @RequestBody AddressData addressData){
        addressData.setAddressId(addressId);
        return groceryStoreService.saveAddress(addressId,addressData);
    }
    @DeleteMapping("delete/{groceryStoreId}")
    public Map<String, String> deleteGroceryStore(@PathVariable Long groceryStoreId){
         groceryStoreService.deleteGroceryStore(groceryStoreId);
        return Map.of("message", "Deletion of grocery store with ID="+ groceryStoreId +" is successful");

    }
}

