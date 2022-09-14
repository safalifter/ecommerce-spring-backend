package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.Converter;
import com.safalifter.ecommerce.dto.CustomerCreateRequest;
import com.safalifter.ecommerce.dto.CustomerDto;
import com.safalifter.ecommerce.dto.UpdateCustomerRequest;
import com.safalifter.ecommerce.error.NotFoundException;
import com.safalifter.ecommerce.model.Customer;
import com.safalifter.ecommerce.model.ShoppingCart;
import com.safalifter.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final Converter converter;


    public CustomerService(CustomerRepository customerRepository, Converter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    public CustomerDto createCustomer(CustomerCreateRequest request) {
        Customer customer = Customer.builder()
                .password(request.getPassword())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .creditCards(Set.of())
                .shoppingCart(new ShoppingCart()).build();
        return converter.customerConvertToDto(customerRepository.save(customer));
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(converter::customerConvertToDto).collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        return converter.customerConvertToDto(customer);
    }

    public CustomerDto updateCustomer(Long id, UpdateCustomerRequest request) {
        Customer inDB = findCustomerById(id);
        inDB.setPassword(request.getPassword());
        inDB.setFirstName(request.getFirstName());
        inDB.setLastName(request.getLastName());
        inDB.setGender(request.getGender());
        return converter.customerConvertToDto(customerRepository.save(inDB));
    }

    public void deleteCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
    }

    protected Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
    }
}