package com.plants.api.services;

import com.plants.api.dto.responses.AdminStatsResponse;
import com.plants.api.dto.responses.SecurityLogResponse;
import com.plants.api.dto.responses.TopItemResponse;
import com.plants.api.entities.Favorite;
import com.plants.api.entities.enums.FavoriteType;
import com.plants.api.repositories.AffiliateClickRepository;
import com.plants.api.repositories.AffiliateProductRepository;
import com.plants.api.repositories.FavoriteRepository;
import com.plants.api.repositories.OilRepository;
import com.plants.api.repositories.PlantRepository;
import com.plants.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminDashboardService {

    private final PlantRepository plantRepository;
    private final OilRepository oilRepository;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final AffiliateClickRepository affiliateClickRepository;
    private final AffiliateProductRepository affiliateProductRepository;

    @Transactional(readOnly = true)
    public AdminStatsResponse getDashboardStats() {
        Long totalPlants = plantRepository.count();
        Long totalOils = oilRepository.count();
        Long totalUsers = userRepository.count();
        Long totalFavorites = favoriteRepository.count();

        List<TopItemResponse> topPlants = getTopPlants();
        List<TopItemResponse> topOils = getTopOils();

        Long totalAffiliateClicks = affiliateClickRepository.count();
        Map<String, Long> clicksByVendor = getClicksByVendor();

        long totalAffiliateProducts = affiliateProductRepository.count();
        Double conversionRate = totalAffiliateProducts == 0
                ? 0.0
                : totalAffiliateClicks.doubleValue() / totalAffiliateProducts;

        List<SecurityLogResponse> securityLogs = List.of(
                SecurityLogResponse.builder()
                        .timestamp(LocalDateTime.now().toString())
                        .event("Failed login attempt")
                        .details("User: test@plants.com, IP: 192.168.1.1")
                        .build()
        );

        return AdminStatsResponse.builder()
                .totalPlants(totalPlants)
                .totalOils(totalOils)
                .totalUsers(totalUsers)
                .totalFavorites(totalFavorites)
                .topPlants(topPlants)
                .topOils(topOils)
                .totalAffiliateClicks(totalAffiliateClicks)
                .conversionRate(conversionRate)
                .clicksByVendor(clicksByVendor)
                .securityLogs(securityLogs)
                .build();
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getClicksByVendor() {
        return affiliateClickRepository.countClicksByVendor().stream()
                .collect(Collectors.toMap(
                        arr -> (String) arr[0],
                        arr -> (Long) arr[1]
                ));
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getClicksByDay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sevenDaysAgo = now.minus(7, ChronoUnit.DAYS);

        return affiliateClickRepository.findByTimestampBetween(sevenDaysAgo, now).stream()
                .collect(Collectors.groupingBy(
                        click -> click.getTimestamp().toLocalDate().toString(),
                        Collectors.counting()
                ));
    }

    @Transactional(readOnly = true)
    public List<TopItemResponse> getTopPlants() {
        List<Favorite> plantFavorites = favoriteRepository.findByType(FavoriteType.PLANT);

        Map<Long, Long> countsByPlantId = plantFavorites.stream()
                .collect(Collectors.groupingBy(Favorite::getTargetId, Collectors.counting()));

        return plantRepository.findAllById(countsByPlantId.keySet()).stream()
                .map(plant -> TopItemResponse.builder()
                        .id(plant.getId())
                        .name(plant.getName())
                        .count(countsByPlantId.getOrDefault(plant.getId(), 0L))
                        .build())
                .sorted(Comparator.comparing(TopItemResponse::getCount).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TopItemResponse> getTopOils() {
        List<Favorite> oilFavorites = favoriteRepository.findByType(FavoriteType.OIL);

        Map<Long, Long> countsByOilId = oilFavorites.stream()
                .collect(Collectors.groupingBy(Favorite::getTargetId, Collectors.counting()));

        return oilRepository.findAllById(countsByOilId.keySet()).stream()
                .map(oil -> TopItemResponse.builder()
                        .id(oil.getId())
                        .name(oil.getName())
                        .count(countsByOilId.getOrDefault(oil.getId(), 0L))
                        .build())
                .sorted(Comparator.comparing(TopItemResponse::getCount).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }
}