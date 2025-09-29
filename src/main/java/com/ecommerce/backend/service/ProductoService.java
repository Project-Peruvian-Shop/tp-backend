package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.producto.PaginatedProductoResponseDTO;
import com.ecommerce.backend.dto.producto.ProductoDashboardResponseDTO;
import com.ecommerce.backend.dto.producto.ProductoFullResponseDTO;
import com.ecommerce.backend.dto.producto.ProductoRequestDTO;
import com.ecommerce.backend.entity.Categoria;
import com.ecommerce.backend.entity.Imagen;
import com.ecommerce.backend.entity.Producto;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.ProductoMapper;
import com.ecommerce.backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ImagenService imagenService;
    private final CategoriaService categoriaService;

    public Page<PaginatedProductoResponseDTO> findAllPaginated(Pageable pageable) {
        return productoRepository.findAll(pageable)
                .map(ProductoMapper::toDTO);
    }


    public ProductoFullResponseDTO findByID(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrada con id: " + id));

        return ProductoMapper.toDTOGetByID(producto);
    }


    public List<PaginatedProductoResponseDTO> findSugeridosByID(Long producto, Long categoria) {
        List<Producto> productos = productoRepository.findTop4ExceptByCategoria(producto, categoria);

        Collections.shuffle(productos);

        return productos.stream()
                .limit(4)
                .map(ProductoMapper::toDTO)
                .toList();
    }


    public Page<ProductoDashboardResponseDTO> findAllPaginatedDashboard(Pageable pageable) {
        return productoRepository.findAll(pageable)
                .map(ProductoMapper::toDashboardDTO);
    }


    public Long countAllProductos() {
        return productoRepository.countAllProductos();
    }


    public Page<ProductoDashboardResponseDTO> searchByNombreOrCategoria(String busqueda, Pageable pageable) {
        return productoRepository.searchByNombreOrCategoria(busqueda, pageable)
                .map(ProductoMapper::toDashboardDTO);
    }


    public ProductoDashboardResponseDTO save(ProductoRequestDTO producto) {
        Imagen imagen = imagenService.findById(producto.getImagenID());
        Categoria categoria = categoriaService.findById(producto.getCategoriaID());

        Producto nuevoProducto = ProductoMapper.toEntity(producto, imagen, categoria);
        return ProductoMapper.toDashboardDTO(productoRepository.save(nuevoProducto));
    }


    public ProductoDashboardResponseDTO update(Long id, ProductoRequestDTO producto) {
        Producto productoEncontrado = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrada con id: " + id));

        Imagen imagen = imagenService.findById(producto.getImagenID());
        Categoria categoria = categoriaService.findById(producto.getCategoriaID());

        productoEncontrado.setNombre(producto.getNombre());
        productoEncontrado.setDescripcion(producto.getDescripcion());
        productoEncontrado.setImagen(imagen);
        productoEncontrado.setCategoria(categoria);
        return ProductoMapper.toDashboardDTO(productoRepository.save(productoEncontrado));
    }
}

