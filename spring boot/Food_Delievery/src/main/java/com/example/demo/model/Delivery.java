package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agentName;
    private String status; // ASSIGNED | PICKED | EN_ROUTE | DELIVERED
    private String eta;    // estimated time of arrival

    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    @JsonBackReference
    private Order order;
}
