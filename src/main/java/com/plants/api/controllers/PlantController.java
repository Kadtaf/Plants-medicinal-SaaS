package com.plants.api.controllers;


import com.plants.api.dto.request.PlantRequest;
import com.plants.api.dto.responses.PlantResponse;
import com.plants.api.services.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
@Tag(name = "Plants", description = "Endpoints pour la gestion des plantes")
public class PlantController {

    private final PlantService plantService;

    // Crée une nouvelle plante (Admin only)
    @PostMapping
    @Operation(summary = "Créer une nouvelle plante")
    public ResponseEntity<PlantResponse> createPlant(@Valid @RequestBody PlantRequest plantRequest) {
        PlantResponse plantResponse = plantService.createPlant(plantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(plantResponse);
    }

    // Récupère une plante par son ID
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une plante par son ID")
    public ResponseEntity<PlantResponse> getPlantById(@PathVariable Long id) {
        PlantResponse plantResponse = plantService.getPlantById(id);
        return ResponseEntity.ok(plantResponse);
    }

    // Récupère toutes les plantes avec pagination
    @GetMapping
    @Operation(summary = "Récupérer toutes les plantes")
    public ResponseEntity<Page<PlantResponse>> getAllPlants(
            @ParameterObject @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<PlantResponse> plantResponses = plantService.getAllPlants(pageable);
        return ResponseEntity.ok(plantResponses);
    }

    // Met à jour une plante (Admin only)
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une plante")
    public ResponseEntity<PlantResponse> updatePlant(
            @PathVariable Long id,
            @Valid @RequestBody PlantRequest plantRequest) {
        PlantResponse plantResponse = plantService.updatePlant(id, plantRequest);
        return ResponseEntity.ok(plantResponse);
    }

    // Supprime une plante (Admin only)
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une plante")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.deletePlant(id);
        return ResponseEntity.noContent().build();
    }

    // Recherche des plantes par nom
    @GetMapping("/search/name")
    @Operation(summary = "Rechercher des plantes par nom")
    public ResponseEntity<Page<PlantResponse>> searchPlantsByName(
            @RequestParam String name,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<PlantResponse> plantResponses = plantService.searchPlantsByName(name, pageable);
        return ResponseEntity.ok(plantResponses);
    }

    // Recherche des plantes par saison
    @GetMapping("/search/season")
    @Operation(summary = "Rechercher des plantes par saison")
    public ResponseEntity<Page<PlantResponse>> searchPlantsBySeason(
            @RequestParam String season,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<PlantResponse> plantResponses = plantService.searchPlantsBySeason(season, pageable);
        return ResponseEntity.ok(plantResponses);
    }

    // Recherche des plantes par propriété
    @GetMapping("/search/property")
    @Operation(summary = "Rechercher des plantes par propriété")
    public ResponseEntity<Page<PlantResponse>> searchPlantsByProperty(
            @RequestParam String property,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<PlantResponse> plantResponses = plantService.searchPlantsByProperty(property, pageable);
        return ResponseEntity.ok(plantResponses);
    }

    // Recherche avancée
    @GetMapping("/search")
    @Operation(summary = "Recherche avancée de plantes")
    public ResponseEntity<Page<PlantResponse>> searchPlants(
            @RequestParam String searchTerm,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<PlantResponse> plantResponses = plantService.searchPlants(searchTerm, pageable);
        return ResponseEntity.ok(plantResponses);
    }

}
