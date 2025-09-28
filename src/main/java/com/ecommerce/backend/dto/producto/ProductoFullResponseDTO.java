package com.ecommerce.backend.dto.producto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoFullResponseDTO {
    private Long id;

    private String nombre;
    private String descripcion;

    private String productoEnlace;
    private String productoAlt;

    private Long categoriaId;
    private String categoria;
    private String categoriaEnlace;
    private String categoriaAlt;
    private String categoriaUsos;
}
