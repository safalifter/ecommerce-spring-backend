package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateCustomerRequest {
    @Size(min = 8)
    @Pattern(message = "password must have at least 1 uppercase 1 lowercase and 1 number", regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    private String password;
    private String firstName;
    private String lastName;
    private Gender gender;
}
