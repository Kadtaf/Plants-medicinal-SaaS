package com.plants.api.controllers;

import com.plants.api.dto.request.AffiliateClickRequest;
import com.plants.api.dto.request.AffiliateProductRequest;
import com.plants.api.dto.responses.AffiliateProductResponse;
import com.plants.api.services.AffiliateClickService;
import com.plants.api.services.AffiliateService;
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
@RequestMapping("/api/affiliate")
@RequiredArgsConstructor
@Tag(name = "Affiliate", description = "Endpoints pour la gestion de l'affiliation")
public class AffiliateController {

    private final AffiliateService affiliateService;
    private final AffiliateClickService affiliateClickService;

    // Crée un nouveau produit affilié (Admin only)
    @PostMapping("/products")
    @Operation(summary = "Créer un nouveau produit affilié")
    public ResponseEntity<AffiliateProductResponse> createAffiliateProduct(
            @Valid @RequestBody AffiliateProductRequest affiliateProductRequest) {
        AffiliateProductResponse response = affiliateService.createAffiliateProduct(affiliateProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Récupère un produit affilié par son ID
    @GetMapping("/products/{id}")
    @Operation(summary = "Récupérer un produit affilié par son ID")
    public ResponseEntity<AffiliateProductResponse> getAffiliateProductById(@PathVariable Long id) {
        AffiliateProductResponse response = affiliateService.getAffiliateProductById(id);
        return ResponseEntity.ok(response);
    }

    // Récupère tous les produits affiliés avec pagination
    @GetMapping("/products")
    @Operation(summary = "Récupérer tous les produits affiliés")
    public ResponseEntity<Page<AffiliateProductResponse>> getAllAffiliateProducts(
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<AffiliateProductResponse> responses = affiliateService.getAllAffiliateProducts(pageable);
        return ResponseEntity.ok(responses);
    }

    // Met à jour un produit affilié (Admin only)
    @PutMapping("/products/{id}")
    @Operation(summary = "Mettre à jour un produit affilié")
    public ResponseEntity<AffiliateProductResponse> updateAffiliateProduct(
            @PathVariable Long id,
            @Valid @RequestBody AffiliateProductRequest affiliateProductRequest) {
        AffiliateProductResponse response = affiliateService.updateAffiliateProduct(id, affiliateProductRequest);
        return ResponseEntity.ok(response);
    }

    // Supprime un produit affilié (Admin only)
    @DeleteMapping("/products/{id}")
    @Operation(summary = "Supprimer un produit affilié")
    public ResponseEntity<Void> deleteAffiliateProduct(@PathVariable Long id) {
        affiliateService.deleteAffiliateProduct(id);
        return ResponseEntity.noContent().build();
    }

    // Récupère les produits affiliés par catégorie
    @GetMapping("/products/category/{category}")
    @Operation(summary = "Récupérer les produits affiliés par catégorie")
    public ResponseEntity<Page<AffiliateProductResponse>> getAffiliateProductsByCategory(
            @PathVariable String category,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<AffiliateProductResponse> responses = affiliateService.getAffiliateProductsByCategory(category, pageable);
        return ResponseEntity.ok(responses);
    }

    // Récupère les produits affiliés par vendeur
    @GetMapping("/products/vendor/{vendor}")
    @Operation(summary = "Récupérer les produits affiliés par vendeur")
    public ResponseEntity<Page<AffiliateProductResponse>> getAffiliateProductsByVendor(
            @PathVariable String vendor,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<AffiliateProductResponse> responses = affiliateService.getAffiliateProductsByVendor(vendor, pageable);
        return ResponseEntity.ok(responses);
    }

    // Récupère les produits affiliés associés à une plante
    @GetMapping("/products/plant/{plantId}")
    @Operation(summary = "Récupérer les produits affiliés associés à une plante")
    public ResponseEntity<List<AffiliateProductResponse>> getAffiliateProductsByPlantId(@PathVariable Long plantId) {
        List<AffiliateProductResponse> responses = affiliateService.getAffiliateProductsByPlantId(plantId);
        return ResponseEntity.ok(responses);
    }

    // Enregistre un clic d'affiliation
    @PostMapping("/click")
    @Operation(summary = "Enregistrer un clic d'affiliation")
    public ResponseEntity<Void> trackAffiliateClick(@Valid @RequestBody AffiliateClickRequest affiliateClickRequest) {
        affiliateClickService.trackAffiliateClick(affiliateClickRequest);
        return ResponseEntity.ok().build();
    }
}