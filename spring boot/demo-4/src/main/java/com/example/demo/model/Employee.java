package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;  // ✅ Import validation annotations
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@JsonIgnoreProperties("employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Positive(message = "ID must be a positive number") // ID should always be a positive number
    private int id;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only alphabets and spaces") // only letters allowed
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 60, message = "Age must not exceed 60")
    private int age;

    @Min(value = 25000, message = "Salary must be at least 25,000")
    private int salary;

    @NotBlank(message = "Designation is required")
    private String designation;
}
