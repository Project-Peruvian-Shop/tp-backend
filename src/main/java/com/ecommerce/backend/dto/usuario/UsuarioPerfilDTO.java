package com.ecommerce.backend.dto.usuario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioPerfilDTO {
    private Long id;

    private String nombre;
    private String rol;

    private String email;
    private String telefono;
}
