package com.safalifter.ecommerce.repository;

import com.safalifter.ecommerce.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
