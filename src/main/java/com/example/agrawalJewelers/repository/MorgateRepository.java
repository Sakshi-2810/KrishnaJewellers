package com.example.agrawalJewelers.repository;

import com.example.agrawalJewelers.model.MorgateForm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MorgateRepository extends MongoRepository<MorgateForm, Integer> {
    MorgateForm findTopByOrderByIdDesc();
    List<MorgateForm> findBySettledFalse();

    List<MorgateForm> findByCustomerDetails_Id(Integer customerId);
}
