package com.ecommerce.backend.dto.usuario;

import lombok.Builder;

@Builder
public class UsuarioPerfilDTO {
    private Long id;

    private String nombre;
    private String rol;

    private String email;
    private String telefono;
}
