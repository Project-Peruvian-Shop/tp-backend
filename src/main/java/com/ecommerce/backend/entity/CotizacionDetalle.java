package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cotizacion_detalle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionDetalle {
    @EmbeddedId
    private CotizacionDetalleId id = new CotizacionDetalleId();

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @MapsId("cotizacionId")
    @JoinColumn(name = "cotizacion_id")
    private Cotizacion cotizacion;
}
