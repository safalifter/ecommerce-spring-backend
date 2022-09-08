package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
}
