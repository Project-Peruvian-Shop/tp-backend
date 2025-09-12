package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.imagen.ImagenRequestDTO;
import com.ecommerce.backend.entity.Imagen;
import org.springframework.stereotype.Component;

@Component
public class ImagenMapper {
    public Imagen toEntity(ImagenRequestDTO imagenRequestDTO) {
        if (imagenRequestDTO == null) return null;

        Imagen imagen = new Imagen();
        imagen.setEnlace(imagenRequestDTO.getEnlace());
        imagen.setNombre(imagenRequestDTO.getNombre());
        imagen.setAlt(imagenRequestDTO.getAlt());
        return imagen;
    }

}
