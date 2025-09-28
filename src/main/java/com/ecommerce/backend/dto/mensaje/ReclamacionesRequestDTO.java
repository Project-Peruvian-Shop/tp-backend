package com.ecommerce.backend.dto.mensaje;

import lombok.Data;

@Data
public class ReclamacionesRequestDTO {
    private String nombre;
    private Integer tipoDocumento;
    private String documento;
    private String telefono;
    private String email;
    private String contenido;
    private Integer tipo;
}
