package com.ecommerce.backend.dto.cotizacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotizacionHistorialEstadoResponseDTO {
    private Long id;
    private String estadoAnterior;
    private String estadoNuevo;
    private String observacion;
    private LocalDateTime fechaCambio;
    private String usuarioNombre;
    private String usuarioEmail;
}
