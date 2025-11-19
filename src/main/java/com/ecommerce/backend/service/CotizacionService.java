package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.cotizacion.*;
import com.ecommerce.backend.dto.producto.ProductoCarritoDetalleDTO;
import com.ecommerce.backend.entity.*;
import com.ecommerce.backend.enums.CotizacionEstadoEnum;
import com.ecommerce.backend.exceptions.BadRequestException;
import com.ecommerce.backend.exceptions.EntityAlreadyExistsException;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.CotizacionMapper;
import com.ecommerce.backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CotizacionService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final CotizacionRepository cotizacionRepository;
    private final CotizacionDetalleRepository detalleRepository;
    private final CotizacionPdfRepository pdfRepository;
    private final CotizacionHistorialEstadoRepository cotizacionHistorialEstadoRepository;
    private final FileUploadService fileUploadService;
    private final MensajeRepository mensajeRepository;

    @Transactional
    public CotizacionResponseDTO save_cotizacion(CotizacionRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioID())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        String numeroCotizacion = this.getNumberFromDB();
        //System.out.println(numeroCotizacion);

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setNumero(numeroCotizacion);
        cotizacion.setEstado(CotizacionEstadoEnum.PENDIENTE);
        cotizacion.setCreacion(LocalDateTime.now());
        cotizacion.setComentario(request.getComentario());
        cotizacion.setNombre(request.getNombre());
        cotizacion.setTipo_documento(request.getTipoDocumento());
        cotizacion.setDocumento(request.getDocumento());
        cotizacion.setTelefono(request.getTelefono());
        cotizacion.setEmail(request.getEmail());
        cotizacion.setUsuario(usuario);

        Cotizacion cotizacionGuardada = cotizacionRepository.save(cotizacion);

        for (CotizacionProductoDTO p : request.getProductos()) {
            Producto producto = productoRepository.findById(p.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

            CotizacionDetalle detalle = new CotizacionDetalle();
            detalle.setId(new CotizacionDetalleId(producto.getId(), cotizacionGuardada.getId()));
            detalle.setCantidad(p.getCantidad());
            detalle.setProducto(producto);
            detalle.setCotizacion(cotizacionGuardada);

            detalleRepository.save(detalle);
        }

        return new CotizacionResponseDTO(
                cotizacionGuardada.getId(),
                cotizacionGuardada.getNumero(),
                cotizacionGuardada.getTipo_documento(),
                cotizacionGuardada.getDocumento(),
                cotizacionGuardada.getEmail(),
                cotizacionGuardada.getTelefono()
        );
    }

    public CotizacionPdfDTO savePdf(Long cotizacionId, MultipartFile archivo) throws IOException {

        Cotizacion cotizacion = cotizacionRepository.findById(cotizacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada"));

        String fileName = "COT-2025-" + cotizacionId + ".pdf";

        String filePath = fileUploadService.uploadFile(archivo, fileName, "uploads/cotizaciones");

        // Generarción URL pública
        String publicUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/uploads/cotizaciones/")
                .path(fileName)
                .toUriString();

        CotizacionPDF pdf = new CotizacionPDF();
        pdf.setEnlace(publicUrl);
        pdf.setCreacion(LocalDateTime.now());
        pdf.setCotizacion(cotizacion);

        CotizacionPDF guardado = pdfRepository.save(pdf);

        return new CotizacionPdfDTO(
                guardado.getId(),
                guardado.getEnlace(),
                guardado.getCreacion(),
                guardado.getCotizacion().getId()
        );
    }

    public List<CotizacionYearDTO> get_cotizacion_year(Integer year) {
        int y = (year != null) ? year : LocalDate.now().getYear();

        return cotizacionRepository.cotizacionesForYear(y).stream()
                .map(row -> {
                    int mesNumero = ((Number) row[0]).intValue();
                    Long cantidad = ((Number) row[1]).longValue();
                    String mesNombre = Month.of(mesNumero)
                            .getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
                    return new CotizacionYearDTO(mesNombre, cantidad);
                })
                .toList();
    }

    public List<CotizacionResumenDTO> get_last_cotizaciones() {
        return cotizacionRepository.obtenerUltimasCotizaciones(PageRequest.of(0, 5));
    }

    public List<MensajeDashboardDTO> get_mensajes_pendientes_mes() {
        LocalDate now = LocalDate.now();
        int mes = now.getMonthValue();
        int year = now.getYear();
        return mensajeRepository.mensajesPendientesMes(mes, year);
    }

    public Page<CotizacionDashboardDTO> get_cotizaciones_dashboard(Pageable pageable) {
        return cotizacionRepository.findAllByOrderByCreacionDesc(pageable)
                .map(CotizacionMapper::toDashboardDTO);
    }

    public List<CotizacionByUsuarioResponseDTO> getByUser(Long id) {
        return cotizacionRepository.findByUsuarioIdOrderByCreacionDesc(id)
                .stream()
                .map(CotizacionMapper::toDTOGetByUser)
                .toList();
    }

    public Page<CotizacionDashboardDTO> getByUserPaginated(Long id, Pageable pageable) {
        return cotizacionRepository.findByUsuarioIdOrderByCreacionDesc(id, pageable)
                .map(CotizacionMapper::toDashboardDTO);
    }

    public CotizacionFullResponseDTO getByID(Long id) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada con id: " + id));

        return CotizacionMapper.toDTOGetByID(cotizacion);
    }

    public Long countAllCotizaciones() {
        return cotizacionRepository.countAllCotizaciones();
    }

    public Page<CotizacionDashboardDTO> searchByParams(String busqueda, Pageable pageable) {
        return cotizacionRepository.searchByNumeroClienteObservaciones(busqueda, pageable)
                .map(CotizacionMapper::toDashboardDTO);
    }

    private String getNumberFromDB() {
        int year = java.time.Year.now().getValue();
        String yearPrefix = "COT-" + year + "-";

        Optional<Cotizacion> ultimaCotizacion = cotizacionRepository
                .findTopByNumeroStartingWithOrderByCreacionDesc(yearPrefix);

        int nuevoNumero = 1; // Por defecto si no hay registros del año

        if (ultimaCotizacion.isPresent()) {
            String numero = ultimaCotizacion.get().getNumero(); // ej: "COT-2025-054"
            String[] partes = numero.split("-");
            nuevoNumero = Integer.parseInt(partes[2]) + 1;
        }

        String numeroFormateado = String.format("%03d", nuevoNumero);

        return yearPrefix + numeroFormateado;
    }

    public CotizacionFullResponseDTO updateObservaciones(Long id, String observaciones) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada con id: " + id));

        cotizacion.setObservaciones(observaciones);
        Cotizacion updatedCotizacion = cotizacionRepository.save(cotizacion);

        return CotizacionMapper.toDTOGetByID(updatedCotizacion);
    }

    @Transactional
    public CotizacionFullResponseDTO change_state(Long id, EstadoCotizacionRequestDTO request) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada con id: " + id));

        CotizacionEstadoEnum estadoAnterior = cotizacion.getEstado();
        CotizacionEstadoEnum nuevoEstado = request.getNuevoEstado();

        // Verifica que realmente haya un cambio
        if (estadoAnterior.equals(nuevoEstado)) {
            throw new BadRequestException("La cotización ya se encuentra en el estado: " + nuevoEstado);
        }

        // Actualiza el estado actual
        cotizacion.setEstado(nuevoEstado);
        Cotizacion updatedCotizacion = cotizacionRepository.save(cotizacion);

        // Crear registro en historial
        CotizacionHistorialEstado historial = new CotizacionHistorialEstado();
        historial.setCotizacion(cotizacion);
        historial.setEstadoAnterior(estadoAnterior);
        historial.setEstadoNuevo(nuevoEstado);
        historial.setObservacion(request.getObservacion());

        // Si tu DTO incluye usuarioId (quién cambió el estado)
        if (request.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElse(null);
            historial.setUsuario(usuario);
        }

        cotizacionHistorialEstadoRepository.save(historial);

        // Devuelve la cotización actualizada
        return CotizacionMapper.toDTOGetByID(updatedCotizacion);
    }

    public List<ProductoCarritoDetalleDTO> productos(Long id) {
        return detalleRepository.obtenerProductosPorCotizacion(id);
    }

    public List<CotizacionHistorialEstadoResponseDTO> obtenerHistorialPorCotizacion(Long cotizacionId) {
        Cotizacion cotizacion = cotizacionRepository.findById(cotizacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada con id: " + cotizacionId));

        List<CotizacionHistorialEstado> historial = cotizacionHistorialEstadoRepository.findByCotizacionIdOrderByFechaCambioAsc(cotizacionId);

        return historial.stream().map(h -> new CotizacionHistorialEstadoResponseDTO(
                h.getId(),
                h.getEstadoAnterior().name(),
                h.getEstadoNuevo().name(),
                h.getObservacion(),
                h.getFechaCambio(),
                h.getUsuario() != null ? h.getUsuario().getNombre() + " " + h.getUsuario().getApellidos() : null,
                h.getUsuario() != null ? h.getUsuario().getEmail() : null
        )).collect(Collectors.toList());
    }

    public Page<ProductoCarritoDetalleDTO> obtenerProductosDeCotizacion(Long cotizacionId, Pageable pageable) {
        return detalleRepository.findProductosByCotizacionId(cotizacionId, pageable);
    }
}
