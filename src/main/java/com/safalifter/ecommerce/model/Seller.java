package com.safalifter.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Seller extends User {
    private String companyName;
    private String about;
}
