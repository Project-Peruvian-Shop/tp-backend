package com.ecommerce.backend.dto.cotizacion;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoFullResponseDTO {
    private String name;
    private Integer cantidad;
}