package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.cotizacion.CotizacionRequestDTO;
import com.ecommerce.backend.service.CotizacionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_COTIZACION)
@RequiredArgsConstructor
public class CotizacionController {

    private final CotizacionService cotizacionService;

    @PostMapping("/create")
    @Operation(
            summary = "Crear una cotizacion",
            description = "Ubicación: Solicitud de Cotizacion  \n" +
                    "Seguridad: Usuario, Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> createCotizacion(@Valid @RequestBody CotizacionRequestDTO cotizacionRequestDTO) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = cotizacionService.save_cotizacion(cotizacionRequestDTO);
            status = HttpStatus.CREATED;
            message = "Cotizacion created successfully";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error creating cotizacion";
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


    @PostMapping("/create_pdf/{id}")
    public ResponseEntity<GlobalResponse> createPdf(@PathVariable Long id, @RequestParam("archivo") MultipartFile archivo) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = cotizacionService.savePdf(id, archivo);
            status = HttpStatus.CREATED;
            message = "PDF created successfully";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error creating PDF";
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

    @GetMapping("/productos_mes")
    @Operation(
            summary = "Traer productos cotizados del mes",
            description = "Ubicación: cotizaciones del dashboard  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> productos_cotizados_mes(
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer year
    ) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = cotizacionService.get_productos_cotizados_mes(mes, year);
            status = HttpStatus.OK;
            message = "Productos cotizados del mes retrieved successfully";
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving productos cotizados del mes";
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


    @GetMapping("/usuarios_mes")
    public ResponseEntity<GlobalResponse> get_usuarios_mes(@RequestParam(required = false) Integer mes, @RequestParam(required = false) Integer year) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = cotizacionService.get_usuarios_mes(mes, year);
            status = HttpStatus.OK;
            message = "Usuarios del mes retrieved successfully";
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving usuarios del mes";
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

    @GetMapping("/lineas_mes")
    @Operation(
            summary = "Traer lineas cotizadas del mes",
            description = "Ubicación: cotizaciones del dashboard  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> get_lineas_mes(@RequestParam(required = false) Integer mes, @RequestParam(required = false) Integer year) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = cotizacionService.get_lineas_cotizadas_mes(mes, year);
            status = HttpStatus.OK;
            message = "Lineas del mes retrieved successfully";
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving lineas del mes";
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


    @GetMapping("cotizaciones_year")
    @Operation(
            summary = "Traer cotizaciones del año",
            description = "Ubicación: cotizaciones del dashboard  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> get_cotizaciones_year(@RequestParam(required = false) Integer year) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = cotizacionService.get_cotizacion_year(year);
            status = HttpStatus.OK;
            message = "Cotizaciones del año retrieved successfully";
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving cotizaciones del año";
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
            summary = "Traer cotizaciones del dashboard paginadas",
            description = "Ubicación: cotizaciones del dashboard  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> get_cotizaciones_dashboard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Pageable pageable = PageRequest.of(page, size);
            data = cotizacionService.get_cotizaciones_dashboard(pageable);
            status = HttpStatus.OK;
            message = "Cotizaciones del dashboard retrieved successfully";
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving cotizaciones del dashboard";
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
            summary = "Traer cantidad de cotizaciones",
            description = "Ubicación: Dashboard - cotizaciones  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> countAllCotizaciones() {
        HttpStatus status;
        Object data = null;
        String message;
        String details = null;

        try {
            data = cotizacionService.countAllCotizaciones();
            message = "Cantidad de cotizaciones para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "Error al traer cantidad de cotizaciones";
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
            data = cotizacionService.searchByParams(busqueda, pageable);
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


    @GetMapping("/by-usuario/{id}")
    @Operation(
            summary = "Traer cotizaciones del usuario",
            description = "Ubicación: cotizaciones del mismo usuario  \n" +
                    "Seguridad: Usuario, Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> getCotizacionesByUsuario(@PathVariable Long id) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = cotizacionService.getByUser(id);
            status = HttpStatus.OK;
            message = "Cotizaciones del usuario";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error al traer cotizaciones del usuario";
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
            summary = "Traer cotizaciones por ID",
            description = "Ubicación: cotizacion particular & Dashboard cotizacion one \n" +
                    "Seguridad: Usuario, Manager, Admin"
    )
    public ResponseEntity<GlobalResponse> getCotizacionesById(@PathVariable Long id) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = cotizacionService.getByID(id);
            status = HttpStatus.OK;
            message = "Cotizaciones por ID";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error al traer cotizaciones por ID";
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
