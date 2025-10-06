package com.ecommerce.backend.dto.cotizacion;

import com.ecommerce.backend.enums.CotizacionTipoDocumentoEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionRequestDTO {
    @NotNull(message = "usuarioID field cannot be null")
    private Long usuarioID;

    @NotEmpty(message = "productos list cannot be empty")
    private List<CotizacionProductoDTO> productos;

    @NotBlank(message = "nombre field is required")
    private String nombre;

    @NotNull(message = "tipoDocumento field cannot be null")
    private CotizacionTipoDocumentoEnum tipoDocumento;

    @NotBlank(message = "documento field is required")
    private String documento;

    @NotBlank(message = "telefono field is required")
    private String telefono;

    @NotBlank(message = "email field is required")
    @Email(message = "email must be valid")
    private String email;

    private String comentario;
}
