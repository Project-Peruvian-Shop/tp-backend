package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.dashboard.KPISResponseDTO;
import com.ecommerce.backend.enums.Periodo;
import com.ecommerce.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CotizacionRepository cotizacionRepository;
    private final MensajeRepository mensajeRepository;

    public KPISResponseDTO getKPIS(Periodo periodo) {
        Long cotizacionesPendientes = cotizacionRepository.countCotizacionesPendientesHoy();
        Long cotizacionesAceptadas = cotizacionRepository.countCotizacionesAceptadasHoy();
        Long mensajesPendientes = mensajeRepository.countMensajesPendientesHoy();

        return KPISResponseDTO.builder()
                .cotizacionesPendientes(cotizacionesPendientes)
                .cotizacionesAceptadas(cotizacionesAceptadas)
                .mensajesPendientes(mensajesPendientes)
                .build();
    }
}
