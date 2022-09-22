package com.safalifter.ecommerce.controller;

import com.safalifter.ecommerce.dto.AuthRequest;
import com.safalifter.ecommerce.dto.AuthResponse;
import com.safalifter.ecommerce.dto.RegisterRequest;
import com.safalifter.ecommerce.dto.RegisterResponse;
import com.safalifter.ecommerce.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    ResponseEntity<AuthResponse> handleLogin(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register")
    ResponseEntity<RegisterResponse> handleRegister(@Valid @RequestBody RegisterRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }
}