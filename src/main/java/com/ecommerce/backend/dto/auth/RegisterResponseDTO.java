package com.ecommerce.backend.dto.auth;

import com.ecommerce.backend.enums.UsuarioRolEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private UsuarioRolEnum rol;
    private String accessToken;
    private String refreshToken;
}
