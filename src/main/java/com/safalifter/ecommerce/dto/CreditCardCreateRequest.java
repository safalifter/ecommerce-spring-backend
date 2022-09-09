package com.safalifter.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardCreateRequest {
    private Long id;
    private String creditCardNumber;
    private String expirationDate;
    private String cvc;
    private Long customerId;
}