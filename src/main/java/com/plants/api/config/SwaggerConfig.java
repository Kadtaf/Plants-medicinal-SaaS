package com.plants.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Configuration globale de OpenAPI
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST pour la gestion des plantes, huiles et affiliation")
                        .version("1.0.0")
                        .description("Une API REST complète, sécurisée et monétisable pour gérer les plantes, les huiles essentielles, les articles, et l'affiliation.")
                        .contact(new Contact()
                                .name("Abdelkader TAFTAF")
                                .email("abdelkader.taftaf@example.com")
                        )
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }

    // Groupement des endpoints par module
    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("Authentification")
                .pathsToMatch("/api/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi plantsApi() {
        return GroupedOpenApi.builder()
                .group("Plantes")
                .pathsToMatch("/api/plants/**")
                .build();
    }

    @Bean
    public GroupedOpenApi oilsApi() {
        return GroupedOpenApi.builder()
                .group("Huiles")
                .pathsToMatch("/api/oils/**")
                .build();
    }

    @Bean
    public GroupedOpenApi usersApi() {
        return GroupedOpenApi.builder()
                .group("Utilisateurs")
                .pathsToMatch("/api/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi favoritesApi() {
        return GroupedOpenApi.builder()
                .group("Favoris")
                .pathsToMatch("/api/favorites/**")
                .build();
    }

    @Bean
    public GroupedOpenApi articlesApi() {
        return GroupedOpenApi.builder()
                .group("Articles")
                .pathsToMatch("/api/articles/**")
                .build();
    }

    @Bean
    public GroupedOpenApi affiliateApi() {
        return GroupedOpenApi.builder()
                .group("Affiliation")
                .pathsToMatch("/api/affiliate/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("Admin")
                .pathsToMatch("/api/admin/**")
                .build();
    }
}
