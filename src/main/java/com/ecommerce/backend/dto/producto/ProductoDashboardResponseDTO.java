package com.ecommerce.backend.dto.producto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDashboardResponseDTO {
    private Long id;

    private String imagenEnlace;
    private String imagenAlt;

    private String nombre;
    private String categoriaNombre;
    private String descripcion;
}
