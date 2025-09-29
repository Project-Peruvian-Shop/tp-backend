package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GlobalResponse> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            Pageable pageable = PageRequest.of(page, size);
            data = productoService.findAllPaginated(pageable);
            message = "Paginated Productos retrieved successfully";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while retrieving paginated productos";
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
            summary = "Traer al producto por id",
            description = "Ubicación: Producto en Tienda  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse> getByID(@PathVariable Long id) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;


        try {
            data = productoService.findByID(id);
            message = "Producto con id " + id;
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al traer el producto";
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


    @GetMapping("/sugeridos")
    @Operation(
            summary = "Traer productos sugeridos por id",
            description = "Ubicación: Producto en Tienda  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse> getProductosByID(
            @RequestParam(defaultValue = "1") Long producto,
            @RequestParam(defaultValue = "1") Long categoria
    ) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            data = productoService.findSugeridosByID(producto, categoria);
            message = "Producto sugeridos " + producto;
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al traer productos sugeridos";
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
            summary = "Traer productos paginados",
            description = "Ubicación: Dashboard - Productos  \n" +
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
            data = productoService.findAllPaginatedDashboard(pageable);
            message = "Paginated Productos para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while retrieving paginated productos";
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
            summary = "Traer cantidad de productos",
            description = "Ubicación: Dashboard - Productos  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> countAllProductos() {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            data = productoService.countAllProductos();
            message = "Paginated Productos para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while retrieving paginated productos";
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
            summary = "Traer productos por busqueda",
            description = "Ubicación: Dashboard - Productos  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> searchByNombreOrCategoria(
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
            data = productoService.searchByNombreOrCategoria(busqueda, pageable);
            message = "Busqueda de Productos para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while retrieving search productos";
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
