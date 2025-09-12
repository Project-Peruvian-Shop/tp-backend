package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensaje")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 200)
    private String nombre;

    @Column(name = "tipo_documento")
    private Integer tipo_documento;

    @Column(name = "documento", length = 30)
    private String documento;

    @Column(name = "telefono", length = 25)
    private String telefono;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "contenido",nullable = false)
    private String contenido;

    @Column(name = "tipo",nullable = false)
    private Integer tipo;

    @Column(name = "estado",nullable = false)
    private Integer estado;

    @Column(name = "creacion",nullable = false)
    private LocalDateTime creacion;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = true)
    private Usuario usuario;
}
