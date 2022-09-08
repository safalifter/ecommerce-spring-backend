package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.Converter;
import com.safalifter.ecommerce.dto.CustomerCreateRequest;
import com.safalifter.ecommerce.dto.CustomerDto;
import com.safalifter.ecommerce.dto.UpdateCustomerRequest;
import com.safalifter.ecommerce.error.UserNotFoundException;
import com.safalifter.ecommerce.model.Customer;
import com.safalifter.ecommerce.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())

                .build();
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
        inDB.setUsername(request.getUsername());
        inDB.setPassword(request.getPassword());
        inDB.setFirstName(request.getFirstName());
        inDB.setLastName(request.getLastName());
        inDB.setGender(request.getGender());
        return converter.customerConvertToDto(customerRepository.save(inDB));
    }

    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customerRepository.delete(customer);
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}