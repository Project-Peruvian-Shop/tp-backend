package com.ecommerce.backend.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaCotizadaDTO {
    private Long categoriaId;
    private String nombreCategoria;
    private Long cantidadCotizaciones;
}
