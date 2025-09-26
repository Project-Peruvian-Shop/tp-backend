package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.cotizacion.CategoriaMesDTO;
import com.ecommerce.backend.dto.cotizacion.*;
import com.ecommerce.backend.dto.cotizacion.ProductoCotizadoMesDTO;
import com.ecommerce.backend.dto.cotizacion.UsuarioCotizacionMesDTO;
import com.ecommerce.backend.entity.*;
import com.ecommerce.backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CotizacionService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final CotizacionRepository cotizacionRepository;
    private final CotizacionDetalleRepository detalleRepository;
    private final CotizacionPdfRepository pdfRepository;

    @Transactional
    public CotizacionResponseDTO save_cotizacion(CotizacionRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioID())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setNumero(UUID.randomUUID().toString().substring(0,8));
        cotizacion.setEstado(0);
        cotizacion.setCreacion(LocalDateTime.now());
        cotizacion.setComentario(request.getComentario());
        cotizacion.setNombre(request.getNombre());
        cotizacion.setTipoDocumento(request.getTipoDocumento());
        cotizacion.setDocumento(request.getDocumento());
        cotizacion.setTelefono(request.getTelefono());
        cotizacion.setEmail(request.getEmail());
        cotizacion.setObservaciones(request.getObservaciones());
        cotizacion.setUsuario(usuario);

        Cotizacion cotizacionGuardada = cotizacionRepository.save(cotizacion);

        for (CotizacionProductoDTO p : request.getProductos()) {
            Producto producto = productoRepository.findById(p.getProductoID())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            CotizacionDetalle detalle = new CotizacionDetalle();
            detalle.setId(new CotizacionDetalleId(producto.getId(), cotizacionGuardada.getId()));
            detalle.setCantidad(p.getCantidad());
            detalle.setProducto(producto);
            detalle.setCotizacion(cotizacionGuardada);

            detalleRepository.save(detalle);
        }

        return new CotizacionResponseDTO(
                cotizacionGuardada.getId(),
                cotizacionGuardada.getTipoDocumento(),
                cotizacionGuardada.getDocumento(),
                cotizacionGuardada.getEmail(),
                cotizacionGuardada.getTelefono()
        );
    }

    public CotizacionPdfDTO save_pdf(Long cotizacionId, String archivo) {
        Cotizacion cotizacion = cotizacionRepository.findById(cotizacionId)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        CotizacionPDF pdf = new CotizacionPDF();
        pdf.setArchivo(archivo);
        pdf.setCreacion(LocalDateTime.now());
        pdf.setCotizacion(cotizacion);

        CotizacionPDF guardado = pdfRepository.save(pdf);

        return new CotizacionPdfDTO(
                guardado.getId(),
                guardado.getArchivo(),
                guardado.getCreacion(),
                guardado.getCotizacion().getId()
        );
    }
    public List<ProductoCotizadoMesDTO> get_productos_cotizados_mes(Integer mes, Integer year) {
        LocalDate now = LocalDate.now();
        int m = (year != null) ? mes : now.getMonthValue();
        int y = (year != null) ? year : now.getYear();
        return detalleRepository.productos_cotizados_mes(m, y);
    }

    public UsuarioCotizacionMesDTO get_usuarios_mes(Integer mes, Integer year){
        LocalDate now = LocalDate.now();
        int m = (mes != null) ? mes : now.getMonthValue();
        int y = (year != null) ? year : now.getYear();
        Long nuevos = cotizacionRepository.count_usuarios_nuevos_mes(m, y);
        Long cotizadores = cotizacionRepository.count_usuarios_cotizadores_mes(m, y);
        return new UsuarioCotizacionMesDTO(nuevos, cotizadores);
    }

    public List<CategoriaMesDTO> get_lineas_cotizadas_mes(Integer mes, Integer year){
        LocalDate now = LocalDate.now();
        int m = (mes != null) ? mes : now.getMonthValue();
        int y = (year != null) ? year : now.getYear();
        return detalleRepository.lineasCotizadasMes(m, y);
    }

    public List<CotizacionYearDTO> get_cotizacion_year(Integer year){
        int y = (year != null) ? year : LocalDate.now().getYear();

        return cotizacionRepository.cotizacionesForYear(y).stream()
                .map(row -> {
                    Integer mesNumero = ((Number) row[0]).intValue();
                    Long cantidad = ((Number) row[1]).longValue();
                    String mesNombre = Month.of(mesNumero)
                            .getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
                    return new CotizacionYearDTO(mesNombre, cantidad);
                })
                .toList();
    }
    public Page<CotizacionDashboardDTO> get_cotizaciones_dashboard(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return cotizacionRepository.findAllCotizacionesDashboard(pageable);
    }
}
