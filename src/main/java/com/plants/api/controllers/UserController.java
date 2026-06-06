package com.plants.api.controllers;

import com.plants.api.dto.responses.UserResponse;
import com.plants.api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints pour la gestion des utilisateurs (Admin only)")
public class UserController {

    private final UserService userService;

    // Récupère un utilisateur par son ID (Admin only)
    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un utilisateur par son ID")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    // Récupère tous les utilisateurs avec pagination (Admin only)
    @GetMapping
    @Operation(summary = "Récupérer tous les utilisateurs")
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @ParameterObject @PageableDefault(size = 10, sort = "email") Pageable pageable) {
        Page<UserResponse> userResponses = userService.getAllUsers(pageable);
        return ResponseEntity.ok(userResponses);
    }

    // Met à jour un utilisateur (Admin only)
    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un utilisateur")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserResponse userResponse) {
        UserResponse updatedUser = userService.updateUser(id, userResponse);
        return ResponseEntity.ok(updatedUser);
    }

    // Supprime un utilisateur (Admin only)
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
