package com.plants.api.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private Long id;
    private String title;
    private String slug;
    private String content;
    private String category;
    private Integer readTime;
    private String imageUrl;
    private LocalDateTime createdAt;
}
