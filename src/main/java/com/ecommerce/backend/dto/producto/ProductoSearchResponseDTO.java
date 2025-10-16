package com.ecommerce.backend.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoSearchResponseDTO {
    private Long id;
    private String nombre;
    private String imagenEnlace;
    private String imagenAlt;
    private String categoriaNombre;
    private String categoriaNorma;
}
