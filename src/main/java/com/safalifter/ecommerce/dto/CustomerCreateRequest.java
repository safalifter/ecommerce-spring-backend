package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCreateRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
}
