package com.safalifter.ecommerce.dto;

import com.safalifter.ecommerce.model.Customer;
import com.safalifter.ecommerce.model.Seller;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public SellerDto sellerConvertToDto(Seller from) {
        return new SellerDto(from.getUsername(), from.getEmail(), from.getCompanyName(), from.getAbout());
    }

    public CustomerDto customerConvertToDto(Customer from) {
        return new CustomerDto(from.getUsername(), from.getEmail(), from.getFirstName(), from.getLastName(), from.getGender());
    }
}
