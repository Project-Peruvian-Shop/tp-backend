package com.ecommerce.backend.dto.mensaje;

import com.ecommerce.backend.enums.MensajeTipoDocumentoEnum;
import com.ecommerce.backend.enums.MensajeTipoEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeRequestDTO {
    @NotBlank(message = "nombre field cannot be null")
    private String nombre;

    @NotNull(message = "tipoDocumento field cannot be null")
    private MensajeTipoDocumentoEnum tipoDocumento;

    @NotBlank(message = "documento field cannot be null")
    private String documento;

    @NotBlank(message = "telefono field cannot be null")
    private String telefono;

    @NotBlank(message = "email field cannot be null")
    @Email(message = "email field must be a valid email address")
    private String email;

    @NotBlank(message = "contenido field cannot be null")
    private String contenido;

    @NotNull(message = "tipo field cannot be null")
    private MensajeTipoEnum tipo;

    private Long usuario_id;
}
