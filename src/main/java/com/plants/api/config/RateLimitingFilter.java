package com.plants.api.config;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import java.util.function.Supplier; // ✅ Ajout de l'import manquant

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
public class RateLimitingFilter implements Filter {

    private final Supplier<Bucket> bucketSupplier;
    private final RateLimitingProperties rateLimitingProperties;
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    // ✅ Liste des chemins à exclure du rat limiting (plus lisible)
    private static final String[] EXCLUDED_PATHS = {
            "/api/auth",
            "/swagger-ui",
            "/v3/api-docs",
            "/swagger-ui.html",
            "/actuator",
            "/webjars",
            "/api-docs",
            "/favicon.ico" // ✅ Ajout pour éviter les erreurs 429 sur la favicon
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Si le rate limiting est désactivé, on passe directement
        if (!rateLimitingProperties.isEnabled()) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();

        // ✅ Vérification plus propre avec une boucle
        for (String excludedPath : EXCLUDED_PATHS) {
            if (path.startsWith(excludedPath)) {
                chain.doFilter(request, response);
                return;
            }
        }

        // ✅ Gestion de l'IP client (plus robuste)
        String clientIp = getClientIp(httpRequest);
        Bucket bucket = buckets.computeIfAbsent(clientIp, k -> bucketSupplier.get());

        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            chain.doFilter(request, response);
        } else {
            // ✅ Réponse plus complète avec le header Retry-After
            httpResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            httpResponse.setHeader("X-Rate-Limit-Retry-After-Seconds",
                    String.valueOf(probe.getNanosToWaitForRefill() / 1_000_000_000));
            httpResponse.setHeader("Retry-After",
                    String.valueOf(probe.getNanosToWaitForRefill() / 1_000_000_000));
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    String.format("{\"error\": \"Too many requests\", \"retryAfter\": %d}",
                            probe.getNanosToWaitForRefill() / 1_000_000_000)
            );
            log.warn("Rate limit exceeded for IP: {} (Path: {})", clientIp, path);
        }
    }

    // ✅ Méthode utilitaire pour obtenir l'IP client (plus robuste)
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // ✅ En cas d'IP multiple (ex: "192.168.1.1, 192.168.1.2"), on prend la première
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip != null ? ip : "anonymous";
    }
}