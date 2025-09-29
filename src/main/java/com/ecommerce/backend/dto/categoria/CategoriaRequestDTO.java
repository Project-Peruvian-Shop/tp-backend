package com.ecommerce.backend.dto.categoria;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaRequestDTO {
    @NotBlank(message = "nombre field cannot be null")
    private String nombre;

    @NotBlank(message = "descripcion field cannot be null")
    private String norma;

    @NotBlank(message = "usos field cannot be null")
    private String usos;

    @NotNull(message = "imagenId field cannot be null")
    @Min(value = 1, message = "imagenID must be at least 1")
    private Long imagenId;
}
