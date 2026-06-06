package com.plants.api.services;

import com.plants.api.dto.request.AffiliateClickRequest;
import com.plants.api.entities.AffiliateClick;
import com.plants.api.entities.User;
import com.plants.api.repositories.AffiliateClickRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AffiliateClickService {

    private final AffiliateClickRepository affiliateClickRepository;

    // Enregistre un clic d'affiliation
    @Transactional
    public void trackAffiliateClick(AffiliateClickRequest affiliateClickRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = null;
        if (authentication != null && authentication.isAuthenticated()) {
            userId = ((User) authentication.getPrincipal()).getId();
        }

        AffiliateClick affiliateClick = AffiliateClick.builder()
                .productId(affiliateClickRequest.getProductId())
                .productName(affiliateClickRequest.getProductName())
                .vendor(affiliateClickRequest.getVendor())
                .userId(userId)
                .page(affiliateClickRequest.getPage())
                .timestamp(LocalDateTime.now())
                .build();

        affiliateClickRepository.save(affiliateClick);
        log.info("Affiliate click tracked: productId={}, vendor={}, userId={}, page={}",
                affiliateClickRequest.getProductId(),
                affiliateClickRequest.getVendor(),
                userId,
                affiliateClickRequest.getPage());
    }

    // Récupère le nombre total de clics
    @Transactional(readOnly = true)
    public Long getTotalClicks() {
        return affiliateClickRepository.count();
    }

    // Récupère le nombre de clics par vendeur
    @Transactional(readOnly = true)
    public java.util.Map<String, Long> getClicksByVendor() {
        return affiliateClickRepository.countClicksByVendor().stream()
                .collect(java.util.stream.Collectors.toMap(
                        arr -> (String) arr[0],
                        arr -> (Long) arr[1]
                ));
    }

    // Récupère le nombre de clics par produit
    @Transactional(readOnly = true)
    public java.util.Map<Long, Long> getClicksByProduct() {
        return affiliateClickRepository.findAll().stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        AffiliateClick::getProductId,
                        java.util.stream.Collectors.counting()
                ));
    }
}