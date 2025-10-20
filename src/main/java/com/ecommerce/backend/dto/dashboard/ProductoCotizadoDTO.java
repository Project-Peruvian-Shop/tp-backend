package com.ecommerce.backend.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoCotizadoDTO {
    private Long productoId;
    private String nombreProducto;
    private Long cantidadApariciones;
}
