package com.ecommerce.backend.dto.cotizacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionDashboardDTO {
    private Long cotizacionID;
    private LocalDateTime cotizacionCreacion;
    private String cotizacionNombre;
    private String cotizacionEmail;
    private String cotizacionTelefono;
    private String cotizacionComentarios;
    private Integer cotizacionEstado;
}
