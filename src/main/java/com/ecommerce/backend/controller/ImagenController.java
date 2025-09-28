package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.imagen.ImagenRequestDTO;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.service.ImagenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_IMAGEN)
@RequiredArgsConstructor
public class ImagenController {
    private final ImagenService imagenService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Traer imagen por id",
            description = "Seguridad: publico\n" +
                    "Ubicacion: llamadas a la API"
    )
    public ResponseEntity<GlobalResponse> findById(@PathVariable Long id) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            status = HttpStatus.OK;
            data = imagenService.findById(id);
            message = "Imagen found successfully";
        } catch (ResourceNotFoundException e) {
            status = HttpStatus.NOT_FOUND;
            message = "Imagen not found";
            details = e.getMessage();
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while retrieving the imagen";
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

    @PostMapping
    @Operation(
            summary = "Crear imagen",
            description = "Seguridad: publico\n" +
                    "Ubicacion: llamadas a la API"
    )
    public ResponseEntity<GlobalResponse> create(@Valid @RequestBody ImagenRequestDTO imagen) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            status = HttpStatus.CREATED;
            data = imagenService.create(imagen);
            message = "Imagen created successfully";
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while creating the imagen";
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
