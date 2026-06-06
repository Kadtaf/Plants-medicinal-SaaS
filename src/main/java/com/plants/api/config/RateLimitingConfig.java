package com.plants.api.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Supplier;

@Configuration
public class RateLimitingConfig {

    @Value("${rate-limit.enabled:true}")
    private boolean rateLimitEnabled;

    @Value("${rate-limit.requests-per-minute:100}")
    private int requestsPerMinute;

    @Bean
    public Supplier<Bucket> bucketSupplier() {

        if (!rateLimitEnabled) {
            // Bucket "illimité" mais réaliste : 1 million req/min
            int unlimited = 1_000_000;

            Refill refill = Refill.intervally(unlimited, Duration.ofMinutes(1));
            Bandwidth limit = Bandwidth.classic(unlimited, refill);

            return () -> Bucket4j.builder()
                    .addLimit(limit)
                    .build();
        }

        // Bucket limité
        Refill refill = Refill.intervally(requestsPerMinute, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(requestsPerMinute, refill);

        return () -> Bucket4j.builder()
                .addLimit(limit)
                .build();
    }
}
