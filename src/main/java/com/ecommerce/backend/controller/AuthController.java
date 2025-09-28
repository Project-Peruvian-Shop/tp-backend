package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.usuario.LoginRequestDTO;
import com.ecommerce.backend.dto.usuario.UsuarioRequestDTO;
import com.ecommerce.backend.dto.usuario.UsuarioResponseDTO;
import com.ecommerce.backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
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
    private final UsuarioService usuarioService;

    @PostMapping("/register")
    @Operation(
            summary = "Registar a nuevo usuario",
            description = "Ubicación: Registrar  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse> addUser(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = usuarioService.register(usuarioRequestDTO);
            status = HttpStatus.CREATED;
            message = "Usuario created successfully";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error creating usuario";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }

    @PostMapping("/login")
    @Operation(
            summary = "Logear a un usuario",
            description = "Ubicación: Login  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UsuarioResponseDTO data = usuarioService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPasswordd());

        return ResponseEntity.ok(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message("Login successful")
                        .data(data)
                        .details(null)
                        .build()
        );
    }
}
