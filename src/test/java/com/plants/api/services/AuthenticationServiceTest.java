package com.plants.api.services;

import com.plants.api.dto.request.LoginRequest;
import com.plants.api.dto.request.RefreshTokenRequest;
import com.plants.api.dto.request.RegisterRequest;
import com.plants.api.dto.responses.AuthResponse;
import com.plants.api.entities.User;
import com.plants.api.entities.enums.Role;
import com.plants.api.exceptions.BadRequestException;
import com.plants.api.exceptions.UnauthorizedException;
import com.plants.api.repositories.UserRepository;
import com.plants.api.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private User user;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        registerRequest = RegisterRequest.builder()
                .email("test@plants.com")
                .password("password123")
                .build();

        loginRequest = LoginRequest.builder()
                .email("test@plants.com")
                .password("password123")
                .build();

        user = User.builder()
                .id(1L)
                .email("test@plants.com")
                .password("encodedPassword")
                .role(Role.USER)
                .build();

        userDetails = org.springframework.security.core.userdetails.User.builder()
                .username("test@plants.com")
                .password("encodedPassword")
                .roles("USER")
                .build();
    }

    @Test
    void register_ShouldReturnAuthResponse_WhenUserDoesNotExist() {
        when(userRepository.existsByEmail(registerRequest.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtUtils.generateAccessToken(userDetails)).thenReturn("accessToken");
        when(jwtUtils.generateRefreshToken(userDetails)).thenReturn("refreshToken");

        AuthResponse response = authenticationService.register(registerRequest);

        assertNotNull(response);
        assertEquals("accessToken", response.getAccessToken());
        assertEquals("refreshToken", response.getRefreshToken());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void register_ShouldThrowBadRequestException_WhenEmailAlreadyExists() {
        when(userRepository.existsByEmail(registerRequest.getEmail())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> {
            authenticationService.register(registerRequest);
        });
    }

    @Test
    void login_ShouldReturnAuthResponse_WhenCredentialsAreValid() {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(user));
        when(jwtUtils.generateAccessToken(userDetails)).thenReturn("accessToken");
        when(jwtUtils.generateRefreshToken(userDetails)).thenReturn("refreshToken");

        AuthResponse response = authenticationService.login(loginRequest);

        assertNotNull(response);
        assertEquals("accessToken", response.getAccessToken());
        assertEquals("refreshToken", response.getRefreshToken());
    }

    @Test
    void login_ShouldThrowUnauthorizedException_WhenUserNotFound() {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(loginRequest.getEmail())).thenReturn(Optional.empty());

        assertThrows(UnauthorizedException.class, () -> {
            authenticationService.login(loginRequest);
        });
    }

    @Test
    void refreshToken_ShouldReturnNewTokens_WhenRefreshTokenIsValid() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("validRefreshToken");
        when(jwtUtils.extractUsername("validRefreshToken")).thenReturn("test@plants.com");
        when(userRepository.findByEmail("test@plants.com")).thenReturn(Optional.of(user));
        when(jwtUtils.validateToken("validRefreshToken", userDetails)).thenReturn(true);
        when(jwtUtils.generateAccessToken(userDetails)).thenReturn("newAccessToken");
        when(jwtUtils.generateRefreshToken(userDetails)).thenReturn("newRefreshToken");

        AuthResponse response = authenticationService.refreshToken(refreshTokenRequest);

        assertNotNull(response);
        assertEquals("newAccessToken", response.getAccessToken());
        assertEquals("newRefreshToken", response.getRefreshToken());
    }

    @Test
    void refreshToken_ShouldThrowUnauthorizedException_WhenRefreshTokenIsInvalid() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("invalidRefreshToken");
        when(jwtUtils.extractUsername("invalidRefreshToken")).thenReturn("test@plants.com");
        when(userRepository.findByEmail("test@plants.com")).thenReturn(Optional.of(user));
        when(jwtUtils.validateToken("invalidRefreshToken", userDetails)).thenReturn(false);

        assertThrows(UnauthorizedException.class, () -> {
            authenticationService.refreshToken(refreshTokenRequest);
        });
    }
}
