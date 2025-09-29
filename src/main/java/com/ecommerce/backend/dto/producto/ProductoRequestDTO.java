package com.ecommerce.backend.dto.producto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoRequestDTO {
    @NotBlank(message = "nombre field cannot be null")
    private String nombre;

    @NotNull(message = "categoriaID field cannot be null")
    @Min(value = 1, message = "categoriaID must be at least 1")
    private Long categoriaID;

    @NotBlank(message = "descripcion field cannot be null")
    private String descripcion;

    @NotNull(message = "imagenID field cannot be null")
    @Min(value = 1, message = "imagenID must be at least 1")
    private Long imagenID;
}
