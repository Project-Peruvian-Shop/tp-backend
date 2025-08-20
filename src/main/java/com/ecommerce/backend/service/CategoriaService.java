package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Categoria;
import com.ecommerce.backend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public Page<Categoria> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoriaRepository.findAll(pageable);
    }
}
