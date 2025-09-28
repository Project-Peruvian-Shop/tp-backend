package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.usuario.request.UsuarioRequestDTO;
import com.ecommerce.backend.dto.usuario.response.UsuarioResponseDTO;
import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.role.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public static UsuarioResponseDTO toDTO(Usuario usuario){
        if(usuario == null){
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
    public static Usuario toEntity(UsuarioRequestDTO dto){
        if(dto == null){
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());
        usuario.setPasswordd(dto.getPasswordd());
        usuario.setTelefono(dto.getTelefono());
        if (dto.getRol() != null) {
            usuario.setRol(UserRole.valueOf(dto.getRol().toUpperCase()));
        } else {
            usuario.setRol(UserRole.ROLE_USER);
        }

        return usuario;
    }
}
