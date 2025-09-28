package com.ecommerce.backend.dto.cotizacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaMesDTO {
    private Long categoriaID;
    private String categoriaNombre;
    private Long categoriaCantidad;
}
