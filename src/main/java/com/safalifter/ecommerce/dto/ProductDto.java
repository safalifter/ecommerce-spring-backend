package com.safalifter.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private Long sellerId;
}