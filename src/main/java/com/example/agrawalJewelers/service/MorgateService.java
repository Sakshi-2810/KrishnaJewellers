package com.example.agrawalJewelers.service;

import com.example.agrawalJewelers.model.MorgateForm;
import com.example.agrawalJewelers.model.Response;
import com.example.agrawalJewelers.repository.MorgateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class MorgateService {
    @Autowired
    private MorgateRepository morgateRepository;
    @Autowired
    private CustomerService customerService;

    public Integer getNextMorgateId() {
        var lastMorgate = morgateRepository.findTopByOrderByIdDesc();
        return (lastMorgate != null) ? lastMorgate.getId() + 1 : 1;
    }

    public Response saveMorgateForm(MorgateForm morgateForm) {
        if (morgateForm.getId() != null && morgateRepository.existsById(morgateForm.getId())) {
            log.info("Morgate form with ID: {} already exists. Updating existing form.", morgateForm.getId());
        } else {
            morgateForm.setId(getNextMorgateId());
        }
        customerService.saveCustomer(morgateForm.getCustomerDetails());
        log.info("Saving morgate form with ID: {}", morgateForm.getId());
        morgateForm.setSettled(Objects.equals(morgateForm.getAmountPaid(), morgateForm.getEstValue()));
        morgateRepository.save(morgateForm);
        return new Response("Morgate form saved successfully with ID: " + morgateForm.getId(), morgateForm);
    }

    public Response getMorgateFormById(Integer id) {
        var morgateForm = morgateRepository.findById(id).orElse(null);
        if (morgateForm == null) {
            log.warn("Morgate form not found with ID: {}", id);
            return new Response("Morgate form not found with ID: " + id, null);
        }
        log.info("Fetched morgate form with ID: {}", id);
        return new Response("Morgate form fetched successfully with ID: " + id, morgateForm);
    }

    public Response deleteMorgateFormById(Integer id) {
        if (!morgateRepository.existsById(id)) {
            log.warn("Morgate form not found for deletion with ID: {}", id);
            return new Response("Morgate form not found for deletion with ID: " + id, null);
        }
        morgateRepository.deleteById(id);
        log.info("Deleted morgate form with ID: {}", id);
        return new Response("Morgate form deleted successfully with ID: " + id, null);
    }

    public Response getAllMorgateForms() {
        var morgateForms = morgateRepository.findAll();
        log.info("Fetched all morgate forms, count: {}", morgateForms.size());
        return new Response("All morgate forms fetched successfully", morgateForms);
    }

    public Response getActiveMorgateForms() {
        var morgateForms = morgateRepository.findBySettledFalse();
        log.info("Fetched active morgate forms, count: {}", morgateForms.size());
        return new Response("Active morgate forms fetched successfully", morgateForms);
    }

    public Response getMorgateFormsByCustomerId(Integer customerId) {
        var morgateForms = morgateRepository.findByCustomerDetails_Id(customerId);
        log.info("Fetched morgate forms for customer ID: {}, count: {}", customerId, morgateForms.size());
        return new Response("Morgate forms fetched successfully for customer ID: " + customerId, morgateForms);
    }

    public Response closeMorgateForm(Integer id) {
        var morgateForm = morgateRepository.findById(id).orElse(null);
        if (morgateForm == null) {
            log.warn("Morgate form not found for closing with ID: {}", id);
            return new Response("Morgate form not found for closing with ID: " + id, null);
        }
        morgateForm.setSettled(true);
        morgateForm.setAmountPaid(morgateForm.getEstValue());
        morgateRepository.save(morgateForm);
        log.info("Closed morgate form with ID: {}", id);
        return new Response("Morgate form closed successfully with ID: " + id, morgateForm);
    }
}
