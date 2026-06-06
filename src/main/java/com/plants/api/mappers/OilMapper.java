package com.plants.api.mappers;


import com.plants.api.dto.request.OilRequest;
import com.plants.api.dto.responses.OilResponse;
import com.plants.api.entities.Oil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface OilMapper {

    // Convertit une entité Oil en OilResponse
    @Mapping(source = "plant.id", target = "plantId")
    @Mapping(source = "plant.name", target = "plantName")
    OilResponse toResponse(Oil oil);

    // Convertit une OilRequest en entité Oil
    @Mapping(target = "plant", ignore = true)
    Oil toEntity(OilRequest oilRequest);

    // Met à jour une entité Oil avec les données de OilRequest
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plant", ignore = true)
    void updateEntity(@MappingTarget Oil oil, OilRequest oilRequest);
}
