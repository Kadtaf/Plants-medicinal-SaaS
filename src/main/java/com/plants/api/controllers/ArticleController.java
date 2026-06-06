package com.plants.api.controllers;

import com.plants.api.dto.request.ArticleRequest;
import com.plants.api.dto.responses.ArticleResponse;
import com.plants.api.services.ArticleService;
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
@RequestMapping("/api/articles")
@RequiredArgsConstructor
@Tag(name = "Articles", description = "Endpoints pour la gestion des articles")
public class ArticleController {

    private final ArticleService articleService;

    // Crée un nouvel article (Admin only)
    @PostMapping
    @Operation(summary = "Créer un nouvel article")
    public ResponseEntity<ArticleResponse> createArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        ArticleResponse articleResponse = articleService.createArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(articleResponse);
    }

    // Récupère un article par son ID
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un article par son ID")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable Long id) {
        ArticleResponse articleResponse = articleService.getArticleById(id);
        return ResponseEntity.ok(articleResponse);
    }

    // Récupère un article par son slug
    @GetMapping("/slug/{slug}")
    @Operation(summary = "Récupérer un article par son slug")
    public ResponseEntity<ArticleResponse> getArticleBySlug(@PathVariable String slug) {
        ArticleResponse articleResponse = articleService.getArticleBySlug(slug);
        return ResponseEntity.ok(articleResponse);
    }

    // Récupère tous les articles avec pagination
    @GetMapping
    @Operation(summary = "Récupérer tous les articles")
    public ResponseEntity<Page<ArticleResponse>> getAllArticles(
            @ParameterObject @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        Page<ArticleResponse> articleResponses = articleService.getAllArticles(pageable);
        return ResponseEntity.ok(articleResponses);
    }

    // Met à jour un article (Admin only)
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un article")
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable Long id,
            @Valid @RequestBody ArticleRequest articleRequest) {
        ArticleResponse articleResponse = articleService.updateArticle(id, articleRequest);
        return ResponseEntity.ok(articleResponse);
    }

    // Supprime un article (Admin only)
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un article")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    // Recherche des articles par catégorie
    @GetMapping("/category/{category}")
    @Operation(summary = "Rechercher des articles par catégorie")
    public ResponseEntity<Page<ArticleResponse>> getArticlesByCategory(
            @PathVariable String category,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleResponse> articleResponses = articleService.getArticlesByCategory(category, pageable);
        return ResponseEntity.ok(articleResponses);
    }

    // Recherche avancée
    @GetMapping("/search")
    @Operation(summary = "Recherche avancée d'articles")
    public ResponseEntity<Page<ArticleResponse>> searchArticles(
            @RequestParam String searchTerm,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        Page<ArticleResponse> articleResponses = articleService.searchArticles(searchTerm, pageable);
        return ResponseEntity.ok(articleResponses);
    }
}
