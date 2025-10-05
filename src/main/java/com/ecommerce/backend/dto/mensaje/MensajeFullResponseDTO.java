package com.ecommerce.backend.dto.mensaje;

import com.ecommerce.backend.enums.MensajeEstadoEnum;
import com.ecommerce.backend.enums.MensajeMedioRespuestaEnum;
import com.ecommerce.backend.enums.MensajeTipoDocumentoEnum;
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
public class MensajeFullResponseDTO {
    private Long id;
    private MensajeTipoEnum tipo;
    private MensajeEstadoEnum estado;
    private MensajeMedioRespuestaEnum medioRespuesta;
    private LocalDateTime creacion;
    private String contenido;

    private MensajeTipoDocumentoEnum tipoDocumento;
    private String documento;
    private String nombre;
    private String telefono;
    private String email;
}
