package com.safalifter.ecommerce.service;

import com.safalifter.ecommerce.dto.Converter;
import com.safalifter.ecommerce.dto.SellerCreateRequest;
import com.safalifter.ecommerce.dto.SellerDto;
import com.safalifter.ecommerce.dto.UpdateSellerRequest;
import com.safalifter.ecommerce.error.NotFoundException;
import com.safalifter.ecommerce.model.Seller;
import com.safalifter.ecommerce.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .password(request.getPassword())
                .email(request.getEmail())
                .companyName(request.getCompanyName())
                .about(request.getAbout())
                .products(List.of()).build();
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
        inDB.setPassword(request.getPassword());
        inDB.setCompanyName(request.getCompanyName());
        inDB.setAbout(request.getAbout());
        return converter.sellerConvertToDto(sellerRepository.save(inDB));
    }

    public void deleteSellerById(Long id) {
        Seller seller = findSellerById(id);
        sellerRepository.delete(seller);
    }

    protected Seller findSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Seller not found"));
    }

    // sellerRepository.findSellerByProducts_Id(id) return null because return type isn't list
    public SellerDto getSellerByProductId(Long id) {
        return converter.sellerConvertToDto(Optional.ofNullable(sellerRepository.findSellerByProducts_Id(id))
                .orElseThrow(() -> new NotFoundException("Product not found")));
    }
}