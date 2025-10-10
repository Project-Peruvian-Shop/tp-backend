package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.producto.PaginatedProductoResponseDTO;
import com.ecommerce.backend.dto.producto.ProductoDashboardResponseDTO;
import com.ecommerce.backend.dto.producto.ProductoFullResponseDTO;
import com.ecommerce.backend.dto.producto.ProductoRequestDTO;
import com.ecommerce.backend.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_PRODUCTOS)
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping("/paginated")
    @Operation(
            summary = "Traer productos paginados",
            description = "Ubicación: Tienda  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse<Page<PaginatedProductoResponseDTO>>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long categoria
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PaginatedProductoResponseDTO> data;

        if (categoria != null) {
            data = productoService.findAllByCategoriaPaginated(categoria, pageable);
        } else {
            data = productoService.findAllPaginated(pageable);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Productos paginados"));
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Traer al producto por id",
            description = "Ubicación: Producto en Tienda, Dashboard producto one  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse<ProductoFullResponseDTO>> getByID(@PathVariable Long id) {
        ProductoFullResponseDTO data = productoService.findByID(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Producto con id: " + id));
    }


    @GetMapping("/sugeridos")
    @Operation(
            summary = "Traer productos sugeridos por id",
            description = "Ubicación: Producto en Tienda  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse<List<PaginatedProductoResponseDTO>>> getProductosByID(
            @RequestParam(defaultValue = "1") Long producto,
            @RequestParam(defaultValue = "1") Long categoria
    ) {
        List<PaginatedProductoResponseDTO> data = productoService.findSugeridosByID(producto, categoria);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Productos sugeridos"));
    }


    @GetMapping("/dashboard-paginated")
    @Operation(
            summary = "Traer productos paginados",
            description = "Ubicación: Dashboard - Productos  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Page<ProductoDashboardResponseDTO>>> getAllPaginatedDashboard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductoDashboardResponseDTO> data = productoService.findAllPaginatedDashboard(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Productos paginados para dashboard"));
    }


    @GetMapping("/dashboard-quantity")
    @Operation(
            summary = "Traer cantidad de productos",
            description = "Ubicación: Dashboard - Productos  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Long>> countAllProductos() {
        Long data = productoService.countAllProductos();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cantidad de productos en el sistema"));
    }


    @GetMapping("/dashboard-search")
    @Operation(
            summary = "Traer productos por busqueda",
            description = "Ubicación: Dashboard - Productos  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Page<ProductoDashboardResponseDTO>>> searchByNombreOrCategoria(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductoDashboardResponseDTO> data = productoService.searchByNombreOrCategoria(busqueda, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Productos encontrados para: " + busqueda));
    }


    @PostMapping("/")
    @Operation(
            summary = "Crear productos",
            description = "Ubicacion: Dashboard productos create  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<ProductoDashboardResponseDTO>> save(@Valid @RequestBody ProductoRequestDTO producto) {
        ProductoDashboardResponseDTO data = productoService.save(producto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GlobalResponse.success(data, "Producto creado"));
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar productos",
            description = "Ubicacion: Dashboard productos update  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<ProductoDashboardResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO producto
    ) {
        ProductoDashboardResponseDTO data = productoService.update(id, producto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Producto actualizado con id: " + id));
    }
}
