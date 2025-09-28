package com.ecommerce.backend.dto.cotizacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCotizacionMesDTO {
    private Long usuarios_nuevos_mes;
    private Long usuarios_cotizadores_mes;
}
