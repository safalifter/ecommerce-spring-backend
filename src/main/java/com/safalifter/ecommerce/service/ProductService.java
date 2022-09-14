package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.Converter;
import com.safalifter.ecommerce.dto.ProductCreateRequest;
import com.safalifter.ecommerce.dto.ProductDto;
import com.safalifter.ecommerce.dto.UpdateProductRequest;
import com.safalifter.ecommerce.error.NotFoundException;
import com.safalifter.ecommerce.model.Product;
import com.safalifter.ecommerce.model.Seller;
import com.safalifter.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SellerService sellerService;
    private final Converter converter;

    public ProductService(ProductRepository productRepository, SellerService sellerService, Converter converter) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
        this.converter = converter;
    }

    public ProductDto addProduct(ProductCreateRequest request) {
        Seller seller = sellerService.findSellerById(request.getSellerId());
        Product product = new Product(request.getId(), request.getName(), request.getDescription(), request.getPrice(), request.getQuantity(), seller);
        return converter.productConvertToDto(productRepository.save(product));
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(converter::productConvertToDto).collect(Collectors.toList());
    }

    public ProductDto getProductById(Long id) {
        return converter.productConvertToDto(findProductById(id));
    }

    public ProductDto updateProduct(Long id, UpdateProductRequest request) {
        Product inDB = findProductById(id);
        inDB.setName(request.getName());
        inDB.setDescription(request.getDescription());
        inDB.setPrice(request.getPrice());
        inDB.setQuantity(request.getQuantity());
        return converter.productConvertToDto(productRepository.save(inDB));
    }

    public void deleteProductById(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }

    protected Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    // if productRepository.findBySeller_Id(id) return null we can use Optional.ofNullable() but this method's returning empty
    public List<ProductDto> getAllProductsBySellerId(Long id) {
        return productRepository.findBySeller_Id(id).stream()
                .map(converter::productConvertToDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Optional::of))
                .filter(p -> !p.isEmpty()).orElseThrow(() -> new NotFoundException("Seller not found"));
    }
}
