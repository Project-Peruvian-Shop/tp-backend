package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "cotizacion_pdf")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CotizacionPDF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String enlace;

    @Column(nullable = false)
    private LocalDateTime creacion = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "cotizacion_id", nullable = false, unique = true)
    private Cotizacion cotizacion;
}
