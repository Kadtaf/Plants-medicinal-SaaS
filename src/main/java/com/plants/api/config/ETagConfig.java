package com.plants.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.util.concurrent.TimeUnit;

@Configuration
public class ETagConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebContentInterceptor interceptor = new WebContentInterceptor();

        interceptor.addCacheMapping(
                CacheControl.maxAge(3600, TimeUnit.SECONDS).mustRevalidate(),
                "/api/plants/**"
        );

        interceptor.addCacheMapping(
                CacheControl.maxAge(3600, TimeUnit.SECONDS).mustRevalidate(),
                "/api/oils/**"
        );

        interceptor.addCacheMapping(
                CacheControl.maxAge(1800, TimeUnit.SECONDS).mustRevalidate(),
                "/api/articles/**"
        );

        registry.addInterceptor(interceptor);
    }
}