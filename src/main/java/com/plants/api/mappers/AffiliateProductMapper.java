package com.plants.api.mappers;



import com.plants.api.dto.request.AffiliateProductRequest;
import com.plants.api.dto.responses.AffiliateProductResponse;
import com.plants.api.entities.AffiliateProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AffiliateProductMapper {

    // Convertit une entité AffiliateProduct en AffiliateProductResponse
    AffiliateProductResponse toResponse(AffiliateProduct affiliateProduct);

    // Convertit une AffiliateProductRequest en entité AffiliateProduct
    AffiliateProduct toEntity(AffiliateProductRequest affiliateProductRequest);

    // Met à jour une entité AffiliateProduct avec les données de AffiliateProductRequest
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget AffiliateProduct affiliateProduct, AffiliateProductRequest affiliateProductRequest);
}
