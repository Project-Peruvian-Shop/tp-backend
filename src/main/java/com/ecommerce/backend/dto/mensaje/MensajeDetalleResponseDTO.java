package com.ecommerce.backend.dto.mensaje;

import com.ecommerce.backend.enums.MensajeEstadoEnum;
import com.ecommerce.backend.enums.MensajeMedioRespuestaEnum;
import com.ecommerce.backend.enums.MensajeTipoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDetalleResponseDTO {
    private Long id;
    private MensajeTipoEnum mensaje_tipo;
    private MensajeEstadoEnum mensaje_estado;
    private MensajeMedioRespuestaEnum mensaje_medio_respuesta;
    private String mensaje_contenido;
    private MensajeTipoEnum mensaje_tipo_documento;
    private String mensaje_documento;
    private String mensaje_nombre;
    private String mensaje_telefono;
    private String mensaje_email;
}
