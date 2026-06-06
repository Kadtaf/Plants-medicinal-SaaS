package com.plants.api.mappers;



import com.plants.api.dto.responses.UserResponse;
import com.plants.api.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Convertit une entité User en UserResponse
    UserResponse toResponse(User user);
}
