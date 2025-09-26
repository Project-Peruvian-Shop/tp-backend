package com.ecommerce.backend.dto.cotizacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionYearDTO {
    private String cotizacionesNombreMes;
    private Long cotizacionesCantidadMes;
}
