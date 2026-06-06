package com.plants.api.mappers;


import com.plants.api.dto.request.ArticleRequest;
import com.plants.api.dto.responses.ArticleResponse;
import com.plants.api.entities.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    // Convertit une entité Article en ArticleResponse
    ArticleResponse toResponse(Article article);

    // Convertit une ArticleRequest en entité Article
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Article toEntity(ArticleRequest articleRequest);

    // Met à jour une entité Article avec les données de ArticleRequest
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(@MappingTarget Article article, ArticleRequest articleRequest);
}
