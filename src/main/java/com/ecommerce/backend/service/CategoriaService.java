package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.categoria.AllAndQuantityResponseDTO;
import com.ecommerce.backend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ImagenService imagenService;

    public List<AllAndQuantityResponseDTO> findAllAndQuantity() {
        return categoriaRepository.findAllAndQuantity()
                .stream()
                .map(obj -> new AllAndQuantityResponseDTO(
                        ((Number) obj[0]).longValue(),
                        (String) obj[1],
                        ((Number) obj[2]).longValue()
                ))
                .toList();
    }
}
