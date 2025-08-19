package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Imagen;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.repository.ImagenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImagenService {
    private final ImagenRepository imagenRepository;

    public Imagen findById(Long id) {
        return imagenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Imagen not found with id: " + id));
    }
}
