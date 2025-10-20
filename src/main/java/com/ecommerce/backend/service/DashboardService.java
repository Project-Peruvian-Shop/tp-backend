package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.dashboard.CategoriaCotizadaDTO;
import com.ecommerce.backend.dto.dashboard.CotizacionesPorMesDTO;
import com.ecommerce.backend.dto.dashboard.KPISResponseDTO;
import com.ecommerce.backend.dto.dashboard.ProductoCotizadoDTO;
import com.ecommerce.backend.enums.Modo;
import com.ecommerce.backend.enums.Periodo;
import com.ecommerce.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CotizacionRepository cotizacionRepository;
    private final CotizacionDetalleRepository cotizacionDetalleRepository;
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

    public List<CotizacionesPorMesDTO> obtenerCotizacionesAceptadasPorRango() {
        LocalDateTime fechaInicio = LocalDateTime.now().minusYears(1);
        LocalDateTime fechaFin = LocalDateTime.now();

        List<Object[]> rows = cotizacionRepository.countCotizacionesAceptadasPorRango(fechaInicio, fechaFin);

        Map<String, Long> resultados = rows.stream()
                .collect(Collectors.toMap(
                        r -> r[0] + "-" + r[1], // key: "2024-12"
                        r -> ((Number) r[2]).longValue()
                ));

        List<CotizacionesPorMesDTO> listaFinal = new ArrayList<>();
        LocalDate actual = fechaInicio.toLocalDate().withDayOfMonth(1);

        while (!actual.isAfter(fechaFin.toLocalDate())) {
            String key = actual.getYear() + "-" + actual.getMonthValue();
            Long cantidad = resultados.getOrDefault(key, 0L);

            listaFinal.add(new CotizacionesPorMesDTO(
                    actual.getYear(),
                    actual.getMonthValue(),
                    cantidad
            ));

            actual = actual.plusMonths(1);
        }

        return listaFinal;
    }

    public List<CategoriaCotizadaDTO> obtenerCategoriasMasCotizadas(Modo modo, Integer mes, Integer year) {
        LocalDate now = LocalDate.now();
        int m = (year != null) ? mes : now.getMonthValue();
        int y = (year != null) ? year : now.getYear();

        switch (modo) {
            case APARICION:
                return cotizacionDetalleRepository.categoriasMasApariciones(m, y, PageRequest.of(0, 5))
                        .stream()
                        .map(c -> new CategoriaCotizadaDTO(
                                c.getCategoriaId(),
                                c.getNombreCategoria(),
                                c.getCantidadCotizaciones()
                        ))
                        .toList();

            case DEMANDA:
            default:
                return cotizacionDetalleRepository.categoriasMasDemandadas(m, y, PageRequest.of(0, 5))
                        .stream()
                        .map(c -> new CategoriaCotizadaDTO(
                                c.getCategoriaId(),
                                c.getNombreCategoria(),
                                c.getCantidadCotizaciones()
                        ))
                        .toList();
        }
    }

    public List<ProductoCotizadoDTO> obtenerProductosMasCotizados(Modo modo, Integer mes, Integer year) {
        LocalDate now = LocalDate.now();
        int m = (year != null) ? mes : now.getMonthValue();
        int y = (year != null) ? year : now.getYear();

        switch (modo) {
            case APARICION:
                return cotizacionDetalleRepository.productosMasApariciones(m, y, PageRequest.of(0, 5))
                        .stream()
                        .map(p -> new ProductoCotizadoDTO(
                                p.getProductoId(),
                                p.getNombreProducto(),
                                p.getCantidadApariciones()
                        ))
                        .toList();

            case DEMANDA:
            default:
                return cotizacionDetalleRepository.productosMasDemandados(m, y, PageRequest.of(0, 5))
                        .stream()
                        .map(p -> new ProductoCotizadoDTO(
                                p.getProductoId(),
                                p.getNombreProducto(),
                                p.getCantidadApariciones()
                        ))
                        .toList();
        }
    }
}
