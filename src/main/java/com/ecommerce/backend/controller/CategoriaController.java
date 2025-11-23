package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.categoria.AllAndQuantityResponseDTO;
import com.ecommerce.backend.dto.categoria.CategoriaDashboardResponseDTO;
import com.ecommerce.backend.dto.categoria.CategoriaRequestDTO;
import com.ecommerce.backend.dto.producto.PaginatedProductoResponseDTO;
import com.ecommerce.backend.service.CategoriaService;
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
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_CATEGORIA)
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping("/all-and-quantity")
    @Operation(
            summary = "Traer categorias/lineas nombre y cantidad",
            description = "Ubicación: Side bar en tienda  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse<List<AllAndQuantityResponseDTO>>> findAllAndQuantity() {
        List<AllAndQuantityResponseDTO> data = categoriaService.findAllAndQuantity();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Categorias obtenidas exitosamente"));
    }


    @GetMapping("/dashboard-quantity")
    @Operation(
            summary = "Traer cantidad de categorias/lineas",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Long>> countAllCategorias() {
        Long data = categoriaService.countAllCategorias();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cantidad de categorias para dashboard"));
    }


    @GetMapping("/dashboard-paginated")
    @Operation(
            summary = "Traer categorias paginados",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Page<CategoriaDashboardResponseDTO>>> getAllPaginatedDashboard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoriaDashboardResponseDTO> data = categoriaService.findAllPaginatedDashboard(pageable);

        return ResponseEntity.status(HttpStatus.OK).
                body(GlobalResponse.success(data, "Categorias paginadas para dashboard"));
    }


    @GetMapping("/dashboard-search")
    @Operation(
            summary = "Traer categorias por busqueda",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Page<CategoriaDashboardResponseDTO>>> searchByNombreUsosNorma(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoriaDashboardResponseDTO> data = categoriaService.searchByNombreUsosNorma(busqueda, pageable);

        return ResponseEntity.status(HttpStatus.OK).
                body(GlobalResponse.success(data, "Categorias buscadas para dashboard"));
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Traer al categoria por id",
            description = "Ubicación: Dashboard categoria one  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<CategoriaDashboardResponseDTO>> getByID(@PathVariable Long id) {
        CategoriaDashboardResponseDTO data = categoriaService.findByID(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Categoria encontrada con id: " + id));
    }


    @GetMapping("/productos/{id}")
    @Operation(
            summary = "Traer los productos por categoria",
            description = "Ubicación: Dashboard categoria one & landing  \n" +
                    "Seguridad: Admin, Manager, public"
    )
    public ResponseEntity<GlobalResponse<Page<PaginatedProductoResponseDTO>>> getProductosByID(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PaginatedProductoResponseDTO> data = categoriaService.findProductosByCategoriaID(id, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Productos encontrados para categoria con id: " + id));
    }


    @PostMapping("/")
    @Operation(
            summary = "Crear una nueva categoria",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<CategoriaDashboardResponseDTO>> save(@Valid @RequestBody CategoriaRequestDTO categoria) {
        CategoriaDashboardResponseDTO data = categoriaService.save(categoria);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GlobalResponse.success(data, "Categoria creada exitosamente"));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una categoria",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<CategoriaDashboardResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO categoria
    ) {
        CategoriaDashboardResponseDTO data = categoriaService.update(id, categoria);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Categoria actualizada exitosamente"));
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar Lineas",
            description = "Ubicacion: Dashboard Lineas   \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Object>> deleteCategoria(@PathVariable Long id){
        categoriaService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(null, "Categoria deleted successfully - id: " + id));
    }
}
