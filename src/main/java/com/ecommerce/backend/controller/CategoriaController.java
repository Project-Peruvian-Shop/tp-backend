package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.categoria.CategoriaRequestDTO;
import com.ecommerce.backend.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<GlobalResponse> findAllAndQuantity() {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            data = categoriaService.findAllAndQuantity();
            message = "Categorias retrieved successfully";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while retrieving categorias";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }


    @GetMapping("/dashboard-quantity")
    @Operation(
            summary = "Traer cantidad de categorias/lineas",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> countAllCategorias() {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            data = categoriaService.countAllCategorias();
            message = "Cantidad de categorias para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al traer cantidad de categorias";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }


    @GetMapping("/dashboard-paginated")
    @Operation(
            summary = "Traer categorias paginados",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> getAllPaginatedDashboard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            Pageable pageable = PageRequest.of(page, size);
            data = categoriaService.findAllPaginatedDashboard(pageable);
            message = "Paginated Categorias para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while retrieving paginated categorias";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }


    @GetMapping("/dashboard-search")
    @Operation(
            summary = "Traer categorias por busqueda",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> searchByNombreUsosNorma(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            Pageable pageable = PageRequest.of(page, size);
            data = categoriaService.searchByNombreUsosNorma(busqueda, pageable);
            message = "Busqueda de Categorias para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while retrieving search categorias";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Traer al categoria por id",
            description = "Ubicación: Dashboard categoria one  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> getByID(@PathVariable Long id) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            data = categoriaService.findByID(id);
            message = "Categoria con id " + id;
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al traer el Categoria";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }


    @GetMapping("/productos/{id}")
    @Operation(
            summary = "Traer los productos por categoria",
            description = "Ubicación: Dashboard categoria one & landing  \n" +
                    "Seguridad: Admin, Manager, public"
    )
    public ResponseEntity<GlobalResponse> getProductosByID(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            Pageable pageable = PageRequest.of(page, size);
            data = categoriaService.findProductosByCategoriaID(id, pageable);
            message = "Productos de la categoria " + id;
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al traer los productos de la categoria";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }


    @PostMapping("/")
    @Operation(
            summary = "Crear una nueva categoria",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> save(@Valid @RequestBody CategoriaRequestDTO categoria) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            data = categoriaService.save(categoria);
            message = "Categoria creada";
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al crear categoria";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una categoria",
            description = "Ubicación: Dashboard - Categorias  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO categoria
    ) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            data = categoriaService.update(id, categoria);
            message = "Categoria actualizada";
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al actualizar categoria";
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(data != null)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }
}
