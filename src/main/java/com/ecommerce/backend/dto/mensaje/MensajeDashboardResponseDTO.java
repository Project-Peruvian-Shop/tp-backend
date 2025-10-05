package com.ecommerce.backend.dto.mensaje;

import com.ecommerce.backend.enums.MensajeEstadoEnum;
import com.ecommerce.backend.enums.MensajeTipoEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MensajeDashboardResponseDTO {
    private Long id;
    private MensajeTipoEnum tipo;
    private String mensaje;
    private LocalDateTime creacion;
    private MensajeEstadoEnum estado;
}
