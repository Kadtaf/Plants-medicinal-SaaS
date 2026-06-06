package com.plants.api.repositories;

import com.plants.api.entities.Favorite;
import com.plants.api.entities.enums.FavoriteType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUserId(Long userId);

    List<Favorite> findByUserIdAndType(Long userId, FavoriteType type);

    List<Favorite> findByType(FavoriteType type);

    boolean existsByUserIdAndTargetIdAndType(Long userId, Long targetId, FavoriteType type);

    void deleteByUserIdAndTargetIdAndType(Long userId, Long targetId, FavoriteType type);
}