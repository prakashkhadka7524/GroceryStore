package com.grocerystore.service;

import com.grocerystore.controller.model.AddressData;
import com.grocerystore.controller.model.CustomerData;
import com.grocerystore.controller.model.EmployeeData;
import com.grocerystore.controller.model.GroceryStoreData;
import com.grocerystore.dao.AddressDao;
import com.grocerystore.dao.CustomerDao;
import com.grocerystore.dao.EmployeeDao;
import com.grocerystore.dao.GroceryStoreDao;
import com.grocerystore.entity.Address;
import com.grocerystore.entity.Customer;
import com.grocerystore.entity.Employee;
import com.grocerystore.entity.GroceryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class GroceryStoreService {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    private GroceryStoreDao groceryStoreDao;
    @Autowired
    private AddressDao addressDao;

    public GroceryStoreService() {
    }

    public GroceryStoreData saveGroceryData(GroceryStoreData groceryStoreData) {
        Long groceryStoreId = groceryStoreData.getGroceryStoreId();
        GroceryStore groceryStore = findOrCreateGroceryStore(groceryStoreId);
        setGroceryStore(groceryStore, groceryStoreData);
        return new GroceryStoreData(groceryStoreDao.save(groceryStore));
    }

    private void setGroceryStore(GroceryStore groceryStore, GroceryStoreData groceryStoreData) {
        groceryStore.setGroceryStoreId(groceryStoreData.getGroceryStoreId());
        groceryStore.setStoreName(groceryStoreData.getStoreName());
    }

    private GroceryStore findOrCreateGroceryStore(Long groceryStoreId) {
        GroceryStore groceryStore;
        if (Objects.isNull(groceryStoreId)) {
            groceryStore = new GroceryStore();
        } else {
            groceryStore = findGroceryStoreById(groceryStoreId);
        }
        return groceryStore;
    }

    public GroceryStore findGroceryStoreById(Long groceryStoreId) {
        return groceryStoreDao.findById(groceryStoreId).orElseThrow(() ->
                new NoSuchElementException("Grocery Store with Id " + groceryStoreId + "was not found"));
    }

    @Transactional(readOnly = false)
    public EmployeeData saveEmployee(Long groceryId, EmployeeData employeeData) {
        GroceryStore groceryStore = findGroceryStoreById(groceryId);
        Employee employee = findOrCreateEmployee(employeeData.getEmployeeId());
        setEmployee(employee, employeeData);
        employee.setGroceryStore(groceryStore);
        groceryStore.getEmployees().add(employee);
        Employee dbEmployee = employeeDao.save(employee);
        return new EmployeeData(dbEmployee);

    }

    private void setEmployee(Employee employee, EmployeeData employeeData) {
        employee.setEmployeeId(employeeData.getEmployeeId());
        employee.setEmployeeFirstName(employeeData.getEmployeeFirstName());
        employee.setEmployeeLastName(employeeData.getEmployeeLastName());
        employee.setEmployeeSocialSecurityNumber(employeeData.getEmployeeSocialSecurityNumber());
        employee.setEmployeePhoneNumber(employeeData.getEmployeePhoneNumber());
        employee.setEmployeeEmail(employeeData.getEmployeeEmail());
    }

    private Employee findOrCreateEmployee(Long employeeId) {
        Employee employee;
        if (Objects.isNull(employeeId)) {
            employee = new Employee();
        } else {
            employee = findEmployeeById(employeeId);
        }
        return employee;
    }

    private Employee findEmployeeById(Long employeeId) {
        return employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee with Id " + employeeId
                + " was not found"));
    }

    @Transactional(readOnly = false)
    public CustomerData saveCustomer(Long groceryStoreId, CustomerData customerData) {
        GroceryStore groceryStore = findGroceryStoreById(groceryStoreId);
        Customer customer = findOrCreateCustomer(groceryStoreId, customerData.getCustomerId());
        setCustomerFields(customer, customerData);
        customer.getGroceryStores().add(groceryStore);
        groceryStore.getCustomers().add(customer);
        Customer dbCustomer = customerDao.save(customer);
        return new CustomerData(dbCustomer);
    }

    private void setCustomerFields(Customer customer, CustomerData customerData) {
        customer.setCustomerId(customerData.getCustomerId());
        customer.setCustomerFirstName(customerData.getCustomerFirstName());
        customer.setCustomerLastName(customerData.getCustomerLastName());
        customer.setCustomerPhone(customerData.getCustomerPhone());
        customer.setCustomerEmail(customerData.getCustomerEmail());
    }

    private Customer findOrCreateCustomer(Long groceryStoreId, Long customerId) {
        Customer customer;
        if (Objects.isNull(customerId)) {
            customer = new Customer();
        } else {
            customer = findCustomerById(groceryStoreId, customerId);
        }
        return customer;
    }

    private Customer findCustomerById(Long groceryStoreId, Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer with Id " + customerId
                        + " was not found"));
        boolean found = false;
        for (GroceryStore groceryStore : customer.getGroceryStores()) {
            if (Objects.equals(groceryStore.getGroceryStoreId(), groceryStoreId)) {
                found = true;
                break;
            }
            throw new IllegalArgumentException("Customer with ID" + customerId + "not found in customer");
        }


        return customer;
    }
@Transactional(readOnly = true)
    public List<GroceryStoreData> retriveAllGroceryStores() {
        List<GroceryStore> groceryStores=groceryStoreDao.findAll();
        List<GroceryStoreData> groceryStoreDataList= new LinkedList<>();
        for (GroceryStore groceryStore : groceryStores){
            GroceryStoreData groceryStoreData= new GroceryStoreData(groceryStore);
            groceryStoreData.getEmployees().clear();
            groceryStoreData.getCustomers().clear();
            groceryStoreDataList.add(groceryStoreData);
        }
        return groceryStoreDataList;
    }
    public GroceryStoreData retriveGroceryStoreById(Long groceryStoreId) {
        GroceryStore groceryStore = findGroceryStoreById(groceryStoreId);
        return new GroceryStoreData(groceryStore);
    }

    public AddressData saveAddress(Long groceryStoreId, AddressData addressData) {
        GroceryStore groceryStore = findGroceryStoreById(groceryStoreId);
        Address address = findOrCreateAddress(groceryStoreId, addressData.getAddressId());
        setAddress(address, addressData);
        Address dbAddress = addressDao.save(address);
        return new AddressData(dbAddress);
    }
    public void setAddress(Address address, AddressData addressData) {
        address.setAddressId(addressData.getAddressId());
        address.setStreetAddress(addressData.getStreetAddress());
        address.setCity(addressData.getCity());
        address.setZip(addressData.getZip());
        address.setState(addressData.getState());
        address.setCountry(addressData.getCountry());
    }

        private Address findOrCreateAddress(Long groceryStoreId, Long addressId) {
        Address address;
        if (Objects.isNull(addressId)){
            address= new Address();
        }
        else {
            address=findAddressById(groceryStoreId, addressId);
        }
        return address;
    }

    private Address findAddressById(Long groceryStoreId, Long addressId) {
        Address address = addressDao.findById(addressId)
                .orElseThrow(() -> new NoSuchElementException("Address with Id " + addressId
                        + " was not found"));
        return address;
    }
    public GroceryStoreData updateGroceryStore(Long groceryStoreId, GroceryStoreData groceryStoreData){
      findGroceryStoreById(groceryStoreId);
     return saveGroceryData(groceryStoreData);
        }

    public void deleteGroceryStore(Long groceryStoreId) {
    groceryStoreDao.findById(groceryStoreId);
      groceryStoreDao.deleteById(groceryStoreId);

    }
}




