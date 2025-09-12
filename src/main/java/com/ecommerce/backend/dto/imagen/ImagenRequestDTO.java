package com.ecommerce.backend.dto.imagen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagenRequestDTO {
    @NotBlank(message = "enlace field is required")
    @NotNull(message = "enlace field cannot be null")
    private String enlace;

    @NotBlank(message = "nombre field is required")
    @NotNull(message = "nombre field cannot be null")
    private String nombre;

    @NotBlank(message = "alt field is required")
    @NotNull(message = "alt field cannot be null")
    private String alt;
}
