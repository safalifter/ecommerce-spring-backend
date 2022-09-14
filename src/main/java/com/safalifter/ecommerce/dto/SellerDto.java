package com.safalifter.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SellerDto {
    private long id;
    private String email;
    private String companyName;
    private String about;
    private List<ProductDto> products;
}
