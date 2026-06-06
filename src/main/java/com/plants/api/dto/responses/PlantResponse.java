package com.plants.api.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlantResponse {

    private Long id;
    private String name;
    private String description;
    private String origin;
    private String seasonFound;
    private List<String> properties;
    private List<String> uses;
    private String imageUrl;
    private String affiliateLink;
}
