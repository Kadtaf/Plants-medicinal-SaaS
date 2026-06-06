package com.plants.api.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AffiliateProductResponse {

    private Long id;
    private String name;
    private String url;
    private String vendor;
    private String imageUrl;
    private Double price;
    private String category;
    private Long associatedPlantId;
}
