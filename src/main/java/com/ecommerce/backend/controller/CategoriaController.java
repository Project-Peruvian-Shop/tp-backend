package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_CATEGORIA)
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;


    //    Funcion: Traer las categorias y cantidad de productos
    //    Seguridad: Publica
    //    Ubicacion: Tienda, barra lateral
    //    Metodo: GET
    //    Request: none
    //    Response: {categoriaID, categoriaNombre, categoriaCantidad}
    @GetMapping("/all-and-quantity")
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
}
