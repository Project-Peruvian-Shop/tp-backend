package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imagen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enlace", nullable = false)
    private String enlace;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "alt", length = 50, nullable = false)
    private String alt;
}
