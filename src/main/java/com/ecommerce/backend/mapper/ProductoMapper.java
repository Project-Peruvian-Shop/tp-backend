package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.producto.PaginatedProductoResponseDTO;
import com.ecommerce.backend.dto.producto.ProductoFullResponseDTO;
import com.ecommerce.backend.entity.Imagen;
import com.ecommerce.backend.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public static PaginatedProductoResponseDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        if (producto.getImagen() == null) {
            Imagen imagen = new Imagen();
            imagen.setEnlace("NOT_FOUND");
            imagen.setAlt("NOT_FOUND");
            producto.setImagen(imagen);
        }

        return PaginatedProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .imagenUrl(producto.getImagen().getEnlace())
                .imagenAlt(producto.getImagen().getAlt())
                .build();
    }

    public static ProductoFullResponseDTO toDTOGetByID(Producto producto) {
        if (producto == null) {
            return null;
        }

        if (producto.getImagen() == null) {
            Imagen imagen = new Imagen();
            imagen.setEnlace("NOT_FOUND");
            imagen.setAlt("NOT_FOUND");
            producto.setImagen(imagen);
        }

        return ProductoFullResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .productoEnlace(producto.getImagen().getEnlace())
                .productoAlt(producto.getImagen().getAlt())
                .categoria(producto.getCategoria().getNombre() + " " + producto.getCategoria().getNorma())
                .categoriaEnlace(producto.getCategoria().getImagen().getEnlace())
                .categoriaAlt(producto.getCategoria().getImagen().getAlt())
                .categoriaUsos(producto.getCategoria().getUsos())
                .build();
    }
}
