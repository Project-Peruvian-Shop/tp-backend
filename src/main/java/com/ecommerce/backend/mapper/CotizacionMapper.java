package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.cotizacion.CotizacionByUsuarioResponseDTO;
import com.ecommerce.backend.dto.cotizacion.CotizacionDashboardDTO;
import com.ecommerce.backend.dto.cotizacion.CotizacionFullResponseDTO;
import com.ecommerce.backend.dto.cotizacion.ProductoFullResponseDTO;
import com.ecommerce.backend.entity.Cotizacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CotizacionMapper {
    public static CotizacionByUsuarioResponseDTO toDTOGetByUser(Cotizacion cotizacion) {
        if (cotizacion == null) {
            return null;
        }

        return CotizacionByUsuarioResponseDTO.builder()
                .id(cotizacion.getId())
                .numero(cotizacion.getNumero())
                .creacion(cotizacion.getCreacion())
                .estado(cotizacion.getEstado())
                .build();
    }

    public static CotizacionFullResponseDTO toDTOGetByID(Cotizacion cotizacion) {
        if (cotizacion == null) {
            return null;
        }


        List<ProductoFullResponseDTO> productos = cotizacion.getDetalles().stream()
                .map(det -> ProductoFullResponseDTO.builder()
                        .name(det.getProducto().getNombre())
                        .cantidad(det.getCantidad())
                        .build())
                .toList();


        return CotizacionFullResponseDTO.builder()
                .id(cotizacion.getId())
                .numero(cotizacion.getNumero())
                .estado(cotizacion.getEstado())
                .creacion(cotizacion.getCreacion())
                .comentario(cotizacion.getComentario())
                .productos(productos)
                .tipoDocumento(cotizacion.getTipo_documento())
                .documento(cotizacion.getDocumento())
                .cliente(cotizacion.getUsuario().getNombre())
                .email(cotizacion.getEmail())
                .telefono(cotizacion.getTelefono())
                .observaciones(cotizacion.getObservaciones())
                .cotizacionEnlace(cotizacion.getPdf() != null ? cotizacion.getPdf().getEnlace() : null)
                .build();
    }

    public static CotizacionDashboardDTO toDashboardDTO(Cotizacion cotizacion) {
        if (cotizacion == null) {
            return null;
        }

        return new CotizacionDashboardDTO(
                cotizacion.getId(),
                cotizacion.getNumero(),
                cotizacion.getNombre(),
                cotizacion.getDocumento(),
                cotizacion.getCreacion(),
                cotizacion.getEstado(),
                cotizacion.getComentario(),
                cotizacion.getObservaciones()
        );
    }
}
