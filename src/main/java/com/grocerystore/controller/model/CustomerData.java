package com.grocerystore.controller.model;

import com.grocerystore.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CustomerData {
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String customerEmail;

    public CustomerData(Customer customer) {
        customerId = customer.getCustomerId();
        customerFirstName = customer.getCustomerFirstName();
        customerLastName = customer.getCustomerLastName();
        customerPhone = customer.getCustomerPhone();
        customerEmail = customer.getCustomerEmail();
    }

//    public Customer setCustomer() {
//        Customer customer = new Customer();
//        customer.setCustomerId(customerId);
//        customer.setCustomerFirstName(customerFirstName);
//        customer.setCustomerLastName(customerLastName);
//        customer.setCustomerPhone(customerPhone);
//        customer.setCustomerEmail(customerEmail);
//        return customer;
//
//    }
}
