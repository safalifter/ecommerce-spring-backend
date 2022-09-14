package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerRequest {
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
}
