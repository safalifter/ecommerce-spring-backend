package com.safalifter.ecommerce.repository;

import com.safalifter.ecommerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
