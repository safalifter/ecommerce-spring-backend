package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.Converter;
import com.safalifter.ecommerce.dto.UserDto;
import com.safalifter.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Converter converter;

    public UserService(UserRepository userRepository, Converter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public UserDto getUserByEmail(String email) {
        return converter.userConvertToDto(userRepository.findByEmail(email));
    }
}