package com.safalifter.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateCreditCardRequest {
    @NotBlank
    private String creditCardNumber;

    @NotBlank
    private String expirationDate;

    @NotBlank
    private String cvc;
}
