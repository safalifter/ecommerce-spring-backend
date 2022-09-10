package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.CreditCard;
import com.safalifter.ecommerce.model.Customer;
import com.safalifter.ecommerce.model.Product;
import com.safalifter.ecommerce.model.Seller;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Converter {
    public SellerDto sellerConvertToDto(Seller from) {
        return new SellerDto(from.getUsername(), from.getEmail(), from.getCompanyName(), from.getAbout(), from.getProducts());
    }

    public CustomerDto customerConvertToDto(Customer from) {
        return new CustomerDto(from.getUsername(), from.getEmail(), from.getFirstName(), from.getLastName(), from.getGender(),
                from.getCreditCards().stream().map(this::creditCardConvertToDto).collect(Collectors.toSet()));
    }

    public CreditCardDto creditCardConvertToDto(CreditCard from) {
        return new CreditCardDto(from.getId(), from.getCreditCardNumber(), from.getExpirationDate(), from.getCvc(), from.getCustomer().getId());
    }

    public ProductDto productConvertToDto(Product from) {
        return new ProductDto(from.getId(), from.getName(), from.getDescription(), from.getPrice(), from.getSeller().getId());
    }
}