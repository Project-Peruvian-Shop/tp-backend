package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.cotizacion.CotizacionPdfDTO;
import com.ecommerce.backend.dto.cotizacion.CotizacionProductoDTO;
import com.ecommerce.backend.dto.cotizacion.CotizacionRequestDTO;
import com.ecommerce.backend.dto.cotizacion.CotizacionResponseDTO;
import com.ecommerce.backend.entity.*;
import com.ecommerce.backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
}
