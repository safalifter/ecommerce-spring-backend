package com.safalifter.ecommerce.controller;

import com.safalifter.ecommerce.dto.ProductCreateRequest;
import com.safalifter.ecommerce.dto.ProductDto;
import com.safalifter.ecommerce.dto.UpdateProductRequest;
import com.safalifter.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductCreateRequest request) {
        return new ResponseEntity<>(productService.addProduct(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(productService.getAllProducts(name));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllProductsBySellerId/{id}")
    public ResponseEntity<List<ProductDto>> getAllProductsBySellerId(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getAllProductsBySellerId(id));
    }
}