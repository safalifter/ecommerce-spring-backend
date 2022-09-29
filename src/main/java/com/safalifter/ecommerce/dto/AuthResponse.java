package com.safalifter.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthResponse {
    private String email;
    private String role;
    private String token;
}
