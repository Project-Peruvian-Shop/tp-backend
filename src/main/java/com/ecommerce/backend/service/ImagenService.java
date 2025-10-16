package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.imagen.ImagenRequestDTO;
import com.ecommerce.backend.entity.Imagen;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.ImagenMapper;
import com.ecommerce.backend.repository.ImagenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImagenService {
    private final ImagenRepository imagenRepository;
    private final ImagenMapper imagenMapper;
    private final FileUploadService fileUploadService;

    public Imagen findById(Long id) {
        return imagenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Imagen not found with id: " + id));
    }

    public Imagen create(MultipartFile file) throws IOException {
        String originalFileName = Objects.requireNonNull(file.getOriginalFilename());

        String sanitizedFileName = originalFileName.replaceAll("\\s+", "_");

        String filePath = fileUploadService.uploadFile(file, sanitizedFileName, "uploads/imagenes");

        // Generar URL pública
        String publicUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/imagenes/")
                .path(sanitizedFileName)
                .toUriString();

        Imagen imagenEntity = new Imagen();
        imagenEntity.setNombre(sanitizedFileName);
        imagenEntity.setEnlace(publicUrl);
        imagenEntity.setAlt(sanitizedFileName);

        return imagenRepository.save(imagenEntity);
    }
}
