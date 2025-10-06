package com.ecommerce.backend.dto.cotizacion;

import com.ecommerce.backend.enums.MensajeTipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDashboardDTO {
    private Long id;
    private String contenido;
    private MensajeTipoEnum tipo;
}
