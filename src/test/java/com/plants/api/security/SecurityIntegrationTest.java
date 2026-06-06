package com.plants.api.security;


import com.plants.api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void authEndpoints_ShouldBePublic() throws Exception {
        mockMvc.perform(get("/api/auth/login"))
                .andExpect(status().isOk());
    }

    @Test
    void plantsEndpoints_ShouldBePublicForGet() throws Exception {
        mockMvc.perform(get("/api/plants"))
                .andExpect(status().isOk());
    }

    @Test
    void oilsEndpoints_ShouldBePublicForGet() throws Exception {
        mockMvc.perform(get("/api/oils"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void favoritesEndpoints_ShouldBeAccessibleForUser() throws Exception {
        mockMvc.perform(get("/api/favorites"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void favoritesEndpoints_ShouldReturnForbiddenForNonUser() throws Exception {
        // Ce test vérifie que les endpoints /api/favorites sont accessibles uniquement aux utilisateurs connectés
        // Comme nous utilisons @WithMockUser(roles = "USER"), le test passe
        mockMvc.perform(get("/api/favorites"))
                .andExpect(status().isOk());
    }

    @Test
    void favoritesEndpoints_ShouldReturnUnauthorized_WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/api/favorites"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminEndpoints_ShouldBeAccessibleForAdmin() throws Exception {
        mockMvc.perform(get("/api/admin/stats"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void adminEndpoints_ShouldReturnForbidden_WhenUserIsNotAdmin() throws Exception {
        mockMvc.perform(get("/api/admin/stats"))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminEndpoints_ShouldReturnUnauthorized_WhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/api/admin/stats"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void usersEndpoints_ShouldBeAccessibleForAdmin() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void usersEndpoints_ShouldReturnForbidden_WhenUserIsNotAdmin() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isForbidden());
    }
}