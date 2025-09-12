package com.ecommerce.backend.entity;

import com.ecommerce.backend.config.Constant;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = Constant.TABLE_PRODUCTOS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Imagen imagen;
    private Categoria categoria;
}
