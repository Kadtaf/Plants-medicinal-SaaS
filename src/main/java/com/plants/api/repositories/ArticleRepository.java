package com.plants.api.repositories;

import com.plants.api.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByCategory(String category, Pageable pageable);

    @Query("SELECT a FROM Article a WHERE a.title LIKE %:searchTerm% OR a.content LIKE %:searchTerm%")
    Page<Article> searchArticles(String searchTerm, Pageable pageable);

    Optional<Article> findBySlug(String slug);
}
