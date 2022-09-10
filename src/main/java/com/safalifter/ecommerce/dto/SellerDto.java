package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SellerDto {
    private String username;
    private String email;
    private String companyName;
    private String about;
    private List<Product> products;
}
