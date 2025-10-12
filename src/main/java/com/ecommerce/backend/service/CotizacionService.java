package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.cotizacion.CategoriaMesDTO;
import com.ecommerce.backend.dto.cotizacion.*;
import com.ecommerce.backend.dto.cotizacion.ProductoCotizadoMesDTO;
import com.ecommerce.backend.entity.*;
import com.ecommerce.backend.enums.CotizacionEstadoEnum;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.CotizacionMapper;
import com.ecommerce.backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CotizacionService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final CotizacionRepository cotizacionRepository;
    private final CotizacionDetalleRepository detalleRepository;
    private final CotizacionPdfRepository pdfRepository;
    private final FileUploadService fileUploadService;
    private final MensajeRepository mensajeRepository;

    @Transactional
    public CotizacionResponseDTO save_cotizacion(CotizacionRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioID())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setNumero(this.getNumberFromDB());
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

        String fileName = UUID.randomUUID().toString().substring(0, 6) + ".pdf";

        String filePath = fileUploadService.uploadFile(archivo, fileName, "uploads/cotizaciones");

        CotizacionPDF pdf = new CotizacionPDF();
        pdf.setEnlace(filePath);
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

    public List<ProductoCotizadoMesDTO> get_productos_cotizados_mes(Integer mes, Integer year) {
        LocalDate now = LocalDate.now();
        int m = (year != null) ? mes : now.getMonthValue();
        int y = (year != null) ? year : now.getYear();
        return detalleRepository.productos_cotizados_mes(m, y);
    }

    public List<CategoriaMesDTO> get_lineas_cotizadas_mes(Integer mes, Integer year) {
        LocalDate now = LocalDate.now();
        int m = (mes != null) ? mes : now.getMonthValue();
        int y = (year != null) ? year : now.getYear();
        return detalleRepository.lineasCotizadasMes(m, y);
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
        return cotizacionRepository.obtenerUltimas5Cotizaciones();
    }

    public List<MensajeDashboardDTO> get_mensajes_pendientes_mes() {
        LocalDate now = LocalDate.now();
        int mes = now.getMonthValue();
        int year = now.getYear();
        return mensajeRepository.mensajesPendientesMes(mes, year);
    }

    public Page<CotizacionDashboardDTO> get_cotizaciones_dashboard(Pageable pageable) {
        return cotizacionRepository.findAll(pageable)
                .map(CotizacionMapper::toDashboardDTO);
    }

    public List<CotizacionByUsuarioResponseDTO> getByUser(Long id) {
        return cotizacionRepository.findByUsuarioIdOrderByCreacionDesc(id)
                .stream()
                .map(CotizacionMapper::toDTOGetByUser)
                .toList();
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
        Optional<Cotizacion> ultimaCotizacion = cotizacionRepository.findTopByOrderByIdDesc();

        if (ultimaCotizacion.isPresent()) {
            String numero = ultimaCotizacion.get().getNumero();
            String[] lista = numero.split("-");
            String nuevoNumero = String.valueOf(Integer.parseInt(lista[2]) + 1);
            return "COT-" + year + "-" + nuevoNumero;
        }

        return "COT-" + year + "-1";
    }

    public CotizacionFullResponseDTO updateObservaciones(Long id, String observaciones) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada con id: " + id));

        cotizacion.setObservaciones(observaciones);
        Cotizacion updatedCotizacion = cotizacionRepository.save(cotizacion);

        return CotizacionMapper.toDTOGetByID(updatedCotizacion);
    }

    public CotizacionFullResponseDTO change_state(Long id, EstadoCotizacionRequestDTO nuevoEstado) {
        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada con id: " + id));

        cotizacion.setEstado(nuevoEstado.getNuevoEstado());
        Cotizacion updatedCotizacion = cotizacionRepository.save(cotizacion);

        return CotizacionMapper.toDTOGetByID(updatedCotizacion);
    }
}
