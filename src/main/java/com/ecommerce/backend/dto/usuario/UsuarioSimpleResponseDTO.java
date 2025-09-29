package com.ecommerce.backend.dto.usuario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioSimpleResponseDTO {
    private Long id;
    private String nombreCompleto;
    private String role;
}
