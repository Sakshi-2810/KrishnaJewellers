package com.example.agrawalJewelers.model;

import lombok.Data;

@Data
public class Item {
    private String name;
    private double grossWeight;
    private double netWeight;
    private String purity;
}
