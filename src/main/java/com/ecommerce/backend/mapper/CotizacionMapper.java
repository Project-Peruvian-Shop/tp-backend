package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.cotizacion.CotizacionByUsuarioResponseDTO;
import com.ecommerce.backend.entity.Cotizacion;
import org.springframework.stereotype.Component;

@Component
public class CotizacionMapper {
    public static CotizacionByUsuarioResponseDTO toDTOGetByUser(Cotizacion cotizacion) {
        if (cotizacion == null) {
            return null;
        }

        return CotizacionByUsuarioResponseDTO.builder()
                .id(cotizacion.getId())
                .numero(cotizacion.getNumero())
                .creacion(cotizacion.getCreacion().toString())
                .status(cotizacion.getEstado().toString())
                .build();
    }
}
