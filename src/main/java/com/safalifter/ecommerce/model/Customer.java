package com.safalifter.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "shoppingCart"})
public class Customer extends User {
    private String firstName;
    private String lastName;
    private Gender gender;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<CreditCard> creditCards;

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;
}