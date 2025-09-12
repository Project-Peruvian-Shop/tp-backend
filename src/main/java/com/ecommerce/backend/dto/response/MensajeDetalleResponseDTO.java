package com.ecommerce.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDetalleResponseDTO {
    private Long id;
    private Integer mensaje_tipo;
    private Integer mensaje_estado;
    private String mensaje_contenido;
    private Integer mensaje_tipo_documento;
    private String mensaje_documento;
    private String mensaje_nombre;
    private String mensaje_telefono;
    private String mensaje_email;
}
