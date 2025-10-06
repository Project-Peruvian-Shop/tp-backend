package com.ecommerce.backend.dto.cotizacion;

import com.ecommerce.backend.enums.CotizacionEstadoEnum;
import com.ecommerce.backend.enums.CotizacionTipoDocumentoEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CotizacionFullResponseDTO {
    private Long id;

    private String numero;
    private CotizacionEstadoEnum estado;
    private LocalDateTime creacion;
    private String comentario;

    private List<ProductoFullResponseDTO> productos;

    private CotizacionTipoDocumentoEnum tipoDocumento;
    private String documento;
    private String cliente;
    private String email;
    private String telefono;

    private String observaciones;
    private String cotizacionEnlace;
}
