package com.plants.api.repositories;

import com.plants.api.entities.AffiliateClick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AffiliateClickRepository extends JpaRepository<AffiliateClick, Long> {
    List<AffiliateClick> findByProductId(Long productId);
    List<AffiliateClick> findByVendor(String vendor);
    List<AffiliateClick> findByUserId(Long userId);
    List<AffiliateClick> findByPage(String page);

    @Query("SELECT COUNT(a) FROM AffiliateClick a WHERE a.timestamp >= :startDate AND a.timestamp <= :endDate")
    Long countClicksByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT a.vendor, COUNT(a) FROM AffiliateClick a GROUP BY a.vendor")
    List<Object[]> countClicksByVendor();

    List<AffiliateClick> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}