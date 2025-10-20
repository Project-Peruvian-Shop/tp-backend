package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.dashboard.KPISResponseDTO;
import com.ecommerce.backend.enums.Periodo;
import com.ecommerce.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CotizacionRepository cotizacionRepository;
    private final MensajeRepository mensajeRepository;


    public KPISResponseDTO getKPIS(Periodo periodo) {
        LocalDateTime fechaInicio;
        LocalDateTime fechaFin = LocalDateTime.now();

        switch (periodo) {
            case DAY -> fechaInicio = LocalDate.now().atStartOfDay();
            case WEEK -> fechaInicio = LocalDate.now().with(java.time.DayOfWeek.MONDAY).atStartOfDay();
            case MONTH -> fechaInicio = LocalDate.now().withDayOfMonth(1).atStartOfDay();
            default -> throw new IllegalArgumentException("Periodo no soportado: " + periodo);
        }

        Long cotizacionesPendientes = cotizacionRepository.countCotizacionesPendientesPorPeriodo(fechaInicio, fechaFin);
        Long cotizacionesAceptadas = cotizacionRepository.countCotizacionesAceptadasPorPeriodo(fechaInicio, fechaFin);
        Long mensajesPendientes = mensajeRepository.countMensajesPendientesPorPeriodo(fechaInicio, fechaFin);

        return KPISResponseDTO.builder()
                .cotizacionesPendientes(cotizacionesPendientes)
                .cotizacionesAceptadas(cotizacionesAceptadas)
                .mensajesPendientes(mensajesPendientes)
                .build();
    }
}
