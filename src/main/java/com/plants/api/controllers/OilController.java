package com.plants.api.controllers;


import com.plants.api.dto.request.OilRequest;
import com.plants.api.dto.responses.OilResponse;
import com.plants.api.services.OilService;
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

@RestController
@RequestMapping("/api/oils")
@RequiredArgsConstructor
@Tag(name = "Oils", description = "Endpoints pour la gestion des huiles")
public class OilController {

    private final OilService oilService;

    // Crée une nouvelle huile (Admin only)
    @PostMapping
    @Operation(summary = "Créer une nouvelle huile")
    public ResponseEntity<OilResponse> createOil(@Valid @RequestBody OilRequest oilRequest) {
        OilResponse oilResponse = oilService.createOil(oilRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(oilResponse);
    }

    // Récupère une huile par son ID
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une huile par son ID")
    public ResponseEntity<OilResponse> getOilById(@PathVariable Long id) {
        OilResponse oilResponse = oilService.getOilById(id);
        return ResponseEntity.ok(oilResponse);
    }

    // Récupère toutes les huiles avec pagination
    @GetMapping
    @Operation(summary = "Récupérer toutes les huiles")
    public ResponseEntity<Page<OilResponse>> getAllOils(
            @ParameterObject @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<OilResponse> oilResponses = oilService.getAllOils(pageable);
        return ResponseEntity.ok(oilResponses);
    }

    // Met à jour une huile (Admin only)
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une huile")
    public ResponseEntity<OilResponse> updateOil(
            @PathVariable Long id,
            @Valid @RequestBody OilRequest oilRequest) {
        OilResponse oilResponse = oilService.updateOil(id, oilRequest);
        return ResponseEntity.ok(oilResponse);
    }

    // Supprime une huile (Admin only)
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une huile")
    public ResponseEntity<Void> deleteOil(@PathVariable Long id) {
        oilService.deleteOil(id);
        return ResponseEntity.noContent().build();
    }

    // Récupère les huiles par plante
    @GetMapping("/plant/{plantId}")
    @Operation(summary = "Récupérer les huiles par plante")
    public ResponseEntity<Page<OilResponse>> getOilsByPlantId(
            @PathVariable Long plantId,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<OilResponse> oilResponses = oilService.getOilsByPlantId(plantId, pageable);
        return ResponseEntity.ok(oilResponses);
    }

    // Recherche des huiles par bénéfice
    @GetMapping("/search/benefit")
    @Operation(summary = "Rechercher des huiles par bénéfice")
    public ResponseEntity<Page<OilResponse>> searchOilsByBenefit(
            @RequestParam String benefit,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<OilResponse> oilResponses = oilService.searchOilsByBenefit(benefit, pageable);
        return ResponseEntity.ok(oilResponses);
    }

    // Recherche avancée
    @GetMapping("/search")
    @Operation(summary = "Recherche avancée d'huiles")
    public ResponseEntity<Page<OilResponse>> searchOils(
            @RequestParam String searchTerm,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<OilResponse> oilResponses = oilService.searchOils(searchTerm, pageable);
        return ResponseEntity.ok(oilResponses);
    }
}
