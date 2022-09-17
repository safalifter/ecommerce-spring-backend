package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.Gender;
import com.safalifter.ecommerce.model.Role;
import com.safalifter.ecommerce.model.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Set<CreditCardDto> creditCards;
    private ShoppingCart shoppingCart;
    private Role role;
}
