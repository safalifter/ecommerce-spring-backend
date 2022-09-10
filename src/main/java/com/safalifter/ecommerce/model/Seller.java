package com.safalifter.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
public class Seller extends User {
    @Column(nullable = false)
    private String companyName;

    private String about;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;
}