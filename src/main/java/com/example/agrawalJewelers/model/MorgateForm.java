package com.example.agrawalJewelers.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class MorgateForm {
    @Id
    private Integer id;
    private Customer customerDetails;
    private List<Item> itemList;
    private Integer estValue;
    private String signatureBase64;
    private String signatureType;
    private boolean settled = false;
    private Integer amountPaid = 0;
    private Date createdAt = new Date();
    private String proprietor;
}
