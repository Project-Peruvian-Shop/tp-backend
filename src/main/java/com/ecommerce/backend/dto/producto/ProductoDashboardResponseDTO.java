package com.ecommerce.backend.dto.producto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDashboardResponseDTO {
    private Long id;

    private String categoriaImagen;
    private String categoriaAlt;

    private String nombre;
    private String categoriaNombre;
    private String descripcion;
}
