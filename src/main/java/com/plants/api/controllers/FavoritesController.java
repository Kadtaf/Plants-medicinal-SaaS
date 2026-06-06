package com.plants.api.controllers;

import com.plants.api.dto.request.FavoriteRequest;
import com.plants.api.dto.responses.FavoriteResponse;
import com.plants.api.entities.User;
import com.plants.api.services.FavoritesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;

    @PostMapping("/me")
    public ResponseEntity<FavoriteResponse> addFavorite(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody FavoriteRequest request
    ) {
        FavoriteResponse favorite = favoritesService.addFavorite(user, request);
        return ResponseEntity.ok(favorite);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> removeFavorite(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody FavoriteRequest request
    ) {
        favoritesService.removeFavorite(user, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<FavoriteResponse>> getFavorites(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(favoritesService.getFavorites(user));
    }

    @GetMapping("/me/type/{type}")
    public ResponseEntity<List<FavoriteResponse>> getFavoritesByType(
            @AuthenticationPrincipal User user,
            @PathVariable String type
    ) {
        return ResponseEntity.ok(favoritesService.getFavoritesByType(user, type));
    }

    @GetMapping("/me/check")
    public ResponseEntity<Boolean> isFavorite(
            @AuthenticationPrincipal User user,
            @RequestParam Long targetId,
            @RequestParam String type
    ) {
        return ResponseEntity.ok(favoritesService.isFavorite(user, targetId, type));
    }
}