package com.plants.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotBlank(message = "Origin is required")
    private String origin;

    @NotBlank(message = "Season is required")
    private String seasonFound;

    @NotNull(message = "Properties are required")
    private List<String> properties;

    @NotNull(message = "Uses are required")
    private List<String> uses;

    private String imageUrl;
    private String affiliateLink;
}
