package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Employee;
import com.example.demo.service.MyServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")  // Good practice to prefix all routes
public class MyController {

    @Autowired
    private MyServices service;

    // Get all employees
    @GetMapping("/allEmployees")
    public List<Employee> allEmployees() {
        return service.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/employee/{id}")
    public Optional<Employee> allEmployeesByid(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    // Add a new employee
    @PostMapping("/addEmp")
    public String addEmployee(@Valid @RequestBody Employee employee) {
        return service.addEmployee(employee);
    }

    // Update existing employee
    @PutMapping("/updateEmp/{id}")
    public String updateEmployee(@PathVariable int id,@Valid @RequestBody Employee employee) {
        return service.updateEmployee(id, employee);
    }

    // Delete employee by ID
    @DeleteMapping("/deleteEmp/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return service.deleteEmployeeById(id);
    }

    // Delete all employees
    @DeleteMapping("/deleteAllEmps")
    public String deleteAllEmployees() {
        return service.deleteAllEmployees();
    }
}
