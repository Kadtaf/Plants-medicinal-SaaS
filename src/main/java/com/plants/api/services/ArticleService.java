package com.plants.api.services;


import com.plants.api.dto.request.ArticleRequest;
import com.plants.api.dto.responses.ArticleResponse;
import com.plants.api.entities.Article;
import com.plants.api.exceptions.ResourceNotFoundException;
import com.plants.api.mappers.ArticleMapper;
import com.plants.api.repositories.ArticleRepository;
import com.plants.api.utils.SlugUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final SlugUtils slugUtils;

    // Crée un nouvel article
    @Transactional
    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        Article article = articleMapper.toEntity(articleRequest);
        article.setSlug(slugUtils.generateSlug(articleRequest.getTitle()));
        Article savedArticle = articleRepository.save(article);
        return articleMapper.toResponse(savedArticle);
    }

    // Récupère un article par son ID
    @Cacheable(value = "articles", key = "#id")
    @Transactional(readOnly = true)
    public ArticleResponse getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + id));
        return articleMapper.toResponse(article);
    }

    // Récupère un article par son slug
    @Cacheable(value = "articles", key = "#slug")
    @Transactional(readOnly = true)
    public ArticleResponse getArticleBySlug(String slug) {
        Article article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with slug: " + slug));
        return articleMapper.toResponse(article);
    }

    // Récupère tous les articles avec pagination
    @Cacheable(value = "articles")
    @Transactional(readOnly = true)
    public Page<ArticleResponse> getAllArticles(Pageable pageable) {
        return articleRepository.findAll(pageable)
                .map(articleMapper::toResponse);
    }

    // Met à jour un article
    @CacheEvict(value = "articles", allEntries = true)
    @Transactional
    public ArticleResponse updateArticle(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + id));

        article.setSlug(slugUtils.generateSlug(articleRequest.getTitle()));
        articleMapper.updateEntity(article, articleRequest);
        Article updatedArticle = articleRepository.save(article);
        return articleMapper.toResponse(updatedArticle);
    }

    // Supprime un article
    @CacheEvict(value = "articles", allEntries = true)
    @Transactional
    public void deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Article not found with id: " + id);
        }
        articleRepository.deleteById(id);
    }

    // Recherche des articles par catégorie
    @Transactional(readOnly = true)
    public Page<ArticleResponse> getArticlesByCategory(String category, Pageable pageable) {
        return articleRepository.findByCategory(category, pageable)
                .map(articleMapper::toResponse);
    }

    // Recherche avancée
    @Transactional(readOnly = true)
    public Page<ArticleResponse> searchArticles(String searchTerm, Pageable pageable) {
        return articleRepository.searchArticles(searchTerm, pageable)
                .map(articleMapper::toResponse);
    }
}
