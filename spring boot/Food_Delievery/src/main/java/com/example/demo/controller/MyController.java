package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repo.*;
import com.example.demo.service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private MenuRepo menuRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private DeliveryRepo deliveryRepo;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private service orderService;

    //  RESTAURANT 
    @PostMapping("/restaurants")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantRepo.save(restaurant));
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<Restaurant>> listRestaurants() {
        return ResponseEntity.ok(restaurantRepo.findAll());
    }

    @PutMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable int id, @RequestBody Restaurant newData) {
        Restaurant rest = restaurantRepo.findById(id).orElseThrow();
        rest.setResName(newData.getResName());
        rest.setResAddress(newData.getResAddress());
        rest.setResRating(newData.getResRating());
        return ResponseEntity.ok(restaurantRepo.save(rest));
    }

    //  MENU 
    @PostMapping("/menu")
    public ResponseEntity<Menu> addMenuItem(@RequestBody Menu menu) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuRepo.save(menu));
    }

    @GetMapping("/menu/restaurant/{id}")
    public ResponseEntity<List<Menu>> getMenuByRestaurant(@PathVariable int id) {
        return ResponseEntity.ok(menuRepo.findByRestaurantId(id));
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<Menu> updateMenuItem(@PathVariable int id, @RequestBody Menu updated) {
        Menu menu = menuRepo.findById(id).orElseThrow();
        menu.setName(updated.getName());
        menu.setPrice(updated.getPrice());
        menu.setAvailable(updated.isAvailable());
        menu.setType(updated.getType());
        return ResponseEntity.ok(menuRepo.save(menu));
    }

    //  CUSTOMER 
    @PostMapping("/customers")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer cust) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerRepo.save(cust));
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer newData) {
        Customer cust = customerRepo.findById(id).orElseThrow();
        cust.setCustomerName(newData.getCustomerName());
        cust.setEmailAddress(newData.getEmailAddress());
        cust.setCustomerPhone(newData.getCustomerPhone());
        return ResponseEntity.ok(customerRepo.save(cust));
    }

    //  ORDER 
    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable int id, @RequestParam String newStatus) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, newStatus));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int id) {
        return ResponseEntity.ok(orderRepo.findById(id).orElseThrow());
    }

    @GetMapping("/orders/customer/{custId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable int custId) {
        return ResponseEntity.ok(orderRepo.findOrdersByCustomerId(custId));
    }

    //  DELIVERY 
    @PutMapping("/delivery/{id}/status")
    public ResponseEntity<Delivery> updateDeliveryStatus(@PathVariable int id, @RequestParam String status) {
        Delivery delivery = deliveryRepo.findById((long) id).orElseThrow();
        delivery.setStatus(status);
        return ResponseEntity.ok(deliveryRepo.save(delivery));
    }

    //  PAYMENT 
    @PostMapping("/payments")
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentRepo.save(payment));
    }

    @GetMapping("/payments/order/{orderId}")
    public ResponseEntity<Payment> getPaymentByOrder(@PathVariable int orderId) {
        return ResponseEntity.ok(paymentRepo.findPaymentByOrderId(orderId));
    }

    // ----------------- TEST -----------------
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("✅ Controller is working fine!");
    }
}
