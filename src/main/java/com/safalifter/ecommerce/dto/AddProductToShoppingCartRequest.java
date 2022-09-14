package com.safalifter.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddProductToShoppingCartRequest {
    @NotNull
    private long productId;
    @NotNull
    private long customerId;
}
