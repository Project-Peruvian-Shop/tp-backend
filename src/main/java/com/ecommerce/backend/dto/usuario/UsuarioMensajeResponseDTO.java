package com.ecommerce.backend.dto.usuario;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioMensajeResponseDTO {
    private String tipoDocumento;
    private String documento;
    private String nombreCompleto;
    private String email;
    private String telefono;
}
