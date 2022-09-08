package com.safalifter.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SellerDto {
    private String username;
    private String email;
    private String companyName;
    private String about;
}
