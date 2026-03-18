package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeAlreadyExistException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repo.MyRepo;

@Service
public class MyServices {

    @Autowired
    private MyRepo repo;

    // Get all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = repo.findAll();
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found in the database!");
        }
        return employees;
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(int id) {
        Optional<Employee> emp = repo.findById(id);
        if (emp.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found with ID: " + id);
        }
        return emp;
    }

    // Add new employee
    public String addEmployee(Employee employee) {
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee data cannot be null!");
        }

        // ✅ Handle "already exists" case
        if (repo.existsById(employee.getId())) {
            throw new EmployeeAlreadyExistException("Employee already exists with ID: " + employee.getId());
        }

        repo.save(employee);
        return "Employee added successfully!";
    }

    // Update existing employee
    public String updateEmployee(int id, Employee empDetails) {
        Optional<Employee> optionalEmp = repo.findById(id);
        if (optionalEmp.isPresent()) {
            Employee existingEmp = optionalEmp.get();
            existingEmp.setName(empDetails.getName());
            existingEmp.setAge(empDetails.getAge());
            existingEmp.setSalary(empDetails.getSalary());
            existingEmp.setDesignation(empDetails.getDesignation());
            repo.save(existingEmp);
            return "Employee updated successfully!";
        } else {
            throw new EmployeeNotFoundException("Cannot update — Employee not found with ID: " + id);
        }
    }

    // Delete employee by ID
    public String deleteEmployeeById(int id) {
        if (!repo.existsById(id)) {
            throw new EmployeeNotFoundException("No employee found with ID: " + id);
        }
        repo.deleteById(id);
        return "Employee deleted successfully!";
    }

    // Delete all employees
    public String deleteAllEmployees() {
        if (repo.count() == 0) {
            throw new EmployeeNotFoundException("No employees found to delete!");
        }
        repo.deleteAll();
        return "All employees deleted successfully!";
    }
}
