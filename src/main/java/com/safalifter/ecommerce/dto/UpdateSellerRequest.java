package com.safalifter.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSellerRequest {
    private String password;
    private String companyName;
    private String about;
}