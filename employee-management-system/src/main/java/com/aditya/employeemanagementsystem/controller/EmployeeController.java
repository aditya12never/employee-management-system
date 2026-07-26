package com.aditya.employeemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.aditya.employeemanagementsystem.dto.EmployeeDTO;
import com.aditya.employeemanagementsystem.entity.Employee;
import com.aditya.employeemanagementsystem.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // Create Employee
    @PostMapping
    public EmployeeDTO saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return service.saveEmployee(employeeDTO);
    }

    // Get All Employees
    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return service.getAllEmployees();
    }

    // Get Employee By ID
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    // Update Employee
    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id,
                                      @Valid @RequestBody EmployeeDTO employeeDTO) {
        return service.updateEmployee(id, employeeDTO);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "Employee deleted successfully";
    }

    // Search by Name
    @GetMapping("/search/name/{name}")
    public List<EmployeeDTO> getEmployeesByName(@PathVariable String name) {
        return service.getEmployeesByName(name);
    }

    // Search by Department
    @GetMapping("/search/department/{department}")
    public List<EmployeeDTO> getEmployeesByDepartment(@PathVariable String department) {
        return service.getEmployeesByDepartment(department);
    }

    // Pagination
    @GetMapping("/page")
    public Page<Employee> getEmployeesWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        return service.getEmployeesWithPagination(page, size);
    }

    // Sorting
    @GetMapping("/sort")
    public List<Employee> getEmployeesWithSorting(
            @RequestParam String field) {

        return service.getEmployeesWithSorting(field);
    }

    // Pagination + Sorting
    @GetMapping("/page-sort")
    public Page<Employee> getEmployeesWithPaginationAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field) {

        return service.getEmployeesWithPaginationAndSorting(page, size, field);
    }
}