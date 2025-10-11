package com.ecommerce.backend.dto.cotizacion;

import com.ecommerce.backend.enums.CotizacionEstadoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCotizacionRequestDTO {
    @NotNull(message = "nuevoEstado field cannot be null")
    private CotizacionEstadoEnum nuevoEstado;
}
