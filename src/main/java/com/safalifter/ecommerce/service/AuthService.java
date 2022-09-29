package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.AuthRequest;
import com.safalifter.ecommerce.dto.AuthResponse;
import com.safalifter.ecommerce.dto.RegisterRequest;
import com.safalifter.ecommerce.dto.RegisterResponse;
import com.safalifter.ecommerce.error.AuthException;
import com.safalifter.ecommerce.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CustomerService customerService;
    private final SellerService sellerService;
    private final UserDetailsService userDetailsService;

    public AuthService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil, CustomerService customerService, SellerService sellerService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.customerService = customerService;
        this.sellerService = sellerService;
        this.userDetailsService = userDetailsService;
    }

    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new AuthException("Login failed.");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthResponse(userDetails.getUsername(), userDetails.getAuthorities().toString(), jwt);
    }

    public RegisterResponse register(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        if (request.getRole().toString().equals("CUSTOMER")) {
            customerService.createCustomer(request);
            return new RegisterResponse(request.getEmail(), request.getRole());
        } else if (request.getRole().toString().equals("SELLER")) {
            sellerService.createSeller(request);
            return new RegisterResponse(request.getEmail(), request.getRole());
        }
        return null;
    }
}