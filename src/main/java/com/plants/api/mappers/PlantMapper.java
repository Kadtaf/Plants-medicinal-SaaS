package com.plants.api.mappers;

import com.plants.api.dto.request.PlantRequest;
import com.plants.api.dto.responses.PlantResponse;
import com.plants.api.entities.Plant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlantMapper {

    // Convertit une entité Plant en PlantResponse
    PlantResponse toResponse(Plant plant);

    // Convertit une PlantRequest en entité Plant
    Plant toEntity(PlantRequest plantRequest);

    // Met à jour une entité Plant avec les données de PlantRequest
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "oils", ignore = true)
    void updateEntity(@MappingTarget Plant plant, PlantRequest plantRequest);
}
