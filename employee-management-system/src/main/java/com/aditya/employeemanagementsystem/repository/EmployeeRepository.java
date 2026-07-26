package com.aditya.employeemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aditya.employeemanagementsystem.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Search employee by name
    List<Employee> findByName(String name);

    // Search employee by department
    List<Employee> findByDepartment(String department);

}