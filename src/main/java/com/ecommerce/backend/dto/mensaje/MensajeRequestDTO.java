package com.ecommerce.backend.dto.mensaje;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeRequestDTO {
    private String nombre;
    private Integer tipoDocumento;
    private String documento;
    private String telefono;
    private String email;
    private String contenido;
}
