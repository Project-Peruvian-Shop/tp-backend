package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.mensaje.ChangeStateMensajeRequestDTO;
import com.ecommerce.backend.dto.mensaje.MensajeRequestDTO;
import com.ecommerce.backend.dto.mensaje.ReclamacionesRequestDTO;
import com.ecommerce.backend.service.MensajeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.API_VERSION + "/" + Constant.TABLE_MENSAJES)
public class MensajeController {

    private final MensajeService mensajeService;

    @PostMapping("/contactenos")
    @Operation(
            summary = "Crear mensaje de contactenos",
            description = "Ubicación: Contactenos  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse> send_contactenos(@Valid @RequestBody MensajeRequestDTO mensajeRequestDTO) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = mensajeService.send_contactenos(mensajeRequestDTO);
            status = HttpStatus.OK;
            message = "Mensaje sent successfully";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error sending mensaje";
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

    @PostMapping("/reclamaciones")
    @Operation(
            summary = "Crear mensaje de libro de reclamaciones",
            description = "Ubicación: Libro de reclamaciones  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse> send_reclamos(@Valid @RequestBody ReclamacionesRequestDTO mensajeRequestDTO) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            data = mensajeService.send_reclamos(mensajeRequestDTO);
            status = HttpStatus.OK;
            message = "Claim sent successfully";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error sending mensaje";
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

    @GetMapping("/dashboard/{mes}")
    public ResponseEntity<GlobalResponse> get_dashboard_message(@PathVariable Long mes) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = mensajeService.get_dashboard_menssage(mes);
            status = HttpStatus.OK;
            message = "Claim sent successfully";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error sending mensaje";
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
    public ResponseEntity<GlobalResponse> get_mensaje_by_id(@PathVariable Long id) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = mensajeService.get_mensaje_by_id(id);
            status = HttpStatus.OK;
            message = "Message retrieved successfully";
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error retrieving message";
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

    @PutMapping("/change_state/{id}")
    public ResponseEntity<GlobalResponse> change_state(@PathVariable Long id, @RequestBody ChangeStateMensajeRequestDTO changeStateMensajeRequestDTO) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;
        try {
            data = mensajeService.change_state(id, changeStateMensajeRequestDTO.getNew_state());
            status = HttpStatus.OK;
            message = "Message state changed successfully";
        } catch (Exception e) {
            status = HttpStatus.NOT_FOUND;
            data = null;
            message = "Error changing message state";
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
