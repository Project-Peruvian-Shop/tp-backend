package com.ecommerce.backend.dto.mensaje;

import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.enums.MensajeEstadoEnum;
import com.ecommerce.backend.enums.MensajeTipoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MensajeResponseDTO {
    private Long id;
    private String contenido;
    private MensajeTipoEnum tipo;
    private MensajeEstadoEnum estado;
    private LocalDateTime creacion;
    private Usuario usuario;
}
