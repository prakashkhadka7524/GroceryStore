package com.grocerystore.dao;

import com.grocerystore.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
