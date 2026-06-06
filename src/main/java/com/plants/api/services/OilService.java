package com.plants.api.services;

import com.plants.api.dto.request.OilRequest;
import com.plants.api.dto.responses.OilResponse;
import com.plants.api.entities.Oil;
import com.plants.api.entities.Plant;
import com.plants.api.exceptions.ResourceNotFoundException;
import com.plants.api.mappers.OilMapper;
import com.plants.api.repositories.OilRepository;
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
public class OilService {

    private final OilRepository oilRepository;
    private final PlantRepository plantRepository;
    private final OilMapper oilMapper;

    // Crée une nouvelle huile
    @Transactional
    public OilResponse createOil(OilRequest oilRequest) {
        Plant plant = plantRepository.findById(oilRequest.getPlantId())
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + oilRequest.getPlantId()));

        Oil oil = oilMapper.toEntity(oilRequest);
        oil.setPlant(plant);
        Oil savedOil = oilRepository.save(oil);
        return oilMapper.toResponse(savedOil);
    }

    // Récupère une huile par son ID
    @Cacheable(value = "oils", key = "#id")
    @Transactional(readOnly = true)
    public OilResponse getOilById(Long id) {
        Oil oil = oilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oil not found with id: " + id));
        return oilMapper.toResponse(oil);
    }

    // Récupère toutes les huiles avec pagination
    @Cacheable(value = "oils")
    @Transactional(readOnly = true)
    public Page<OilResponse> getAllOils(Pageable pageable) {
        return oilRepository.findAll(pageable)
                .map(oilMapper::toResponse);
    }

    // Met à jour une huile
    @CacheEvict(value = "oils", allEntries = true)
    @Transactional
    public OilResponse updateOil(Long id, OilRequest oilRequest) {
        Oil oil = oilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Oil not found with id: " + id));

        Plant plant = plantRepository.findById(oilRequest.getPlantId())
                .orElseThrow(() -> new ResourceNotFoundException("Plant not found with id: " + oilRequest.getPlantId()));

        oil.setPlant(plant);
        oilMapper.updateEntity(oil, oilRequest);
        Oil updatedOil = oilRepository.save(oil);
        return oilMapper.toResponse(updatedOil);
    }

    // Supprime une huile
    @CacheEvict(value = "oils", allEntries = true)
    @Transactional
    public void deleteOil(Long id) {
        if (!oilRepository.existsById(id)) {
            throw new ResourceNotFoundException("Oil not found with id: " + id);
        }
        oilRepository.deleteById(id);
    }

    // Recherche des huiles par plante
    @Transactional(readOnly = true)
    public Page<OilResponse> getOilsByPlantId(Long plantId, Pageable pageable) {
        return oilRepository.findByPlantId(plantId, pageable)
                .map(oilMapper::toResponse);
    }

    // Recherche des huiles par bénéfice
    @Transactional(readOnly = true)
    public Page<OilResponse> searchOilsByBenefit(String benefit, Pageable pageable) {
        return oilRepository.findByBenefit(benefit, pageable)
                .map(oilMapper::toResponse);
    }

    // Recherche avancée
    @Transactional(readOnly = true)
    public Page<OilResponse> searchOils(String searchTerm, Pageable pageable) {
        return oilRepository.searchOils(searchTerm, pageable)
                .map(oilMapper::toResponse);
    }
}