package com.safalifter.ecommerce.repository;

import com.safalifter.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller_Id(Long id);

    @Query(nativeQuery = true,
            value = "SELECT *" +
                    " FROM PRODUCT" +
                    " where LOWER(NAME) like lower(concat(:pName,'%'))")
    List<Product> findProductByNameStartsWithIgnoreCase(String pName);
}

