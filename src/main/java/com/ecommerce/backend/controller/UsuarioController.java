package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.API_VERSION + "/" +Constant.TABLE_USUARIOS)
public class UsuarioController {

    private final UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<GlobalResponse> getAllUsuarios(){
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = usuarioService.findAll();
            status = HttpStatus.OK;
            message = "Usuarios retrieved successfully";
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error retrieving usuarios";
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
    public ResponseEntity<GlobalResponse> findById(@PathVariable Long id) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            status = HttpStatus.OK;
            data = usuarioService.findById(id);
            message = "Usuario retrieved - id: " + id;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving usuario with id: " +id;
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
