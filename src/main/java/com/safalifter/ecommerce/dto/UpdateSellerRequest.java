package com.safalifter.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateSellerRequest {
    @Size(min = 8)
    @Pattern(message = "password must have at least 1 uppercase 1 lowercase and 1 number", regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;
    private String companyName;
    private String about;
}