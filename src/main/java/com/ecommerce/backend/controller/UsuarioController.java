package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.usuario.UsuarioRequestDTO;
import com.ecommerce.backend.role.UserRole;
import com.ecommerce.backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_USUARIOS)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<GlobalResponse> getAllUsuarios() {
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
    @Operation(
            summary = "Traer datos de un usuario",
            description = "Ubicación: Mi perfil  \n" +
                    "Seguridad: Cliente, Manager, Admin"
    )
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
            message = "Error retrieving usuario with id: " + id;
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

    @PostMapping("/save")
    public ResponseEntity<GlobalResponse> saveUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = usuarioService.save(usuarioRequestDTO);
            status = HttpStatus.CREATED;
            message = "Usuario created successfully";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error creating usuario";
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

    @PutMapping("/update/{id}")
    public ResponseEntity<GlobalResponse> updateUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = usuarioService.update(id, usuarioRequestDTO);
            status = HttpStatus.OK;
            message = "Usuario updated successfully - id: " + id;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error updating usuario with id: " + id;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GlobalResponse> deleteUsuario(@PathVariable Long id) {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            usuarioService.deleteById(id);
            status = HttpStatus.OK;
            message = "Usuario deleted successfully - id: " + id;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            message = "Error deleting usuario with id: " + id;
            details = e.getMessage();
        }

        return ResponseEntity.status(status).body(
                GlobalResponse.builder()
                        .ok(status == HttpStatus.OK)
                        .message(message)
                        .data(data)
                        .details(details)
                        .build()
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<GlobalResponse> findUsers(
            @RequestParam(required = false) String nombre, @RequestParam(required = false) String apellidos,
            @RequestParam(required = false) String email, @RequestParam(required = false) String rol
    ) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            if (email != null) {
                data = usuarioService.findByEmail(email);
                message = "Usuario encontrado por email: " + email;
            } else if (nombre != null && apellidos != null) {
                data = usuarioService.findByNombreCompleto(nombre, apellidos);
                message = "Usuario encontrado por nombre completo: " + nombre + " " + apellidos;
            } else if (rol != null) {
                UserRole roleEnum = UserRole.fromString(rol);
                data = usuarioService.findByRol(roleEnum);
                message = "Usuarios encontrado por rol: " + rol;
            } else {
                throw new IllegalArgumentException("Debe proporcionar al menos un criterio de búsqueda (email, nombre+apellidos o rol).");
            }
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving usuario with nombre: " + nombre + " " + apellidos;
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
