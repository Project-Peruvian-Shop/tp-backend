package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cotizacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String numero;

    @Column(nullable = false)
    private Integer estado = 0;

    @Column(nullable = false)
    private LocalDateTime creacion = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String comentario;

    // datos del cliente
    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(name = "tipo_documento", nullable = false)
    private Integer tipoDocumento = 0;

    @Column(nullable = false, length = 30)
    private String documento;

    @Column(nullable = false, length = 25)
    private String telefono;

    @Column(nullable = false, length = 150)
    private String email;

    // empresa
    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CotizacionDetalle> detalles = new ArrayList<>();

    @OneToOne(mappedBy = "cotizacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private CotizacionPDF pdf;
}
