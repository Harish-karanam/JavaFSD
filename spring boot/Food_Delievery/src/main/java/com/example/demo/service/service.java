package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.InvalidOperationException;

@Service
public class service {

    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final MenuRepo menuRepo;
    private final DeliveryRepo deliveryRepo;
    private final CustomerRepo customerRepo;

    public service(OrderRepo orderRepo,
                   OrderItemRepo orderItemRepo,
                   MenuRepo menuRepo,
                   DeliveryRepo deliveryRepo,
                   CustomerRepo customerRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.menuRepo = menuRepo;
        this.deliveryRepo = deliveryRepo;
        this.customerRepo = customerRepo;
    }

    
     // Create new order with items and auto-assign delivery.
     
    @Transactional
    public Order createOrder(Order order) {

       
        if (order.getCustomer() == null || !customerRepo.existsById(order.getCustomer().getCustomerId())) {
            throw new ResourceNotFoundException("Customer not found or invalid.");
        }

        
        double total = 0.0;
        for (OrderItem item : order.getOrderItems()) {
            Menu menuItem = menuRepo.findById(item.getMenu().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Invalid menu item ID: " + item.getMenu().getId()));

            if (!menuItem.isAvailable()) {
                throw new InvalidOperationException(
                        "Menu item '" + menuItem.getName() + "' is currently unavailable.");
            }

            // Calculate line total //subtotal counting  /2 items * 10 =20
            double lineTotal = menuItem.getPrice() * item.getQuantity();
            item.setLineAmount(lineTotal);
            total += lineTotal;

            item.setOrder(order); //order reference 
        }

        
        order.setTotal_amount((int) total); //converting to int from double
        order.setStatus("PLACED");

        
        Order savedOrder = orderRepo.save(order);
        orderItemRepo.saveAll(order.getOrderItems());

        
        assignDelivery(savedOrder);

        return savedOrder;
    }

 // Checking  status flow:
    //PLACED → PREPARING → OUT_FOR_DELIVERY → DELIVERED
   
  private boolean isValidStatusTransition(String current, String next) {
      return switch (current) {
          case "PLACED" -> next.equals("PREPARING");
          case "PREPARING" -> next.equals("OUT_FOR_DELIVERY");
          case "OUT_FOR_DELIVERY" -> next.equals("DELIVERED");
          default -> false;
      };
  } 
    
     // Update order status with controlled flow.
     
    @Transactional
    public Order updateOrderStatus(int orderId, String newStatus) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        String current = order.getStatus();
        if (!isValidStatusTransition(current, newStatus)) {
            throw new InvalidOperationException(
                    "Invalid status transition from '" + current + "' to '" + newStatus + "'");
        }

        order.setStatus(newStatus);
        return orderRepo.save(order);
    }

  
     

    
     // Stub logic: Auto-assign nearest delivery agent , means randomly picking by partner
     
    private void assignDelivery(Order order) {
        Delivery delivery = new Delivery();
        delivery.setOrder(order);
       delivery.setAgentName(findNearestAgentStub());
        delivery.setStatus("ASSIGNED");

        deliveryRepo.save(delivery);
        order.setDelivery(delivery);
    }

    private String findNearestAgentStub() {
       
        String[] agents = {"APasham kirikir", "Danger balu", "Psyco senu", "Blade babji"};
        int idx = (int) (Math.random() * agents.length);
        return agents[idx];
    }
}
