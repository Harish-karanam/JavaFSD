package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    private String status; // PLACED | PREPARING | OUT_FOR_DELIVERY | DELIVERED | CANCELLED
    private int total_amount;

    //many to one 
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference 
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Delivery delivery;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

   

    @PrePersist //calls before saving
    @PreUpdate //calls before updating
    public void calculateTotal() {
        if (orderItems != null && !orderItems.isEmpty()) {
            total_amount = (int) orderItems.stream()
                    .mapToDouble(item -> item.getLineAmount() != null ? item.getLineAmount() : 0.0)
                    .sum();
        }
    }
}
