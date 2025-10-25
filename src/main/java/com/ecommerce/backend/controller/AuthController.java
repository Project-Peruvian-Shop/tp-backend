package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.auth.*;
import com.ecommerce.backend.security.JwtUtil;
import com.ecommerce.backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.API_VERSION + "/" + Constant.AUTH)
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    @Operation(
            summary = "Registar a nuevo usuario",
            description = "Ubicación: Registrar  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse<RegisterResponseDTO>> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        RegisterResponseDTO data = authService.register(registerRequestDTO);

        String accessToken = jwtUtil.generateAccessToken(data.getEmail(),data.getRol().toString());
        String refreshToken = jwtUtil.generateRefreshToken(data.getEmail());

        RegisterResponseDTO response = RegisterResponseDTO.builder()
                .id(data.getId())
                .nombre(data.getNombre())
                .apellidos(data.getApellidos())
                .email(data.getEmail())
                .telefono(data.getTelefono())
                .rol(data.getRol())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GlobalResponse.success(response, "Usuario creado exitosamente"));
    }

    @PostMapping("/login")
    @Operation(
            summary = "Logear a un usuario",
            description = "Ubicación: Login  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO data = authService.login(loginRequestDTO);

        String accessToken = jwtUtil.generateAccessToken(data.getEmail(),data.getRol().toString());
        String refreshToken = jwtUtil.generateRefreshToken(data.getEmail());

        LoginResponseDTO response = LoginResponseDTO.builder()
                .id(data.getId())
                .nombre(data.getNombre())
                .apellidos(data.getApellidos())
                .email(data.getEmail())
                .telefono(data.getTelefono())
                .rol(data.getRol())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(response, "Login exitoso"));
    }
    @PostMapping("/refresh-token")
    @Operation(summary = "Refrescar el token de acceso",
            description = "Ubicación: Refresh Token  \n" +
                    "Seguridad: Pública(con refresh token)"
    )
    public ResponseEntity<GlobalResponse<RefreshTokenResponseDTO>> refreshToken(@Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        String refreshToken = refreshTokenRequestDTO.getRefreshToken();
        if(!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(GlobalResponse.failure( "Refresh token inválido o expirado", null));

        }
        String email = jwtUtil.getEmailFromToken(refreshToken);
        String role = authService.getRoleByEmail(email);
        String newAccessToken = jwtUtil.generateAccessToken(email, role);

        RefreshTokenResponseDTO response = RefreshTokenResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(response, "Token de acceso refrescado exitosamente"));
    }

}
