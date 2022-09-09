package com.safalifter.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCreditCardRequest {
    private String creditCardNumber;
    private String expirationDate;
    private String cvc;
}
