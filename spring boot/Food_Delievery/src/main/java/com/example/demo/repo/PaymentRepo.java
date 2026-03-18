package com.example.demo.repo;

import com.example.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.order.order_id = :orderId")
    Payment findPaymentByOrderId(@Param("orderId") int orderId);
}

