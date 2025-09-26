package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.cotizacion.CotizacionRequestDTO;
import com.ecommerce.backend.service.CotizacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_COTIZACION)
@RequiredArgsConstructor
public class CotizacionController {

    private final CotizacionService cotizacionService;

    @PostMapping("/create")
    public ResponseEntity<GlobalResponse> create_Cotizacion(@RequestBody CotizacionRequestDTO cotizacionRequestDTO){

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
    @PostMapping("/{id}/pdf")
    public ResponseEntity<GlobalResponse> create_Pdf(@PathVariable Long id, @RequestParam String archivo){
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = cotizacionService.save_pdf(id, archivo);
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
    public ResponseEntity<GlobalResponse> productos_cotizados_mes( @RequestParam(required = false) Integer mes, @RequestParam(required = false) Integer year){
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
    public ResponseEntity<GlobalResponse> get_usuarios_mes(@RequestParam(required = false) Integer mes, @RequestParam(required = false) Integer year){
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = cotizacionService.get_usuarios_mes(mes,year);
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
    public ResponseEntity<GlobalResponse> get_lineas_mes(@RequestParam(required = false) Integer mes, @RequestParam(required = false) Integer year){
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = cotizacionService.get_lineas_cotizadas_mes(mes,year);
            status = HttpStatus.OK;
            message = "Lineas del mes retrieved successfully";
        }catch (Exception e){
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
    public ResponseEntity<GlobalResponse> get_cotizaciones_year(@RequestParam(required = false) Integer year){
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = cotizacionService.get_cotizacion_year(year);
            status = HttpStatus.OK;
            message = "Cotizaciones del año retrieved successfully";
        }catch (Exception e){
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
    @GetMapping("/paginated")
    public ResponseEntity<GlobalResponse> get_cotizaciones_dashboard(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {

            data = cotizacionService.get_cotizaciones_dashboard(page, size);
            status = HttpStatus.OK;
            message = "Cotizaciones del dashboard retrieved successfully";
        }catch (Exception e){
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

}
