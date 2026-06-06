package com.plants.api.controllers;

import com.plants.api.dto.request.PlantRequest;
import com.plants.api.entities.User;
import com.plants.api.entities.enums.Role;
import com.plants.api.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PlantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private PlantRequest plantRequest;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

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
    }

    @Test
    void getAllPlants_ShouldReturnOk_WhenPlantsExist() throws Exception {
        mockMvc.perform(get("/api/plants")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPlantById_ShouldReturnOk_WhenPlantExists() throws Exception {
        // Créer une plante d'abord (nécessite un utilisateur admin)
        User admin = User.builder()
                .email("admin@plants.com")
                .password("admin123")
                .role(Role.ADMIN)
                .build();
        userRepository.save(admin);

        // Créer une plante via un utilisateur admin
        mockMvc.perform(post("/api/plants")
                        .with(user((UserDetails) admin))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantRequest)))
                .andExpect(status().isCreated());

        // Récupérer la plante
        mockMvc.perform(get("/api/plants/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lavande"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void createPlant_ShouldReturnCreated_WhenUserIsAdmin() throws Exception {
        mockMvc.perform(post("/api/plants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Lavande"));
    }

    @Test
    void createPlant_ShouldReturnForbidden_WhenUserIsNotAdmin() throws Exception {
        mockMvc.perform(post("/api/plants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void updatePlant_ShouldReturnOk_WhenPlantExists() throws Exception {
        // Créer une plante d'abord
        mockMvc.perform(post("/api/plants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantRequest)))
                .andExpect(status().isCreated());

        // Mettre à jour la plante
        PlantRequest updatedPlantRequest = PlantRequest.builder()
                .name("Lavande mise à jour")
                .description("Plante aromatique mise à jour")
                .origin("France")
                .seasonFound("Été")
                .properties(List.of("Calmante", "Antiseptique"))
                .uses(List.of("Huile essentielle", "Infusion"))
                .imageUrl("https://plants.com/lavande.jpg")
                .affiliateLink("https://plants.com/affiliate/lavande")
                .build();

        mockMvc.perform(put("/api/plants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPlantRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lavande mise à jour"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deletePlant_ShouldReturnNoContent_WhenPlantExists() throws Exception {
        // Créer une plante d'abord
        mockMvc.perform(post("/api/plants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plantRequest)))
                .andExpect(status().isCreated());

        // Supprimer la plante
        mockMvc.perform(delete("/api/plants/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void searchPlantsByName_ShouldReturnOk_WhenPlantsExist() throws Exception {
        mockMvc.perform(get("/api/plants/search/name")
                        .param("name", "Lavande")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
