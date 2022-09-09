package com.safalifter.ecommerce.repository;

import com.safalifter.ecommerce.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findByCustomer_Id(Long id);
}