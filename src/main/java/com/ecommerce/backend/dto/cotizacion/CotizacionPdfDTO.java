package com.ecommerce.backend.dto.cotizacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionPdfDTO {
    private Long id;
    private String archivo;
    private LocalDateTime creacion;
    private Long cotizacionID;
}
