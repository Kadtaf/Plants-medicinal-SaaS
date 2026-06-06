package com.plants.api.services;

import com.plants.api.dto.request.AffiliateProductRequest;
import com.plants.api.dto.responses.AffiliateProductResponse;
import com.plants.api.entities.AffiliateProduct;
import com.plants.api.exceptions.ResourceNotFoundException;
import com.plants.api.mappers.AffiliateProductMapper;
import com.plants.api.repositories.AffiliateProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AffiliateService {

    private final AffiliateProductRepository affiliateProductRepository;
    private final AffiliateProductMapper affiliateProductMapper;

    // Crée un nouveau produit affilié
    @Transactional
    public AffiliateProductResponse createAffiliateProduct(AffiliateProductRequest affiliateProductRequest) {
        AffiliateProduct affiliateProduct = affiliateProductMapper.toEntity(affiliateProductRequest);
        AffiliateProduct savedAffiliateProduct = affiliateProductRepository.save(affiliateProduct);
        return affiliateProductMapper.toResponse(savedAffiliateProduct);
    }

    // Récupère un produit affilié par son ID
    @Cacheable(value = "affiliateProducts", key = "#id")
    @Transactional(readOnly = true)
    public AffiliateProductResponse getAffiliateProductById(Long id) {
        AffiliateProduct affiliateProduct = affiliateProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Affiliate product not found with id: " + id));
        return affiliateProductMapper.toResponse(affiliateProduct);
    }

    // Récupère tous les produits affiliés avec pagination
    @Cacheable(value = "affiliateProducts")
    @Transactional(readOnly = true)
    public Page<AffiliateProductResponse> getAllAffiliateProducts(Pageable pageable) {
        return affiliateProductRepository.findAll(pageable)
                .map(affiliateProductMapper::toResponse);
    }

    // Met à jour un produit affilié
    @CacheEvict(value = "affiliateProducts", allEntries = true)
    @Transactional
    public AffiliateProductResponse updateAffiliateProduct(Long id, AffiliateProductRequest affiliateProductRequest) {
        AffiliateProduct affiliateProduct = affiliateProductRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Affiliate product not found with id: " + id));

        affiliateProductMapper.updateEntity(affiliateProduct, affiliateProductRequest);
        AffiliateProduct updatedAffiliateProduct = affiliateProductRepository.save(affiliateProduct);
        return affiliateProductMapper.toResponse(updatedAffiliateProduct);
    }

    // Supprime un produit affilié
    @CacheEvict(value = "affiliateProducts", allEntries = true)
    @Transactional
    public void deleteAffiliateProduct(Long id) {
        if (!affiliateProductRepository.existsById(id)) {
            throw new ResourceNotFoundException("Affiliate product not found with id: " + id);
        }
        affiliateProductRepository.deleteById(id);
    }

    // Récupère les produits affiliés par catégorie
    @Transactional(readOnly = true)
    public Page<AffiliateProductResponse> getAffiliateProductsByCategory(String category, Pageable pageable) {
        return affiliateProductRepository.findByCategory(category, pageable)
                .map(affiliateProductMapper::toResponse);
    }

    // Récupère les produits affiliés par vendeur
    @Transactional(readOnly = true)
    public Page<AffiliateProductResponse> getAffiliateProductsByVendor(String vendor, Pageable pageable) {
        return affiliateProductRepository.findByVendor(vendor, pageable)
                .map(affiliateProductMapper::toResponse);
    }

    // Récupère les produits affiliés associés à une plante
    @Transactional(readOnly = true)
    public List<AffiliateProductResponse> getAffiliateProductsByPlantId(Long plantId) {
        return affiliateProductRepository.findByAssociatedPlantId(plantId).stream()
                .map(affiliateProductMapper::toResponse)
                .collect(java.util.stream.Collectors.toList());
    }
}
