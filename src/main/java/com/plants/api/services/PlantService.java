package com.plants.api.services;

import com.plants.api.dto.request.PlantRequest;
import com.plants.api.dto.responses.PlantResponse;
import com.plants.api.entities.Plant;
import com.plants.api.exceptions.ResourceNotFoundException;
import com.plants.api.mappers.PlantMapper;
import com.plants.api.repositories.PlantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepository plantRepository;
    private final PlantMapper plantMapper;

    // Crée une nouvelle plante
    @Transactional
    public PlantResponse createPlant(PlantRequest plantRequest) {
        Plant plant = plantMapper.toEntity(plantRequest);
        Plant savedPlant = plantRepository.save(plant);
        return plantMapper.toResponse(savedPlant);
    }

    // Récupère une plante par son ID
    @Cacheable(value = "plants", key = "#id")
    @Transactional(readOnly = true)
    public PlantResponse getPlantById(Long id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + id));
        return plantMapper.toResponse(plant);
    }

    // Récupère toutes les plantes avec pagination
    @Cacheable(value = "plants")
    @Transactional(readOnly = true)
    public Page<PlantResponse> getAllPlants(Pageable pageable) {
        return plantRepository.findAll(pageable)
                .map(plantMapper::toResponse);
    }

    // Met à jour une plante
    @CacheEvict(value = "plants", allEntries = true)
    @Transactional
    public PlantResponse updatePlant(Long id, PlantRequest plantRequest) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + id));

        plantMapper.updateEntity(plant, plantRequest);
        Plant updatedPlant = plantRepository.save(plant);
        return plantMapper.toResponse(updatedPlant);
    }

    // Supprime une plante
    @CacheEvict(value = "plants", allEntries = true)
    @Transactional
    public void deletePlant(Long id) {
        if (!plantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Plant not found with id: " + id);
        }
        plantRepository.deleteById(id);
    }

    // Recherche des plantes par nom
    @Transactional(readOnly = true)
    public Page<PlantResponse> searchPlantsByName(String name, Pageable pageable) {
        return plantRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(plantMapper::toResponse);
    }

    // Recherche des plantes par saison
    @Transactional(readOnly = true)
    public Page<PlantResponse> searchPlantsBySeason(String season, Pageable pageable) {
        return plantRepository.findBySeasonFoundContainingIgnoreCase(season, pageable)
                .map(plantMapper::toResponse);
    }

    // Recherche des plantes par propriété
    @Transactional(readOnly = true)
    public Page<PlantResponse> searchPlantsByProperty(String property, Pageable pageable) {
        return plantRepository.findByProperty(property, pageable)
                .map(plantMapper::toResponse);
    }

    // Recherche avancée
    @Transactional(readOnly = true)
    public Page<PlantResponse> searchPlants(String searchTerm, Pageable pageable) {
        return plantRepository.searchPlants(searchTerm, pageable)
                .map(plantMapper::toResponse);
    }

}
