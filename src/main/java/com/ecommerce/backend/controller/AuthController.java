package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.auth.LoginRequestDTO;
import com.ecommerce.backend.dto.auth.LoginResponseDTO;
import com.ecommerce.backend.dto.auth.RegisterRequestDTO;
import com.ecommerce.backend.dto.auth.RegisterResponseDTO;
import com.ecommerce.backend.dto.usuario.UsuarioResponseDTO;
import com.ecommerce.backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.API_VERSION + "/" + Constant.AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(
            summary = "Registar a nuevo usuario",
            description = "Ubicación: Registrar  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse<RegisterResponseDTO>> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        try {
            RegisterResponseDTO data = authService.register(registerRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(GlobalResponse.success(data, "Usuario creado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(GlobalResponse.failure("Error al crear el usuario", e.getMessage()));
        }
    }

    @PostMapping("/login")
    @Operation(
            summary = "Logear a un usuario",
            description = "Ubicación: Login  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            LoginResponseDTO data = authService.login(loginRequestDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(GlobalResponse.success(data, "Login exitoso"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(GlobalResponse.failure("Error al iniciar sesión", e.getMessage()));
        }
    }
}
