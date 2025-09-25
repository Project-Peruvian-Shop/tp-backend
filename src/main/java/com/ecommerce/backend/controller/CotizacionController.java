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
    public ResponseEntity<GlobalResponse> createCotizacion(@RequestBody CotizacionRequestDTO cotizacionRequestDTO){

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
    public ResponseEntity<GlobalResponse> createPdf(@PathVariable Long id, @RequestParam String archivo){
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
}
