package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Menu item name cannot be blank")
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    private int price;

    private String type; // veg | non-veg
    private boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference //child
    private Restaurant restaurant;
}
