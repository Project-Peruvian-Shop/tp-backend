package com.ecommerce.backend.dto.cotizacion;

import com.ecommerce.backend.enums.CotizacionEstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionResumenDTO {
    private Long id;
    private String numero;
    private Long totalItems;
    private CotizacionEstadoEnum estado;
}
