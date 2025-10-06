package com.ecommerce.backend.dto.cotizacion;

import com.ecommerce.backend.enums.CotizacionTipoDocumentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionResponseDTO {
    private Long cotizacionID;
    private String numero;
    private CotizacionTipoDocumentoEnum tipoDocumento;
    private String documento;
    private String email;
    private String telefono;
}
