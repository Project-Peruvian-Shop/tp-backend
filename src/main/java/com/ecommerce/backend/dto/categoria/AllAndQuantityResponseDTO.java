package com.ecommerce.backend.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllAndQuantityResponseDTO {
    private Long id;
    private String nombre;
    private Long cantidad;
}
