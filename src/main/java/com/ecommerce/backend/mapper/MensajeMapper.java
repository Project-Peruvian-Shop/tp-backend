package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.mensaje.MensajeRequestDTO;
import com.ecommerce.backend.dto.mensaje.ReclamacionesRequestDTO;
import com.ecommerce.backend.entity.Mensaje;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MensajeMapper {
    public static Mensaje toEntity(MensajeRequestDTO dto, int tipo) {
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
        mensaje.setTipo(tipo);
        mensaje.setEstado(0);
        mensaje.setCreacion(LocalDateTime.now());
        return mensaje;
    }

    public static Mensaje toReclamacionesEntity(ReclamacionesRequestDTO dto) {
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
        mensaje.setEstado(0);
        mensaje.setCreacion(LocalDateTime.now());
        return mensaje;
    }
}
