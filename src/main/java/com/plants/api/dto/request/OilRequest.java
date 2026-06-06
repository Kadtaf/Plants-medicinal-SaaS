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
public class OilRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Benefits are required")
    private List<String> benefits;

    @NotNull(message = "Precautions are required")
    private List<String> precautions;

    private String imageUrl;
    private String affiliateLink;

    @NotNull(message = "Plant ID is required")
    private Long plantId;
}
