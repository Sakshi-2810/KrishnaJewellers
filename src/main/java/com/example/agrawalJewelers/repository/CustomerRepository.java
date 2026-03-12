package com.example.agrawalJewelers.repository;

import com.example.agrawalJewelers.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {
    Customer findTopByOrderByIdDesc();
}
