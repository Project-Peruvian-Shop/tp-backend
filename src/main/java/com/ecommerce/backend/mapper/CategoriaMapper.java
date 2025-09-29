package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.categoria.CategoriaDashboardResponseDTO;
import com.ecommerce.backend.dto.categoria.CategoriaRequestDTO;
import com.ecommerce.backend.entity.Categoria;
import com.ecommerce.backend.entity.Imagen;
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

    public static Categoria toEntity(CategoriaRequestDTO dto, Imagen imagen) {
        if (dto == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoria.setUsos(dto.getUsos());
        categoria.setNorma(dto.getNorma());
        categoria.setImagen(imagen);
        return categoria;
    }
}
