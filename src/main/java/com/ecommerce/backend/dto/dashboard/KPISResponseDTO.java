package com.ecommerce.backend.dto.dashboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KPISResponseDTO {
    private Long cotizacionesPendientes;
    private Long cotizacionesAceptadas;
    private Long mensajesPendientes;
}
