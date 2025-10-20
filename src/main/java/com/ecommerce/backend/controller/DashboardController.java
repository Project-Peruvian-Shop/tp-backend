package com.ecommerce.backend.controller;

import com.ecommerce.backend.config.Constant;
import com.ecommerce.backend.dto.GlobalResponse;
import com.ecommerce.backend.dto.dashboard.CotizacionesPorMesDTO;
import com.ecommerce.backend.dto.dashboard.KPISResponseDTO;
import com.ecommerce.backend.enums.Periodo;
import com.ecommerce.backend.service.CotizacionService;
import com.ecommerce.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(Constant.API_VERSION + "/" + Constant.DASHBOARD)
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/resumen-kpis")
    public ResponseEntity<GlobalResponse<KPISResponseDTO>> getKPIS(
            @RequestParam(defaultValue = "MONTH") Periodo periodo
    ) {
        KPISResponseDTO data = dashboardService.getKPIS(periodo);

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cotizaciones del año obtenidas exitosamente"));
    }

    @GetMapping("/cotizaciones-por-mes")
    public ResponseEntity<GlobalResponse<List<CotizacionesPorMesDTO>>> getCotizacionesPorMes() {
        List<CotizacionesPorMesDTO> data = dashboardService.obtenerCotizacionesAceptadasPorRango();

        return ResponseEntity.status(HttpStatus.OK)
                .body(GlobalResponse.success(data, "Cotizaciones por mes obtenidas exitosamente"));
    }
}
