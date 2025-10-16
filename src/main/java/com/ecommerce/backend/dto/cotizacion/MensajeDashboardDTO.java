package com.ecommerce.backend.dto.cotizacion;

import com.ecommerce.backend.enums.MensajeTipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDashboardDTO {
    private Long id;
    private String contenido;
    private MensajeTipoEnum tipo;
    private LocalDateTime creacion;
}
