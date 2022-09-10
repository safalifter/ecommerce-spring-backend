package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.Converter;
import com.safalifter.ecommerce.dto.CreditCardCreateRequest;
import com.safalifter.ecommerce.dto.CreditCardDto;
import com.safalifter.ecommerce.dto.UpdateCreditCardRequest;
import com.safalifter.ecommerce.error.NotFoundException;
import com.safalifter.ecommerce.model.CreditCard;
import com.safalifter.ecommerce.model.Customer;
import com.safalifter.ecommerce.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final CustomerService customerService;
    private final Converter converter;

    public CreditCardService(CreditCardRepository creditCardRepository, CustomerService customerService, Converter converter) {
        this.creditCardRepository = creditCardRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public CreditCardDto addCreditCard(CreditCardCreateRequest request) {
        Customer customer = customerService.findCustomerById(request.getCustomerId());
        CreditCard creditCard = new CreditCard(request.getId(), customer, request.getCreditCardNumber(), request.getExpirationDate(), request.getCvc());
        return converter.creditCardConvertToDto(creditCardRepository.save(creditCard));
    }

    public CreditCardDto getCreditCardById(Long id) {
        CreditCard creditCard = findCreditCardById(id);
        return converter.creditCardConvertToDto(creditCard);
    }

    public CreditCardDto updateCreditCard(Long id, UpdateCreditCardRequest request) {
        CreditCard inDB = findCreditCardById(id);
        inDB.setCreditCardNumber(request.getCreditCardNumber());
        inDB.setExpirationDate(request.getExpirationDate());
        inDB.setCvc(request.getCvc());
        return converter.creditCardConvertToDto(creditCardRepository.save(inDB));
    }

    public void deleteCreditCardById(Long id) {
        CreditCard creditCard = findCreditCardById(id);
        creditCardRepository.delete(creditCard);
    }

    protected CreditCard findCreditCardById(Long id) {
        return creditCardRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
    }

    // if creditCardRepository.findByCustomer_Id(id) return null we can use Optional.ofNullable() but this method's returning empty
    public List<CreditCardDto> getAllCreditCardsByCustomerId(Long id) {
        return creditCardRepository.findByCustomer_Id(id).stream()
                .map(converter::creditCardConvertToDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of))
                .filter(c -> !c.isEmpty()).orElseThrow(() -> new NotFoundException("Customer not found"));
    }
}