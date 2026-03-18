package com.example.demo.repo;

import com.example.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.customer.customerId = :custId")
    List<Order> findOrdersByCustomerId(@Param("custId") int custId);
}

