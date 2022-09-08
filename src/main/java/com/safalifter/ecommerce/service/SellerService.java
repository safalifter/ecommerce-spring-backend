package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.Converter;
import com.safalifter.ecommerce.dto.SellerCreateRequest;
import com.safalifter.ecommerce.dto.SellerDto;
import com.safalifter.ecommerce.dto.UpdateSellerRequest;
import com.safalifter.ecommerce.model.Seller;
import com.safalifter.ecommerce.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final Converter converter;

    public SellerService(SellerRepository sellerRepository, Converter converter) {
        this.sellerRepository = sellerRepository;
        this.converter = converter;
    }

    public SellerDto createSeller(SellerCreateRequest request) {
        Seller seller = Seller.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .companyName(request.getCompanyName())
                .about(request.getAbout()).build();
        return converter.sellerConvertToDto(sellerRepository.save(seller));
    }

    public List<SellerDto> getAllSellers() {
        return sellerRepository.findAll().stream().map(converter::sellerConvertToDto).collect(Collectors.toList());
    }

    public SellerDto getSellerById(Long id) {
        Seller seller = findSellerById(id);
        return converter.sellerConvertToDto(seller);
    }

    public SellerDto updateSeller(Long id, UpdateSellerRequest request) {
        Seller inDB = findSellerById(id);
        inDB.setUsername(request.getUsername());
        inDB.setPassword(request.getPassword());
        inDB.setCompanyName(request.getCompanyName());
        inDB.setAbout(request.getAbout());
        return converter.sellerConvertToDto(sellerRepository.save(inDB));
    }

    public void deleteSeller(Long id) {
        Seller seller = findSellerById(id);
        sellerRepository.delete(seller);
    }

    private Seller findSellerById(Long id) {
        return sellerRepository.findById(id).orElse(null);
    }
}