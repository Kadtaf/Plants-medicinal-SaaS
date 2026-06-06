package com.plants.api.repositories;

import com.plants.api.entities.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
    Page<Plant> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Plant> findBySeasonFoundContainingIgnoreCase(String season, Pageable pageable);

    @Query("SELECT p FROM Plant p WHERE :property MEMBER OF p.properties")
    Page<Plant> findByProperty(@Param("property") String property, Pageable pageable);

    @Query("SELECT p FROM Plant p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.origin) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Plant> searchPlants(@Param("searchTerm") String searchTerm, Pageable pageable);

    Optional<Plant> findByName(String name);

}