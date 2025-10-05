package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.auth.LoginResponseDTO;
import com.ecommerce.backend.dto.auth.RegisterRequestDTO;
import com.ecommerce.backend.dto.auth.RegisterResponseDTO;
import com.ecommerce.backend.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public static Usuario toEntity(RegisterRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());
        usuario.setPasswordd(dto.getPasswordd());
        usuario.setTelefono(dto.getTelefono());

        return usuario;
    }

    public static RegisterResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        RegisterResponseDTO dto = new RegisterResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRol());
        return dto;
    }

    public static LoginResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        LoginResponseDTO dto = new LoginResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRol());
        return dto;
    }
}
