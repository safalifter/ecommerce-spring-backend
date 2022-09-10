package com.safalifter.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductCreateRequest {
    private long id;

    @NotBlank
    private String name;

    private String description;

    private double price;

    @NotNull
    private Long sellerId;
}
