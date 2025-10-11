package com.ecommerce.backend.dto.cotizacion;

import com.ecommerce.backend.enums.CotizacionEstadoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotizacionDashboardDTO {
    private Long id;
    private String numeroCotizacion;
    private String clienteNombre;
    private String clienteDocumento;
    private LocalDateTime creacion;
    private CotizacionEstadoEnum estado;
    private String comentario;
    private String observaciones;
}
