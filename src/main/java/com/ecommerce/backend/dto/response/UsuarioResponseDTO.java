package com.ecommerce.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String rol;

}
