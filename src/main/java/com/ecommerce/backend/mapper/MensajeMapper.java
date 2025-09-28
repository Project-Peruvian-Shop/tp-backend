package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.mensaje.MensajeRequestDTO;
import com.ecommerce.backend.entity.Mensaje;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MensajeMapper {
    public static Mensaje toEntity(MensajeRequestDTO dto, int tipo){
        if(dto == null){
            return null;
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setNombre(dto.getMensaje_nombre());
        mensaje.setTipo_documento(dto.getMensaje_tipo_documento());
        mensaje.setDocumento(dto.getMensaje_documento());
        mensaje.setTelefono(dto.getMensaje_telefono());
        mensaje.setEmail(dto.getMensaje_email());
        mensaje.setContenido(dto.getMensaje_contenido());
        mensaje.setTipo(tipo);
        mensaje.setEstado(0);
        mensaje.setCreacion(LocalDateTime.now());
        return mensaje;
    }
}
