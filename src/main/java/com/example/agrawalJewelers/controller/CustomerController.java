package com.example.agrawalJewelers.controller;

import com.example.agrawalJewelers.model.Customer;
import com.example.agrawalJewelers.model.Response;
import com.example.agrawalJewelers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/all")
    public ResponseEntity<Response> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/customer")
    public ResponseEntity<Response> getCustomerById(@RequestParam Integer id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("/customer")
    public ResponseEntity<Response> saveCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @DeleteMapping("/customer")
    public ResponseEntity<Response> deleteCustomer(@RequestParam Integer id) {
        return ResponseEntity.ok(customerService.deleteCustomerById(id));
    }
}
