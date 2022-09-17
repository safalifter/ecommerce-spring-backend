package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.AuthRequest;
import com.safalifter.ecommerce.dto.Converter;
import com.safalifter.ecommerce.dto.UserDto;
import com.safalifter.ecommerce.error.AuthException;
import com.safalifter.ecommerce.model.User;
import com.safalifter.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final Converter converter;

    public AuthService(UserRepository userRepository, Converter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public UserDto authenticate(AuthRequest authRequest) {
        User inDB = userRepository.findByEmail(authRequest.getEmail());
        if (Optional.ofNullable(inDB).isPresent() && inDB.getPassword().matches(authRequest.getPassword())) {
            return converter.userConvertToDto(inDB);
        }
        throw new AuthException();
    }
}