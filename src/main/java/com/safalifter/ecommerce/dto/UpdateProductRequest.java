package com.safalifter.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {
    private String name;
    private String description;
    private double price;
    private int quantity;
}