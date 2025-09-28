package com.ecommerce.backend.dto.cotizacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionRequestDTO {
    private Long usuarioID;
    private List<CotizacionProductoDTO> productos;
    private String nombre;
    private Integer tipoDocumento;
    private String documento;
    private String telefono;
    private String email;
    private String comentario;
    private String observaciones;
}
