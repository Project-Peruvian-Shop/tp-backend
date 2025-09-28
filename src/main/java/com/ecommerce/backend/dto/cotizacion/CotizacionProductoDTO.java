package com.ecommerce.backend.dto.cotizacion;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionProductoDTO {
    @NotNull(message = "productoID field cannot be null")
    private Long productoID;

    @NotBlank(message = "cantidad field is required")
    @Min(value = 1, message = "cantidad must be at least 1")
    private Integer cantidad;
}
