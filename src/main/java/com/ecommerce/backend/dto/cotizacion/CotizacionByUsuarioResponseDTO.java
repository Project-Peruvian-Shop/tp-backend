package com.ecommerce.backend.dto.cotizacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotizacionByUsuarioResponseDTO {
    private Long id;
    private String numero;
    private LocalDateTime creacion;
    private String status;
}
