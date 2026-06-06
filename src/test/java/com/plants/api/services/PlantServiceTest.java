package com.plants.api.services;

import com.plants.api.dto.request.PlantRequest;
import com.plants.api.dto.responses.PlantResponse;
import com.plants.api.entities.Plant;
import com.plants.api.exceptions.ResourceNotFoundException;
import com.plants.api.mappers.PlantMapper;
import com.plants.api.repositories.PlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlantServiceTest {

    @Mock
    private PlantRepository plantRepository;

    @Mock
    private PlantMapper plantMapper;

    @InjectMocks
    private PlantService plantService;

    private PlantRequest plantRequest;
    private Plant plant;
    private PlantResponse plantResponse;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        plantRequest = PlantRequest.builder()
                .name("Lavande")
                .description("Plante aromatique")
                .origin("France")
                .seasonFound("Été")
                .properties(List.of("Calmante", "Antiseptique"))
                .uses(List.of("Huile essentielle", "Infusion"))
                .imageUrl("https://plants.com/lavande.jpg")
                .affiliateLink("https://plants.com/affiliate/lavande")
                .build();

        plant = Plant.builder()
                .id(1L)
                .name("Lavande")
                .description("Plante aromatique")
                .origin("France")
                .seasonFound("Été")
                .properties(List.of("Calmante", "Antiseptique"))
                .uses(List.of("Huile essentielle", "Infusion"))
                .imageUrl("https://plants.com/lavande.jpg")
                .affiliateLink("https://plants.com/affiliate/lavande")
                .build();

        plantResponse = PlantResponse.builder()
                .id(1L)
                .name("Lavande")
                .description("Plante aromatique")
                .origin("France")
                .seasonFound("Été")
                .properties(List.of("Calmante", "Antiseptique"))
                .uses(List.of("Huile essentielle", "Infusion"))
                .imageUrl("https://plants.com/lavande.jpg")
                .affiliateLink("https://plants.com/affiliate/lavande")
                .build();

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void createPlant_ShouldReturnPlantResponse() {
        when(plantMapper.toEntity(plantRequest)).thenReturn(plant);
        when(plantRepository.save(plant)).thenReturn(plant);
        when(plantMapper.toResponse(plant)).thenReturn(plantResponse);

        PlantResponse response = plantService.createPlant(plantRequest);

        assertNotNull(response);
        assertEquals(plantResponse.getName(), response.getName());
        verify(plantRepository, times(1)).save(plant);
    }

    @Test
    void getPlantById_ShouldReturnPlantResponse_WhenPlantExists() {
        when(plantRepository.findById(1L)).thenReturn(Optional.of(plant));
        when(plantMapper.toResponse(plant)).thenReturn(plantResponse);

        PlantResponse response = plantService.getPlantById(1L);

        assertNotNull(response);
        assertEquals(plantResponse.getName(), response.getName());
    }

    @Test
    void getPlantById_ShouldThrowResourceNotFoundException_WhenPlantDoesNotExist() {
        when(plantRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            plantService.getPlantById(1L);
        });
    }

    @Test
    void getAllPlants_ShouldReturnPageOfPlantResponses() {
        Page<Plant> plantPage = new PageImpl<>(List.of(plant), pageable, 1);
        when(plantRepository.findAll(pageable)).thenReturn(plantPage);
        when(plantMapper.toResponse(plant)).thenReturn(plantResponse);

        Page<PlantResponse> response = plantService.getAllPlants(pageable);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals(plantResponse.getName(), response.getContent().get(0).getName());
    }

    @Test
    void updatePlant_ShouldReturnUpdatedPlantResponse() {
        when(plantRepository.findById(1L)).thenReturn(Optional.of(plant));
        when(plantRepository.save(plant)).thenReturn(plant);
        when(plantMapper.toResponse(plant)).thenReturn(plantResponse);

        PlantResponse response = plantService.updatePlant(1L, plantRequest);

        assertNotNull(response);
        assertEquals(plantResponse.getName(), response.getName());
        verify(plantMapper, times(1)).updateEntity(plant, plantRequest);
    }

    @Test
    void deletePlant_ShouldNotThrowException_WhenPlantExists() {
        when(plantRepository.existsById(1L)).thenReturn(true);
        doNothing().when(plantRepository).deleteById(1L);

        assertDoesNotThrow(() -> {
            plantService.deletePlant(1L);
        });
        verify(plantRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletePlant_ShouldThrowResourceNotFoundException_WhenPlantDoesNotExist() {
        when(plantRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            plantService.deletePlant(1L);
        });
    }

    @Test
    void searchPlantsByName_ShouldReturnPageOfPlantResponses() {
        Page<Plant> plantPage = new PageImpl<>(List.of(plant), pageable, 1);
        when(plantRepository.findByNameContainingIgnoreCase("Lavande", pageable)).thenReturn(plantPage);
        when(plantMapper.toResponse(plant)).thenReturn(plantResponse);

        Page<PlantResponse> response = plantService.searchPlantsByName("Lavande", pageable);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }
}
