package com.plants.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {

    @NotNull(message = "Target ID is required")
    private Long targetId;

    @NotBlank(message = "Type is required")
    private String type; // PLANT, OIL, ARTICLE

    @NotBlank(message = "Content is required")
    private String content;
}
