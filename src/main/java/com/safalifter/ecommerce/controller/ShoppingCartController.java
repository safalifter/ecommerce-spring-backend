package com.safalifter.ecommerce.controller;

import com.safalifter.ecommerce.dto.AddProductToShoppingCartRequest;
import com.safalifter.ecommerce.dto.ShoppingCartDto;
import com.safalifter.ecommerce.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/shoppingCart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCartDto>> getAllShoppingCarts() {
        return ResponseEntity.ok(shoppingCartService.getAllShoppingCarts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDto> getShoppingCartById(@PathVariable Long id) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartById(id));
    }

    @PostMapping("/addProductToShoppingCart")
    public ResponseEntity<ShoppingCartDto> addProductToShoppingCart(@Valid @RequestBody AddProductToShoppingCartRequest request) {
        return new ResponseEntity<>(shoppingCartService.addProductToShoppingCart(request), HttpStatus.CREATED);
    }
}