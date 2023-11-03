package com.grocerystore.controller;

import com.grocerystore.controller.model.CustomerData;
import com.grocerystore.controller.model.EmployeeData;
import com.grocerystore.controller.model.GroceryStoreData;
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
        log.info("Creating Grocery Store {}", groceryStoreData);
        return groceryStoreService.saveGroceryData(groceryStoreData);
    }

    @PostMapping("{groceryStoreId}/employee")
    public EmployeeData insertEmployee(@PathVariable Long groceryStoreId, @RequestBody EmployeeData employeeData) {
        log.info("Creating Employee of Grocery Store {}", employeeData);
        groceryStoreService.findGroceryStoreById(groceryStoreId);
        return groceryStoreService.saveEmployee(groceryStoreId, employeeData);
    }


    @PostMapping("{groceryStoreId}/customer")
    public CustomerData insertCustomer(@PathVariable Long groceryStoreId, @RequestBody CustomerData customerData) {
        log.info("Creating Customer Grocery Store {} ", customerData);
        groceryStoreService.findGroceryStoreById(groceryStoreId);
        return groceryStoreService.saveCustomer(groceryStoreId, customerData);
    }

    @GetMapping("/all")
    public List<GroceryStoreData> allGroceryStore(){
        log.info("Getting all Grocery Store []");
        return groceryStoreService.retriveAllGroceryStores();
    }
    @GetMapping("/{groceryStoreId}")
    public GroceryStoreData groceryStoreById(@PathVariable Long groceryStoreId){
        log.info("Getting Grocery Store of {}", groceryStoreId);
        return groceryStoreService.retriveGroceryStoreById(groceryStoreId);
    }
    @PutMapping("{groceryStoreId}/update")
    public GroceryStoreData updateGroceryStore(@PathVariable Long groceryStoreId, @RequestBody GroceryStoreData groceryStoreData){
        log.info("Updating Grocery Store of ID {}, {}",groceryStoreId, groceryStoreData);
        groceryStoreData.setGroceryStoreId(groceryStoreId);
        return groceryStoreService.updateGroceryStore(groceryStoreId,groceryStoreData);
    }
    @PutMapping("{groceryStoreId}/employee-update/{employeeId}")
    public EmployeeData updateEmployee( @PathVariable Long groceryStoreId,@PathVariable Long employeeId, @RequestBody EmployeeData employeeData){
        log.info("Updating Employee of ID {}, {}",employeeId, employeeData);
        employeeData.setEmployeeId(employeeId);
        return groceryStoreService.saveEmployee(groceryStoreId, employeeData);
    }
    @PutMapping("{groceryStoreId}/customer-update/{customerId}")
    public CustomerData updateCustomer( @PathVariable Long groceryStoreId,@PathVariable Long customerId, @RequestBody CustomerData customerData
    ){
        log.info("Updating Customer of ID {}, {}",customerId, customerData);
        customerData.setCustomerId(customerId);
        return groceryStoreService.saveCustomer(groceryStoreId, customerData);
    }

    @DeleteMapping("delete/{groceryStoreId}")
    public Map<String, String> deleteGroceryStore(@PathVariable Long groceryStoreId){
        log.info("Deleting grocery store of ID {}",groceryStoreId );
        groceryStoreService.deleteGroceryStore(groceryStoreId);
        return Map.of("message", "Deletion of grocery store with ID="+ groceryStoreId +" is successful");

    }
    @GetMapping("/all-employee")
    public List<EmployeeData> allEmployee(){
        log.info("Getting all Employee []");
        return groceryStoreService.retriveAllEployee();
    }
    @GetMapping("/all-customer")
    public List<CustomerData> allCustomer(){
        log.info("Getting all Customer []");
        return groceryStoreService.retriveAllCustomer();
    }
}
