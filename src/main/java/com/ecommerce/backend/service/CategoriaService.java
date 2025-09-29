package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.categoria.AllAndQuantityResponseDTO;
import com.ecommerce.backend.dto.categoria.CategoriaDashboardResponseDTO;
import com.ecommerce.backend.entity.Categoria;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.CategoriaMapper;
import com.ecommerce.backend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria not found with id: " + id));
    }


    public Long countAllCategorias() {
        return categoriaRepository.countAllCategoria();
    }


    public Page<CategoriaDashboardResponseDTO> findAllPaginatedDashboard(Pageable pageable) {
        return categoriaRepository.findAll(pageable)
                .map(CategoriaMapper::toDashboardDTO);
    }


    public Page<CategoriaDashboardResponseDTO> searchByNombreUsosNorma(String busqueda, Pageable pageable) {
        return categoriaRepository.searchByNombreUsosNorma(busqueda, pageable)
                .map(CategoriaMapper::toDashboardDTO);
    }
}
