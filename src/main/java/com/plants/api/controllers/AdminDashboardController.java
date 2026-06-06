package com.plants.api.controllers;

import com.plants.api.dto.responses.AdminStatsResponse;
import com.plants.api.services.AdminDashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Dashboard", description = "Endpoints pour le dashboard admin")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    // Récupère les statistiques globales du dashboard
    @GetMapping("/stats")
    @Operation(summary = "Récupérer les statistiques globales du dashboard")
    public ResponseEntity<AdminStatsResponse> getDashboardStats() {
        AdminStatsResponse stats = adminDashboardService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }

    // Récupère le nombre de clics par vendeur
    @GetMapping("/stats/clicks-by-vendor")
    @Operation(summary = "Récupérer le nombre de clics par vendeur")
    public ResponseEntity<Map<String, Long>> getClicksByVendor() {
        Map<String, Long> clicksByVendor = adminDashboardService.getClicksByVendor();
        return ResponseEntity.ok(clicksByVendor);
    }

    // Récupère le nombre de clics par jour
    @GetMapping("/stats/clicks-by-day")
    @Operation(summary = "Récupérer le nombre de clics par jour")
    public ResponseEntity<Map<String, Long>> getClicksByDay() {
        Map<String, Long> clicksByDay = adminDashboardService.getClicksByDay();
        return ResponseEntity.ok(clicksByDay);
    }
}
