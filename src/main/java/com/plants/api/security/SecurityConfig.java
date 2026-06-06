package com.plants.api.security;

import com.plants.api.config.SecurityHeadersConfig.SecurityHeadersCustomizer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration principale de Spring Security.
 * <p>
 * ⚠️ IMPORTANT – Une seule SecurityFilterChain (pas de conflit)
 * CORS géré ici (CorsConfig supprimé)
 * Headers de sécurité appliquée via SecurityHeadersCustomizer
 * - RateLimitingFilter retiré
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    // Customizer pour les headers de sécurité (injecté depuis SecurityHeadersConfig)
    private final SecurityHeadersCustomizer securityHeadersCustomizer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // -----------------------------
        // 🔐 Désactivation CSRF (API REST)
        // -----------------------------
        http.csrf(AbstractHttpConfigurer::disable);

        // -----------------------------
        // 🌍 CORS global (Swagger + Front)
        // -----------------------------
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        // -----------------------------
        // 🔓 Règles d'accès
        // -----------------------------
        http.authorizeHttpRequests(auth -> auth

                // Endpoints publics
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/plants/**").permitAll()
                .requestMatchers("/api/oils/**").permitAll()
                .requestMatchers("/api/articles/**").permitAll()
                .requestMatchers("/api/comments/**").permitAll()

                // Swagger
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/webjars/**").permitAll()

                // Actuator
                .requestMatchers("/actuator/**").permitAll()

                // Favicon
                .requestMatchers("/favicon.ico").permitAll()

                // Endpoints nécessitant un rôle USER
                .requestMatchers("/api/favorites/**").hasRole("USER")

                // Endpoints nécessitant un rôle ADMIN
                .requestMatchers("/api/admin/**", "/api/users/**").hasRole("ADMIN")

                // Toute autre requête nécessite une authentification
                .anyRequest().authenticated()
        );

        // -----------------------------
        // 🔐 Session stateless (JWT)
        // -----------------------------
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // -----------------------------
        // 🔐 Provider d'authentification
        // -----------------------------
        http.authenticationProvider(authenticationProvider);

        // -----------------------------
        // 🔐 Filtre JWT avant UsernamePasswordAuthenticationFilter
        // -----------------------------
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // -----------------------------
        // 🔐 Application des headers de sécurité
        // -----------------------------
        securityHeadersCustomizer.customize(http);

        return http.build();
    }

    /**
     * 🌍 Configuration CORS propre et compatible
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // ⚠️ IMPORTANT : pas de "*" avec credentials = true
        configuration.setAllowedOrigins(List.of(
                "http://localhost:5173"   // front local React/Vite

        ));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /**
     * 🔐 Fournit l'AuthenticationManager utilisé par AuthenticationService
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
