package com.grocerystore.controller.model;

import com.grocerystore.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeData {
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeSocialSecurityNumber;
    private String employeePhoneNumber;
    private String employeeEmail;

    public EmployeeData(Employee employee) {
        employeeId = employee.getEmployeeId();
        employeeFirstName = employee.getEmployeeFirstName();
        employeeLastName = employee.getEmployeeLastName();
        employeeSocialSecurityNumber = employee.getEmployeeSocialSecurityNumber();
        employeePhoneNumber = employee.getEmployeePhoneNumber();
        employeeEmail = employee.getEmployeeEmail();
    }

//    public Employee setEmployee() {
//        Employee employee = new Employee();
//        employee.setEmployeeId(employeeId);
//        employee.setEmployeeFirstName(employeeFirstName);
//        employee.setEmployeeLastName(employeeLastName);
//        employee.setEmployeeSocialSecurityNumber(employeeSocialSecurityNumber);
//        employee.setEmployeePhoneNumber(employeePhoneNumber);
//        employee.setEmployeeEmail(employeeEmail);
//        return employee;
//    }
}
