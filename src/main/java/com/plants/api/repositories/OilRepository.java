package com.plants.api.repositories;

import com.plants.api.entities.Oil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OilRepository extends JpaRepository<Oil, Long> {

    Page<Oil> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Oil> findByPlantId(Long plantId, Pageable pageable);

    @Query("SELECT o FROM Oil o WHERE :benefit MEMBER OF o.benefits")
    Page<Oil> findByBenefit(@Param("benefit") String benefit, Pageable pageable);

    @Query("""
        SELECT o FROM Oil o
        WHERE LOWER(o.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
           OR LOWER(o.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
           OR :searchTerm MEMBER OF o.benefits
    """)
    Page<Oil> searchOils(@Param("searchTerm") String searchTerm, Pageable pageable);

    Optional<Oil> findByName(String name);
}