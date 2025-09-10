package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.request.MensajeRequestDTO;
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

}
