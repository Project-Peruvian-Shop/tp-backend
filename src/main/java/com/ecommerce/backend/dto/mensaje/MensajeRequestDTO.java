package com.ecommerce.backend.dto.mensaje;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeRequestDTO {
    @NotNull(message = "nombre field cannot be null")
    private String nombre;

    @NotNull(message = "tipoDocumento field cannot be null")
    @Min(value = 1, message = "tipoDocumento must be at least 1")
    private Integer tipoDocumento;

    @NotNull(message = "documento field cannot be null")
    private String documento;

    @NotNull(message = "telefono field cannot be null")
    private String telefono;

    @NotNull(message = "email field cannot be null")
    private String email;

    @NotNull(message = "contenido field cannot be null")
    private String contenido;
}
