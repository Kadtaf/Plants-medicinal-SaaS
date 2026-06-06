package com.plants.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminStatsResponse {

    private Long totalPlants;
    private Long totalOils;
    private Long totalUsers;
    private Long totalFavorites;
    private List<TopItemResponse> topPlants;
    private List<TopItemResponse> topOils;
    private Long totalAffiliateClicks;
    private Double conversionRate;
    private Map<String, Long> clicksByVendor;
    private List<SecurityLogResponse> securityLogs;
}

