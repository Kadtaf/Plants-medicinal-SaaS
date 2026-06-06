package com.plants.api.services;

import com.plants.api.dto.request.FavoriteRequest;
import com.plants.api.dto.responses.FavoriteResponse;
import com.plants.api.entities.Favorite;
import com.plants.api.entities.User;
import com.plants.api.entities.enums.FavoriteType;
import com.plants.api.exceptions.ResourceNotFoundException;
import com.plants.api.repositories.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoritesService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteResponse addFavorite(User user, FavoriteRequest request) {
        FavoriteType type = parseFavoriteType(request.getType());

        boolean alreadyExists = favoriteRepository.existsByUserIdAndTargetIdAndType(
                user.getId(),
                request.getTargetId(),
                type
        );

        if (alreadyExists) {
            throw new IllegalStateException("This item is already in favorites");
        }

        Favorite favorite = Favorite.builder()
                .user(user)
                .targetId(request.getTargetId())
                .type(type)
                .pinned(false)
                .build();

        Favorite savedFavorite = favoriteRepository.save(favorite);
        return toResponse(savedFavorite);
    }

    public void removeFavorite(User user, FavoriteRequest request) {
        FavoriteType type = parseFavoriteType(request.getType());

        boolean exists = favoriteRepository.existsByUserIdAndTargetIdAndType(
                user.getId(),
                request.getTargetId(),
                type
        );

        if (!exists) {
            throw new ResourceNotFoundException("Favorite not found");
        }

        favoriteRepository.deleteByUserIdAndTargetIdAndType(
                user.getId(),
                request.getTargetId(),
                type
        );
    }

    @Transactional(readOnly = true)
    public List<FavoriteResponse> getFavorites(User user) {
        return favoriteRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FavoriteResponse> getFavoritesByType(User user, String type) {
        FavoriteType favoriteType = parseFavoriteType(type);

        return favoriteRepository.findByUserIdAndType(user.getId(), favoriteType)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public boolean isFavorite(User user, Long targetId, String type) {
        FavoriteType favoriteType = parseFavoriteType(type);

        return favoriteRepository.existsByUserIdAndTargetIdAndType(
                user.getId(),
                targetId,
                favoriteType
        );
    }

    private FavoriteResponse toResponse(Favorite favorite) {
        return FavoriteResponse.builder()
                .id(favorite.getId())
                .targetId(favorite.getTargetId())
                .type(favorite.getType().name())
                .pinned(favorite.isPinned())
                .createdAt(favorite.getCreatedAt())
                .build();
    }

    private FavoriteType parseFavoriteType(String type) {
        try {
            return FavoriteType.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new IllegalArgumentException("Invalid favorite type. Allowed values: PLANT, OIL");
        }
    }
}