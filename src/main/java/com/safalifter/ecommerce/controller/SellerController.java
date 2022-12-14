package com.safalifter.ecommerce.controller;

import com.safalifter.ecommerce.dto.SellerDto;
import com.safalifter.ecommerce.dto.UpdateSellerRequest;
import com.safalifter.ecommerce.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/seller")
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public ResponseEntity<List<SellerDto>> getAllSellers() {
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellerDto> getSellerById(@PathVariable Long id) {
        return ResponseEntity.ok(sellerService.getSellerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SellerDto> updateSeller(@PathVariable Long id, @Valid @RequestBody UpdateSellerRequest request) {
        return ResponseEntity.ok(sellerService.updateSeller(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSellerById(@PathVariable Long id) {
        sellerService.deleteSellerById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getSellerByProductId/{id}")
    public ResponseEntity<SellerDto> getSellerByProductId(@PathVariable Long id) {
        return ResponseEntity.ok(sellerService.getSellerByProductId(id));
    }
}