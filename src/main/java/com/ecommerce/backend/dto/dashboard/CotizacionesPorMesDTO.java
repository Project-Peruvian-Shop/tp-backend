package com.ecommerce.backend.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotizacionesPorMesDTO {
    private Integer year;
    private Integer mes;
    private Long cantidad;
}