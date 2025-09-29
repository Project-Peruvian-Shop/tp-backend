package com.ecommerce.backend.dto.categoria;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDashboardResponseDTO {
    private Long id;
    private String nombre;
    private String usos;
    private String norma;
    private String imagenEnlace;
    private String imagenAlt;
}
