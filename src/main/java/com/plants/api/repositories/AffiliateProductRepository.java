package com.plants.api.repositories;

import com.plants.api.entities.AffiliateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffiliateProductRepository extends JpaRepository<AffiliateProduct, Long> {
    Page<AffiliateProduct> findByCategory(String category, Pageable pageable);
    Page<AffiliateProduct> findByVendor(String vendor, Pageable pageable);
    List<AffiliateProduct> findByAssociatedPlantId(Long plantId);
}