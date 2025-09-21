package com.ecommerce.backend.entity;

import com.ecommerce.backend.config.Constant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Constant.TABLE_CATEGORIA)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "usos", nullable = false)
    private String usos;

    @Column(name = "norma", nullable = false, length = 50)
    private String norma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imagen_id", referencedColumnName = "id")
    private Imagen imagen;
}
