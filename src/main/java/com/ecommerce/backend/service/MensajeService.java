package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.request.MensajeRequestDTO;
import com.ecommerce.backend.dto.response.MensajeDashboardDTO;
import com.ecommerce.backend.dto.response.MensajeResponseDTO;
import com.ecommerce.backend.entity.Mensaje;
import com.ecommerce.backend.mapper.MensajeMapper;
import com.ecommerce.backend.repository.MensajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    @Transactional
    public MensajeResponseDTO send_contactenos(MensajeRequestDTO mensajeRequestDTO){
        Mensaje mensaje = MensajeMapper.toEntity(mensajeRequestDTO,0);
        Mensaje saved_mensaje = mensajeRepository.save(mensaje);

        return new MensajeResponseDTO(saved_mensaje.getId());
    }
    @Transactional
    public MensajeResponseDTO send_reclamos(MensajeRequestDTO mensajeRequestDTO){
        Mensaje mensaje = MensajeMapper.toEntity(mensajeRequestDTO,0);
        Mensaje saved_mensaje = mensajeRepository.save(mensaje);

        return new MensajeResponseDTO(saved_mensaje.getId());
    }

    public MensajeResponseDTO change_state(Long id, Integer new_state){
        Mensaje mensaje = mensajeRepository.findById(id).orElseThrow(()-> new RuntimeException("Mensaje no encontrado - ID: " + id));
        mensaje.setEstado(new_state);
        Mensaje updated_mensaje = mensajeRepository.save(mensaje);

        return new MensajeResponseDTO(updated_mensaje.getId());
    }

    public MensajeDashboardDTO get_dashboard_menssage(Long mes){
        Long respondidos= mensajeRepository.count_response_mes(mes);
        Long pendientes= mensajeRepository.count_pending_mes(mes);

        return new MensajeDashboardDTO(respondidos,pendientes);
    }

}
