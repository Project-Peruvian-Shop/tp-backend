package com.ecommerce.backend.dto.cotizacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionResponseDTO {
    private Long cotizacionID;
    private Integer tipoDocumento;
    private String documento;
    private String email;
    private String telefono;
}
