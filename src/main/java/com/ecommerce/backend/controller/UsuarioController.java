package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.usuario.UsuarioRequestDTO;
import com.ecommerce.backend.role.UserRole;
import com.ecommerce.backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_USUARIOS)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/dashboard-paginated")
    @Operation(
            summary = "Traer todos los usuarios paginados",
            description = "Ubicación: Dashboard - Usuarios  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> getAllUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Pageable pageable = PageRequest.of(page, size);
            data = usuarioService.findAll(pageable);
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


    @GetMapping("/dashboard-search")
    @Operation(
            summary = "Buscar usuarios por nombre, apellidos, email, teléfono o rol",
            description = "Ubicación: Dashboard - Usuarios  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> findUsers(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Pageable pageable = PageRequest.of(page, size);
            data = usuarioService.searchByParam(busqueda, pageable);
            message = "Busqueda de Productos para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving usuario with param";
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
            summary = "Traer cantidad de usuarios",
            description = "Ubicación: Dashboard - Usuarios  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> countAllProductos() {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            data = usuarioService.countAllUsuarios();
            message = "Cantidad de Usuarios para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al traer cantidad de usuarios";
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


    @GetMapping("/{id}/workers")
    @Operation(
            summary = "Traer todos los usuarios con rol de trabajador excluyendo el mismo usuario",
            description = "Ubicación: Mi perfil  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> getAllWorkers(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Pageable pageable = PageRequest.of(page, size);
            data = usuarioService.findAllWorkers(id, pageable);
            status = HttpStatus.OK;
            message = "Workers retrieved successfully";
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            data = null;
            message = "Error retrieving workers";
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
