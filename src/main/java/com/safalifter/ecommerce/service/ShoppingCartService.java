package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.AddProductToShoppingCartRequest;
import com.safalifter.ecommerce.dto.Converter;
import com.safalifter.ecommerce.dto.ShoppingCartDto;
import com.safalifter.ecommerce.error.NotFoundException;
import com.safalifter.ecommerce.model.Customer;
import com.safalifter.ecommerce.model.Product;
import com.safalifter.ecommerce.model.ShoppingCart;
import com.safalifter.ecommerce.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final Converter converter;
    private final ProductService productService;
    private final CustomerService customerService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, Converter converter, ProductService productService, CustomerService customerService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.converter = converter;
        this.productService = productService;
        this.customerService = customerService;
    }

    public List<ShoppingCartDto> getAllShoppingCarts() {
        return shoppingCartRepository.findAll().stream().map(converter::shoppingCartConvertToDto).collect(Collectors.toList());
    }

    public ShoppingCartDto getShoppingCartById(Long id) {
        ShoppingCart shoppingCart = findShoppingCartById(id);
        return converter.shoppingCartConvertToDto(shoppingCart);
    }

    protected ShoppingCart findShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seller not found"));
    }

    public ShoppingCartDto addProductToShoppingCart(AddProductToShoppingCartRequest request) {
        Product product = productService.findProductById(request.getProductId());
        Customer customer = customerService.findCustomerById(request.getCustomerId());
        ShoppingCart shoppingCart = customer.getShoppingCart();
        List<Product> products = shoppingCart.getProducts();
        double totalPrice = shoppingCart.getTotalPrice() + product.getPrice();
        products.add(product);
        shoppingCart.setProducts(products);
        shoppingCart.setTotalPrice(totalPrice);
        return converter.shoppingCartConvertToDto(shoppingCartRepository.save(shoppingCart));
    }
}
