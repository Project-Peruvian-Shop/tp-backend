package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.cotizacion.*;
import com.ecommerce.backend.service.CotizacionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public ResponseEntity<GlobalResponse<CotizacionResponseDTO>> createCotizacion(
            @Valid @RequestBody CotizacionRequestDTO cotizacionRequestDTO
    ) {
        CotizacionResponseDTO data = cotizacionService.save_cotizacion(cotizacionRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GlobalResponse.success(data, "Cotizacion creada exitosamente"));
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
    public ResponseEntity<GlobalResponse<List<ProductoCotizadoMesDTO>>> productos_cotizados_mes(
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer year
    ) {
        List<ProductoCotizadoMesDTO> data = cotizacionService.get_productos_cotizados_mes(mes, year);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Productos cotizados del mes obtenidos exitosamente"));
    }


    @GetMapping("/lineas_mes")
    @Operation(
            summary = "Traer lineas cotizadas del mes",
            description = "Ubicación: cotizaciones del dashboard  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<List<CategoriaMesDTO>>> get_lineas_mes(
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer year
    ) {
        List<CategoriaMesDTO> data = cotizacionService.get_lineas_cotizadas_mes(mes, year);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Lineas cotizadas del mes obtenidas exitosamente"));
    }


    @GetMapping("/cotizaciones_year")
    @Operation(
            summary = "Traer cotizaciones del año",
            description = "Ubicación: cotizaciones del dashboard  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<List<CotizacionYearDTO>>> get_cotizaciones_year(
            @RequestParam(required = false) Integer year
    ) {
        List<CotizacionYearDTO> data = cotizacionService.get_cotizacion_year(year);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cotizaciones del año obtenidas exitosamente"));
    }


    @GetMapping("/last-cotizaciones")
    @Operation(
            summary = "Traer las últimas cotizaciones",
            description = "Ubicación: cotizaciones del dashboard  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<List<CotizacionResumenDTO>>> get_last_cotizaciones() {
        List<CotizacionResumenDTO> data = cotizacionService.get_last_cotizaciones();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Últimas cotizaciones obtenidas exitosamente"));
    }


    @GetMapping("/mensajes-mes")
    @Operation(
            summary = "Traer mensajes pendientes",
            description = "Ubicación: cotizaciones del dashboard  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<List<MensajeDashboardDTO>>> get_mensajes_pendientes_mes() {
        List<MensajeDashboardDTO> data = cotizacionService.get_mensajes_pendientes_mes();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Mensajes pendientes del mes obtenidos exitosamente"));
    }


    @GetMapping("/dashboard-paginated")
    @Operation(
            summary = "Traer cotizaciones del dashboard paginadas",
            description = "Ubicación: cotizaciones del dashboard  \n" +
                    "Seguridad: Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<Page<CotizacionDashboardDTO>>> get_cotizaciones_dashboard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CotizacionDashboardDTO> data = cotizacionService.get_cotizaciones_dashboard(pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cotizaciones del dashboard obtenidas exitosamente"));
    }


    @GetMapping("/dashboard-quantity")
    @Operation(
            summary = "Traer cantidad de cotizaciones",
            description = "Ubicación: Dashboard - cotizaciones  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Long>> countAllCotizaciones() {
        Long data = cotizacionService.countAllCotizaciones();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cantidad de cotizaciones obtenidas exitosamente"));
    }


    @GetMapping("/dashboard-search")
    @Operation(
            summary = "Traer productos por busqueda",
            description = "Ubicación: Dashboard - Productos  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse<Page<CotizacionDashboardDTO>>> searchByNombreOrCategoria(
            @RequestParam(defaultValue = "") String busqueda,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CotizacionDashboardDTO> data = cotizacionService.searchByParams(busqueda, pageable);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Búsqueda de cotizaciones realizada exitosamente"));
    }


    @GetMapping("/by-usuario/{id}")
    @Operation(
            summary = "Traer cotizaciones del usuario",
            description = "Ubicación: cotizaciones del mismo usuario  \n" +
                    "Seguridad: Usuario, Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<List<CotizacionByUsuarioResponseDTO>>> getCotizacionesByUsuario(@PathVariable Long id) {
        List<CotizacionByUsuarioResponseDTO> data = cotizacionService.getByUser(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cotizaciones del usuario obtenidas exitosamente"));
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Traer cotizaciones por ID",
            description = "Ubicación: cotizacion particular & Dashboard cotizacion one \n" +
                    "Seguridad: Usuario, Manager, Admin"
    )
    public ResponseEntity<GlobalResponse<CotizacionFullResponseDTO>> getCotizacionesById(@PathVariable Long id) {
        CotizacionFullResponseDTO data = cotizacionService.getByID(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cotización obtenida exitosamente"));
    }
}
