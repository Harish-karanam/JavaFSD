package com.example.demo.model;

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
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long payment_id;

    //one payment for one order
    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private Order order;  

    private int amount;

    private String mode;   // COD | UPI | CARD
    private String status; // SUCCESS | FAILED
}
