package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.producto.PaginatedProductoResponseDTO;
import com.ecommerce.backend.mapper.ProductoMapper;
import com.ecommerce.backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ImagenService imagenService;

    public Page<PaginatedProductoResponseDTO> findAllPaginated(Pageable pageable) {
        return productoRepository.findAll(pageable)
                .map(ProductoMapper::toDTO);
    }
}

