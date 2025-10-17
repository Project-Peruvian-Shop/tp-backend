package com.ecommerce.backend.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCarritoDetalleDTO {
    private Long id;
    private String nombre;
    private String categoriaNombre;
    private String categoriaNorma;
    private String imagenEnlace;
    private String imagenAlt;
    private Integer cantidad;
}
