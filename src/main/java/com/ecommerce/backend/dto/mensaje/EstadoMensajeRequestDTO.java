package com.ecommerce.backend.dto.mensaje;

import com.ecommerce.backend.enums.MensajeEstadoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoMensajeRequestDTO {
    @NotNull(message = "nuevoEstado field cannot be null")
    private MensajeEstadoEnum nuevoEstado;
}
