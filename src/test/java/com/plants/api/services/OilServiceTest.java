package com.plants.api.services;

import com.plants.api.dto.request.OilRequest;
import com.plants.api.dto.responses.OilResponse;
import com.plants.api.entities.Oil;
import com.plants.api.entities.Plant;
import com.plants.api.exceptions.ResourceNotFoundException;
import com.plants.api.mappers.OilMapper;
import com.plants.api.repositories.OilRepository;
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
class OilServiceTest {

    @Mock
    private OilRepository oilRepository;

    @Mock
    private PlantRepository plantRepository;

    @Mock
    private OilMapper oilMapper;

    @InjectMocks
    private OilService oilService;

    private OilRequest oilRequest;
    private Oil oil;
    private OilResponse oilResponse;
    private Plant plant;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        oilRequest = OilRequest.builder()
                .name("Huile de Lavande")
                .description("Huile essentielle de lavande")
                .benefits(List.of("Calmante", "Antiseptique"))
                .precautions(List.of("Ne pas appliquer pure sur la peau"))
                .imageUrl("https://plants.com/huile-lavande.jpg")
                .affiliateLink("https://plants.com/affiliate/huile-lavande")
                .plantId(1L)
                .build();

        plant = Plant.builder()
                .id(1L)
                .name("Lavande")
                .build();

        oil = Oil.builder()
                .id(1L)
                .name("Huile de Lavande")
                .description("Huile essentielle de lavande")
                .benefits(List.of("Calmante", "Antiseptique"))
                .precautions(List.of("Ne pas appliquer pure sur la peau"))
                .imageUrl("https://plants.com/huile-lavande.jpg")
                .affiliateLink("https://plants.com/affiliate/huile-lavande")
                .plant(plant)
                .build();

        oilResponse = OilResponse.builder()
                .id(1L)
                .name("Huile de Lavande")
                .description("Huile essentielle de lavande")
                .benefits(List.of("Calmante", "Antiseptique"))
                .precautions(List.of("Ne pas appliquer pure sur la peau"))
                .imageUrl("https://plants.com/huile-lavande.jpg")
                .affiliateLink("https://plants.com/affiliate/huile-lavande")
                .plantId(1L)
                .plantName("Lavande")
                .build();

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void createOil_ShouldReturnOilResponse() {
        when(plantRepository.findById(1L)).thenReturn(Optional.of(plant));
        when(oilMapper.toEntity(oilRequest)).thenReturn(oil);
        when(oilRepository.save(oil)).thenReturn(oil);
        when(oilMapper.toResponse(oil)).thenReturn(oilResponse);

        OilResponse response = oilService.createOil(oilRequest);

        assertNotNull(response);
        assertEquals(oilResponse.getName(), response.getName());
        verify(oilRepository, times(1)).save(oil);
    }

    @Test
    void createOil_ShouldThrowResourceNotFoundException_WhenPlantDoesNotExist() {
        when(plantRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            oilService.createOil(oilRequest);
        });
    }

    @Test
    void getOilById_ShouldReturnOilResponse_WhenOilExists() {
        when(oilRepository.findById(1L)).thenReturn(Optional.of(oil));
        when(oilMapper.toResponse(oil)).thenReturn(oilResponse);

        OilResponse response = oilService.getOilById(1L);

        assertNotNull(response);
        assertEquals(oilResponse.getName(), response.getName());
    }

    @Test
    void getOilById_ShouldThrowResourceNotFoundException_WhenOilDoesNotExist() {
        when(oilRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            oilService.getOilById(1L);
        });
    }

    @Test
    void getAllOils_ShouldReturnPageOfOilResponses() {
        Page<Oil> oilPage = new PageImpl<>(List.of(oil), pageable, 1);
        when(oilRepository.findAll(pageable)).thenReturn(oilPage);
        when(oilMapper.toResponse(oil)).thenReturn(oilResponse);

        Page<OilResponse> response = oilService.getAllOils(pageable);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }

    @Test
    void updateOil_ShouldReturnUpdatedOilResponse() {
        when(oilRepository.findById(1L)).thenReturn(Optional.of(oil));
        when(plantRepository.findById(1L)).thenReturn(Optional.of(plant));
        when(oilRepository.save(oil)).thenReturn(oil);
        when(oilMapper.toResponse(oil)).thenReturn(oilResponse);

        OilResponse response = oilService.updateOil(1L, oilRequest);

        assertNotNull(response);
        verify(oilMapper, times(1)).updateEntity(oil, oilRequest);
    }

    @Test
    void deleteOil_ShouldNotThrowException_WhenOilExists() {
        when(oilRepository.existsById(1L)).thenReturn(true);
        doNothing().when(oilRepository).deleteById(1L);

        assertDoesNotThrow(() -> {
            oilService.deleteOil(1L);
        });
        verify(oilRepository, times(1)).deleteById(1L);
    }

    @Test
    void getOilsByPlantId_ShouldReturnPageOfOilResponses() {
        Page<Oil> oilPage = new PageImpl<>(List.of(oil), pageable, 1);
        when(oilRepository.findByPlantId(1L, pageable)).thenReturn(oilPage);
        when(oilMapper.toResponse(oil)).thenReturn(oilResponse);

        Page<OilResponse> response = oilService.getOilsByPlantId(1L, pageable);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
    }
}
