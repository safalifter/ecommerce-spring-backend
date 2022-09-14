package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter {
    public SellerDto sellerConvertToDto(Seller from) {
        return new SellerDto(from.getId(),from.getEmail(),
                from.getCompanyName(), from.getAbout(),
                from.getProducts().stream().map(this::productConvertToDto).collect(Collectors.toList()));
    }

    public CustomerDto customerConvertToDto(Customer from) {
        return new CustomerDto(from.getId(),from.getEmail(),
                from.getFirstName(), from.getLastName(), from.getGender(),
                from.getCreditCards().stream().map(this::creditCardConvertToDto).collect(Collectors.toSet()),
                from.getShoppingCart());
    }

    public CreditCardDto creditCardConvertToDto(CreditCard from) {
        return new CreditCardDto(from.getId(), from.getCreditCardNumber(), from.getExpirationDate(), from.getCvc(), from.getCustomer().getId());
    }

    public ProductDto productConvertToDto(Product from) {
        return new ProductDto(from.getId(), from.getName(), from.getDescription(), from.getPrice(), from.getQuantity(), from.getSeller().getId());
    }

    public ShoppingCartDto shoppingCartConvertToDto(ShoppingCart from) {
        return new ShoppingCartDto(from.getId(), from.getProducts(), from.getTotalPrice());
    }
}