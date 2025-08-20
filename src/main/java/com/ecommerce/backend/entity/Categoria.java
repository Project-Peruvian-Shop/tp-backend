package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "usos", nullable = false, columnDefinition = "TEXT")
    private String usos;

    @Column(name = "norma", nullable = false, length = 50)
    private String norma;

    @OneToOne
    @JoinColumn(name = "imagen_id", referencedColumnName = "id", nullable = false)
    private Imagen imagen;
}
