package com.example.agrawalJewelers.service;

import com.example.agrawalJewelers.model.Customer;
import com.example.agrawalJewelers.model.Response;
import com.example.agrawalJewelers.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public int getNextCustomerId() {
        var lastCustomer = customerRepository.findTopByOrderByIdDesc();
        return (lastCustomer != null) ? lastCustomer.getId() + 1 : 1;
    }

    public Response saveCustomer(Customer customer) {
        if (customer.getId() != null && customerRepository.existsById(customer.getId())) {
             log.info("Customer with ID: " + customer.getId() + " already exists. Updating existing customer.");
        } else {
            customer.setId(getNextCustomerId());
        }
        customerRepository.save(customer);
        return new Response("Customer saved successfully with ID: " + customer.getId(), customer);
    }

    public Response getCustomerById(Integer id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return new Response("Customer not found with ID: " + id, null);
        }
        return new Response("Customer fetched successfully with ID: " + id, customer);
    }

    public Response deleteCustomerById(Integer id) {
        if (!customerRepository.existsById(id)) {
            return new Response("Customer not found for deletion with ID: " + id, null);
        }
        customerRepository.deleteById(id);
        return new Response("Customer deleted successfully with ID: " + id, null);
    }

    public Response getAllCustomers() {
        var customers = customerRepository.findAll();
        return new Response("All customers fetched successfully", customers);
    }
}
