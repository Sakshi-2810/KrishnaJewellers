package com.example.agrawalJewelers.controller;

import com.example.agrawalJewelers.model.MorgateForm;
import com.example.agrawalJewelers.model.Response;
import com.example.agrawalJewelers.service.MorgateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MorgateController {
    @Autowired
    MorgateService morgateService;

    @GetMapping("/morgate")
    public ResponseEntity<Response> getMorgateById(@RequestParam Integer id) {
        return ResponseEntity.ok(morgateService.getMorgateFormById(id));
    }

    @PostMapping("/morgate")
    public ResponseEntity<Response> saveMorgateForm(@RequestBody MorgateForm morgateForm) {
        return ResponseEntity.ok(morgateService.saveMorgateForm(morgateForm));
    }

    @GetMapping("/morgate/all")
    public ResponseEntity<Response> getMorgate() {
        return ResponseEntity.ok(morgateService.getAllMorgateForms());
    }

    @DeleteMapping("/morgate")
    public ResponseEntity<Response> deleteMorgateById(@RequestParam Integer id) {
        return ResponseEntity.ok(morgateService.deleteMorgateFormById(id));
    }

    @GetMapping("/morgate/active")
    public ResponseEntity<Response> getActiveMorgateForms() {
        return ResponseEntity.ok(morgateService.getActiveMorgateForms());
    }

    @PostMapping("/morgate/close")
    public ResponseEntity<Response> closeMorgateForm(@RequestParam Integer id) {
        return ResponseEntity.ok(morgateService.closeMorgateForm(id));
    }

    @GetMapping("/morgate/customer")
    public ResponseEntity<Response> getMorgateFormsByCustomerId(@RequestParam Integer customerId) {
        return ResponseEntity.ok(morgateService.getMorgateFormsByCustomerId(customerId));
    }
}
