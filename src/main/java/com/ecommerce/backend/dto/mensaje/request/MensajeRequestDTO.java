package com.ecommerce.backend.dto.mensaje.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeRequestDTO {
    private String mensaje_nombre;
    private Integer mensaje_tipo_documento;
    private String mensaje_documento;
    private String mensaje_telefono;
    private String mensaje_email;
    private String mensaje_contenido;
}
