package com.safalifter.ecommerce.controller;

import com.safalifter.ecommerce.dto.CreditCardCreateRequest;
import com.safalifter.ecommerce.dto.CreditCardDto;
import com.safalifter.ecommerce.dto.UpdateCreditCardRequest;
import com.safalifter.ecommerce.service.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/creditCard")
public class CreditCardController {
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping
    public ResponseEntity<CreditCardDto> addCreditCard(@Valid @RequestBody CreditCardCreateRequest request) {
        return new ResponseEntity<>(creditCardService.addCreditCard(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CreditCardDto>> getAllCreditCards() {
        return ResponseEntity.ok(creditCardService.getAllCreditCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardDto> getCreditCardById(@PathVariable Long id) {
        return ResponseEntity.ok(creditCardService.getCreditCardById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardDto> updateCreditCard(@PathVariable Long id, @Valid @RequestBody UpdateCreditCardRequest request) {
        return ResponseEntity.ok(creditCardService.updateCreditCard(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditCardById(@PathVariable Long id) {
        creditCardService.deleteCreditCardById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllCreditCardsByCustomerId/{id}")
    public ResponseEntity<List<CreditCardDto>> getAllCreditCardsByCustomerId(@PathVariable Long id) {
        return ResponseEntity.ok(creditCardService.getAllCreditCardsByCustomerId(id));
    }
}