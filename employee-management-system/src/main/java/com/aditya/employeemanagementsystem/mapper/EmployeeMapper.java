package com.aditya.employeemanagementsystem.mapper;

import com.aditya.employeemanagementsystem.dto.EmployeeDTO;
import com.aditya.employeemanagementsystem.entity.Employee;

public class EmployeeMapper {

    // Convert Entity to DTO
    public static EmployeeDTO mapToDTO(Employee employee) {

        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment(),
                employee.getSalary()
        );
    }

    // Convert DTO to Entity
    public static Employee mapToEntity(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());

        return employee;
    }
}