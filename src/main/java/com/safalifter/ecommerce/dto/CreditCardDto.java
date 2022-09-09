package com.safalifter.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreditCardDto {
    private Long id;
    private String creditCardNumber;
    private String expirationDate;
    private String cvc;
    private Long customerId;
}