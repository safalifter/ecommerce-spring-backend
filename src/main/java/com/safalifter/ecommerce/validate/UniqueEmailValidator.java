package com.safalifter.ecommerce.validate;

import com.safalifter.ecommerce.model.User;
import com.safalifter.ecommerce.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userRepository;

    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        User user = userRepository.findByEmail(email);
        return user == null;
    }
}