package com.plants.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rate-limit") // ✅ Préfixe pour application.yml
public class RateLimitingProperties {
    private boolean enabled = true; // ✅ Activé par défaut
    private int requestsPerMinute = 100; // ✅ 100 requêtes par minute par défaut
}