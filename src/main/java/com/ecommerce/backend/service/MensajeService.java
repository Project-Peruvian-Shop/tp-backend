package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.mensaje.*;
import com.ecommerce.backend.entity.Mensaje;
import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.enums.MensajeTipoEnum;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.MensajeMapper;
import com.ecommerce.backend.repository.MensajeRepository;
import com.ecommerce.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public MensajeResponseDTO send_contactenos(MensajeRequestDTO mensajeRequestDTO) {
        mensajeRequestDTO.setTipo(MensajeTipoEnum.CONTACTENOS);

        Mensaje mensaje = MensajeMapper.toEntity(mensajeRequestDTO);
        if (mensajeRequestDTO.getUsuario_id() != null) {
            Optional<Usuario> usuario = usuarioRepository.findById(mensajeRequestDTO.getUsuario_id());
            usuario.ifPresent(mensaje::setUsuario);
        }

        return MensajeMapper.toDTO(mensajeRepository.save(mensaje));
    }

    @Transactional
    public MensajeResponseDTO send_reclamos(MensajeRequestDTO mensajeRequestDTO) {
        Mensaje mensaje = MensajeMapper.toEntity(mensajeRequestDTO);
        if (mensajeRequestDTO.getUsuario_id() != null) {
            usuarioRepository.findById(mensajeRequestDTO.getUsuario_id())
                    .ifPresent(mensaje::setUsuario);
        }

        Mensaje saved = mensajeRepository.save(mensaje);
        return MensajeMapper.toDTO(mensajeRepository.save(mensaje));
    }

    @Transactional(readOnly = true)
    public MensajeFullResponseDTO get_mensaje_by_id(Long id) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensaje no encontrado - ID: " + id));

        return MensajeMapper.toFullDTO(mensaje);
    }

    public MensajeFullResponseDTO change_state(Long id, EstadoMensajeRequestDTO nuevoEstado) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensaje no encontrado - ID: " + id));

        mensaje.setEstado(nuevoEstado.getNuevoEstado());
        Mensaje mensajeEstadoActualizado = mensajeRepository.save(mensaje);

        return MensajeMapper.toFullDTO(mensajeEstadoActualizado);
    }

    public MensajeDashboardDTO get_dashboard_menssage(Long mes) {
        Long respondidos = mensajeRepository.count_response_mes(mes);
        Long pendientes = mensajeRepository.count_pending_mes(mes);

        return new MensajeDashboardDTO(respondidos, pendientes);
    }

    public Page<MensajeDashboardResponseDTO> findDashboardPaginated(Pageable pageable) {
        return mensajeRepository.findAll(pageable).map(MensajeMapper::toDashboardDTO);
    }

    public Long countAllMensajes() {
        return mensajeRepository.countAllMensajes();
    }

    public Page<MensajeDashboardResponseDTO> searchByParams(String busqueda, Pageable pageable) {
        return mensajeRepository.searchByNombreDocumentoTelefonoEmailContenido(busqueda, pageable)
                .map(MensajeMapper::toDashboardDTO);
    }
}

