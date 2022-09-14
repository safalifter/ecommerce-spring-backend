package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ShoppingCartDto {
    private long id;
    private List<Product> products;
    private double totalPrice;
}