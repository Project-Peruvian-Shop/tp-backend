package com.ecommerce.backend.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String passwordd;
    private String rol;
}
