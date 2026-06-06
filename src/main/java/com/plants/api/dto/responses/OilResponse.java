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
public class OilResponse {

    private Long id;
    private String name;
    private String description;
    private List<String> benefits;
    private List<String> precautions;
    private String imageUrl;
    private String affiliateLink;
    private Long plantId;
    private String plantName;
}
