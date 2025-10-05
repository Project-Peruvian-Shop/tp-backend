package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.mensaje.ChangeStateMensajeRequestDTO;
import com.ecommerce.backend.dto.mensaje.MensajeRequestDTO;
import com.ecommerce.backend.dto.mensaje.MensajeResponseDTO;
import com.ecommerce.backend.dto.mensaje.ReclamacionesRequestDTO;
import com.ecommerce.backend.service.MensajeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<GlobalResponse<MensajeResponseDTO>> send_contactenos(@Valid @RequestBody MensajeRequestDTO mensajeRequestDTO) {
        MensajeResponseDTO data = mensajeService.send_contactenos(mensajeRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GlobalResponse.success(data, "Mensaje enviado correctamente"));
    }


    @PostMapping("/reclamaciones")
    @Operation(
            summary = "Crear mensaje de libro de reclamaciones",
            description = "Ubicación: Libro de reclamaciones  \n" +
                    "Seguridad: Pública"
    )
    public ResponseEntity<GlobalResponse<MensajeResponseDTO>> send_reclamos(@Valid @RequestBody MensajeRequestDTO mensajeRequestDTO) {
        MensajeResponseDTO data = mensajeService.send_reclamos(mensajeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GlobalResponse.success(data, "Reclamo enviado correctamente"));
    }


    @GetMapping("/dashboard-quantity/{mes}")
    @Operation(
            summary = "Traer cantidad de mensajes por mes",
            description = "Ubicación: Dashboard - Mensajes  \n" +
                    "Seguridad: Admin, Manager"
    )
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
    @Operation(
            summary = "Obtener mensajes por ID",
            description = "Ubicación: Dashboard  \n" +
                    "Seguridad: Admin, Manager"
    )
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


    @GetMapping("/dashboard-paginated")
    @Operation(
            summary = "Obtener mensajes paginados para el dashboard",
            description = "Ubicación: Dashboard  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> findDashboardPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpStatus status;
        Object data;
        String message;
        String details = null;

        try {
            Pageable pageable = PageRequest.of(page, size);
            data = mensajeService.findDashboardPaginated(pageable);
            status = HttpStatus.OK;
            message = "Messages retrieved successfully";
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            data = null;
            message = "Error retrieving messages";
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


//    public ResponseEntity<GlobalResponse> countAllMensajes() {
//        HttpStatus status;
//        Object data = null;
//        String message;
//        String details = null;
//
//        try {
//            data = mensajeService.countAllMensajes();
//            message = "Cantidad de mensajes para dashboard";
//            status = HttpStatus.OK;
//        } catch (Exception e) {
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//            message = "Error al traer cantidad de mensajes";
//            details = e.getMessage();
//        }
//
//        return ResponseEntity.status(status).body(
//                GlobalResponse.builder()
//                        .ok(data != null)
//                        .message(message)
//                        .data(data)
//                        .details(details)
//                        .build()
//        );
//    }


    @GetMapping("/dashboard-search")
    @Operation(
            summary = "Traer productos por mensajes",
            description = "Ubicación: Dashboard - mensajes  \n" +
                    "Seguridad: Admin, Manager"
    )
    public ResponseEntity<GlobalResponse> searchByParams(
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
            data = mensajeService.searchByParams(busqueda, pageable);
            message = "Busqueda de mensajes para dashboard";
            status = HttpStatus.OK;
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "An error occurred while retrieving search mensajes";
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
