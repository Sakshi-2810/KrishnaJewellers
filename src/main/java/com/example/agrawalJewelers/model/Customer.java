package com.example.agrawalJewelers.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Customer {
    @Id
    private Integer id;
    private String name;
    private String fatherName;
    private String address;
    private String phoneNumber;
    private String aadharNumber;
}
