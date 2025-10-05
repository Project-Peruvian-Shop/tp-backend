package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.usuario.UsuarioPerfilDTO;
import com.ecommerce.backend.dto.usuario.UsuarioRequestDTO;
import com.ecommerce.backend.dto.usuario.UsuarioResponseDTO;
import com.ecommerce.backend.dto.usuario.UsuarioSimpleResponseDTO;
import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.enums.UsuarioRolEnum;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setRol(usuario.getRol().name());
        return dto;
    }

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());
        usuario.setPasswordd(dto.getPasswordd());
        usuario.setTelefono(dto.getTelefono());
        if (dto.getRol() != null) {
            usuario.setRol(UsuarioRolEnum.valueOf(dto.getRol()));
        } else {
            usuario.setRol(UsuarioRolEnum.ROLE_USER);
        }

        return usuario;
    }

    public static UsuarioPerfilDTO toFindById(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return UsuarioPerfilDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre() + " " + usuario.getApellidos())
                .rol(usuario.getRol().name())
                .email(usuario.getEmail())
                .telefono(usuario.getTelefono())
                .build();
    }

    public static UsuarioSimpleResponseDTO toSimpleDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return UsuarioSimpleResponseDTO.builder()
                .id(usuario.getId())
                .nombreCompleto(usuario.getNombre() + " " + usuario.getApellidos())
                .role(usuario.getRol().name())
                .build();
    }
}
