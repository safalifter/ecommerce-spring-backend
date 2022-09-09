package com.safalifter.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "creditCards"})
public class Customer extends User {
    private String firstName;
    private String lastName;
    private Gender gender;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CreditCard> creditCards;
}