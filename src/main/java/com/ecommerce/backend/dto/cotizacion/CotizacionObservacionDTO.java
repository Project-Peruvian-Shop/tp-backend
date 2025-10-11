package com.ecommerce.backend.dto.cotizacion;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CotizacionObservacionDTO {
    @NotNull(message = " observaciones field cannot be null")
    private String observaciones;
}