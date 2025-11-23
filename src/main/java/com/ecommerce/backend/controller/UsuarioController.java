package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.usuario.*;
import com.ecommerce.backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_USUARIOS)
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    @Operation(
            summary = "Traer datos de un usuario",
            description = "Ubicación: Mi perfil  \n" +
                    "Seguridad: Cliente, Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<UsuarioPerfilDTO>> findById(@PathVariable Long id) {
        UsuarioPerfilDTO data = usuarioService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Usuario retrieved successfully - id: " + id));
    }


    @PostMapping("/save")
    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Ubicación: Dashboard - Registro  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<UsuarioResponseDTO>> saveUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        System.out.println("usuarioRequestDTO = " + usuarioRequestDTO);
        usuarioRequestDTO.setPasswordd(passwordEncoder.encode(usuarioRequestDTO.getPasswordd()));
        UsuarioResponseDTO data = usuarioService.save(usuarioRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GlobalResponse.success(data, "Usuario created successfully - id: " + data.getId()));
    }


    @PutMapping("/update/{id}")
    @Operation(
            summary = "Actualizar un usuario",
            description = "Ubicación: Mi perfil, Dashboard - Usuarios  \n" +
                    "Seguridad: Cliente, Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<UsuarioResponseDTO>> updateUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioUpdateRequestDTO usuarioUpdateRequestDTO
    ) {
        UsuarioResponseDTO data = usuarioService.update(id, usuarioUpdateRequestDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Usuario updated successfully - id: " + id));
    }


    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar un usuario",
            description = "Ubicación: Mi perfil, Dashboard - Usuarios  \n" +
                    "Seguridad:  Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<Object>> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(null, "Usuario deleted successfully - id: " + id));
    }


    @GetMapping("/dashboard-paginated")
    @Operation(
            summary = "Traer todos los usuarios paginados",
            description = "Ubicación: Dashboard - Usuarios  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<Page<UsuarioResponseDTO>>> getAllUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UsuarioResponseDTO> data = usuarioService.findAll(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Usuarios retrieved successfully"));
    }


    @GetMapping("/dashboard-search")
    @Operation(
            summary = "Buscar usuarios por nombre, apellidos, email, teléfono o rol",
            description = "Ubicación: Dashboard - Usuarios  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<Page<UsuarioResponseDTO>>> findUsers(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UsuarioResponseDTO> data = usuarioService.searchByParam(busqueda, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Search results for: " + busqueda));
    }


    @GetMapping("/dashboard-quantity")
    @Operation(
            summary = "Traer cantidad de usuarios",
            description = "Ubicación: Dashboard - Usuarios  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Long>> countAllProductos() {
        Long data = usuarioService.countAllUsuarios();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cantidad de usuarios"));
    }


    @GetMapping("/{id}/workers")
    @Operation(
            summary = "Traer todos los usuarios con rol de trabajador excluyendo el mismo usuario",
            description = "Ubicación: Mi perfil  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<Page<UsuarioSimpleResponseDTO>>> getAllWorkers(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UsuarioSimpleResponseDTO> data = usuarioService.findAllWorkers(id, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Workers retrieved successfully"));
    }
}
