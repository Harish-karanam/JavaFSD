package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private Double lineAmount; //total order amount

    //many to one
    @ManyToOne //many orders belongs to one man
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    //many to one 
    @ManyToOne //manyorders from one menu
    @JoinColumn(name = "menu_item_id")
    private Menu menu;
}
