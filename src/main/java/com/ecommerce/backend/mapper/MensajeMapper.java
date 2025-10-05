package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.mensaje.*;
import com.ecommerce.backend.entity.Mensaje;
import com.ecommerce.backend.enums.MensajeEstadoEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MensajeMapper {
    public static Mensaje toEntity(MensajeRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setNombre(dto.getNombre());
        mensaje.setTipo_documento(dto.getTipoDocumento());
        mensaje.setDocumento(dto.getDocumento());
        mensaje.setTelefono(dto.getTelefono());
        mensaje.setEmail(dto.getEmail());
        mensaje.setContenido(dto.getContenido());
        mensaje.setTipo(dto.getTipo());
        mensaje.setEstado(MensajeEstadoEnum.PENDIENTE);
        mensaje.setCreacion(LocalDateTime.now());
        return mensaje;
    }

    public static MensajeResponseDTO toDTO(Mensaje mensaje) {
        if (mensaje == null) {
            return null;
        }

        return MensajeResponseDTO.builder()
                .id(mensaje.getId())
                .contenido(mensaje.getContenido())
                .tipo(mensaje.getTipo())
                .estado(mensaje.getEstado())
                .creacion(mensaje.getCreacion())
                .usuario(mensaje.getUsuario())
                .build();
    }

    public static MensajeFullResponseDTO toFullDTO(Mensaje mensaje) {
        if (mensaje == null) {
            return null;
        }

        return MensajeFullResponseDTO.builder()
                .id(mensaje.getId())
                .tipo(mensaje.getTipo())
                .estado(mensaje.getEstado())
                .medioRespuesta(mensaje.getMedio_respuesta())
                .creacion(mensaje.getCreacion())
                .contenido(mensaje.getContenido())
                .tipoDocumento(mensaje.getTipo_documento())
                .documento(mensaje.getDocumento())
                .nombre(mensaje.getNombre())
                .telefono(mensaje.getTelefono())
                .email(mensaje.getEmail())
                .build();
    }

    public static MensajeDashboardResponseDTO toDashboardDTO(Mensaje mensaje) {
        if (mensaje == null) {
            return null;
        }

        return MensajeDashboardResponseDTO.builder()
                .id(mensaje.getId())
                .tipo(mensaje.getTipo())
                .mensaje(mensaje.getContenido())
                .creacion(mensaje.getCreacion())
                .estado(mensaje.getEstado())
                .build();
    }
}
