package com.ecommerce.backend.dto.mensaje;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MensajeDashboardResponseDTO {
    private Long id;
    private String mensaje;
    private LocalDateTime creacion;
    private Integer estado;
}
