package com.safalifter.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreditCardCreateRequest {
    private long id;

    @NotBlank
    private String creditCardNumber;

    @NotBlank
    private String expirationDate;

    @NotBlank
    private String cvc;

    @NotNull
    private Long customerId;
}