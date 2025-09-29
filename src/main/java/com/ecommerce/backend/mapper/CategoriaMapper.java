package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.categoria.CategoriaDashboardResponseDTO;
import com.ecommerce.backend.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public static CategoriaDashboardResponseDTO toDashboardDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        return CategoriaDashboardResponseDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .usos(categoria.getUsos())
                .norma(categoria.getNorma())
                .imagenEnlace(categoria.getImagen().getEnlace())
                .imagenAlt(categoria.getImagen().getAlt())
                .build();
    }
}
