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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
