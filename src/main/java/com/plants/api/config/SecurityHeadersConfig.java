package com.plants.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

/**
 * Configuration dédiée aux en-têtes de sécurité.
 *
 * ⚠️ IMPORTANT – Ce fichier NE déclare PAS de SecurityFilterChain.
 * - Ce fichier NE contient PAS @EnableWebSecurity.
 * - Ce fichier fournit simplement un "customizer" réutilisable
 *   dans SecurityConfig pour éviter les conflits.
 */
@Configuration
public class SecurityHeadersConfig {

    /**
     * Fournit un customizer réutilisable dans SecurityConfig.
     * Cela permet d'ajouter proprement les headers de sécurité
     * sans créer une deuxième SecurityFilterChain.
     */
    @Bean
    public SecurityHeadersCustomizer securityHeadersCustomizer() {
        return http -> http
                .headers(headers -> headers

                        // -------------------------------
                        // 🔐 Content Security Policy (CSP)
                        // -------------------------------
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives(
                                        "default-src 'self'; " +
                                                "script-src 'self' 'unsafe-inline' cdn.jsdelivr.net; " +
                                                "style-src 'self' 'unsafe-inline' cdn.jsdelivr.net; " +
                                                "img-src 'self' data:; " +
                                                "font-src 'self'; " +
                                                "connect-src 'self'; " +
                                                "frame-src 'none'; " +
                                                "object-src 'none';"
                                )
                        )

                        // -------------------------------
                        // 🔐 HTTP Strict Transport Security
                        // -------------------------------
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .maxAgeInSeconds(31536000) // 1 an
                        )

                        // -------------------------------
                        // 🔐 X-Content-Type-Options
                        // -------------------------------
                        .contentTypeOptions(c -> {})

                        // -------------------------------
                        // 🔐 X-Frame-Options
                        // -------------------------------
                        .frameOptions(frame -> frame.deny())

                        // -------------------------------
                        // 🔐 X-XSS-Protection
                        // -------------------------------
                        .addHeaderWriter(new XXssProtectionHeaderWriter())

                        // -------------------------------
                        // 🔐 Referrer-Policy
                        // -------------------------------
                        .referrerPolicy(referrer -> referrer
                                .policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                        )

                        // -------------------------------
                        // 🔐 Permissions-Policy
                        // -------------------------------
                        .permissionsPolicy(policy -> policy
                                .policy("geolocation=(), microphone=(), camera=(), payment=()")
                        )
                );
    }

    /**
     * Interface fonctionnelle utilisée pour injecter proprement
     * les headers dans SecurityConfig.
     */
    @FunctionalInterface
    public interface SecurityHeadersCustomizer {
        void customize(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception;
    }
}
