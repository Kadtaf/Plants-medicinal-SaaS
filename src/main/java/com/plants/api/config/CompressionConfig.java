package com.plants.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CompressionConfig implements WebMvcConfigurer {
    // La compression GZIP est déjà configurée dans l'application.yml
    // Cette classe est ici pour d'éventuelles personnalisations supplémentaires
}
