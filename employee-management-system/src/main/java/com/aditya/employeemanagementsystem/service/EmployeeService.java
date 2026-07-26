package com.aditya.employeemanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.aditya.employeemanagementsystem.dto.EmployeeDTO;
import com.aditya.employeemanagementsystem.entity.Employee;
import com.aditya.employeemanagementsystem.mapper.EmployeeMapper;
import com.aditya.employeemanagementsystem.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    // Create Employee
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        Employee employee = EmployeeMapper.mapToEntity(employeeDTO);

        Employee savedEmployee = repository.save(employee);

        return EmployeeMapper.mapToDTO(savedEmployee);
    }

    // Get All Employees
    public List<EmployeeDTO> getAllEmployees() {

        return repository.findAll()
                .stream()
                .map(EmployeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get Employee By Id
    public EmployeeDTO getEmployeeById(Long id) {

        Employee employee = repository.findById(id).orElse(null);

        return employee == null ? null : EmployeeMapper.mapToDTO(employee);
    }

    // Update Employee
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {

        Employee employee = repository.findById(id).orElse(null);

        if (employee != null) {

            employee.setName(employeeDTO.getName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setDepartment(employeeDTO.getDepartment());
            employee.setSalary(employeeDTO.getSalary());

            Employee updatedEmployee = repository.save(employee);

            return EmployeeMapper.mapToDTO(updatedEmployee);
        }

        return null;
    }

    // Delete Employee
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    // Search by Name
    public List<EmployeeDTO> getEmployeesByName(String name) {

        return repository.findByName(name)
                .stream()
                .map(EmployeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    // Search by Department
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {

        return repository.findByDepartment(department)
                .stream()
                .map(EmployeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    // Pagination
    public Page<Employee> getEmployeesWithPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable);
    }

    // Sorting
    public List<Employee> getEmployeesWithSorting(String field) {

        return repository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    // Pagination + Sorting
    public Page<Employee> getEmployeesWithPaginationAndSorting(int page,
                                                               int size,
                                                               String field) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(field));

        return repository.findAll(pageable);
    }
}