package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private long id;
    private String email;
    private Role role;
}