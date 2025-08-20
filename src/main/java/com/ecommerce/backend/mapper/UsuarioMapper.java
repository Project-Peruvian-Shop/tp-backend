package com.ecommerce.backend.mapper;

import com.ecommerce.backend.dto.request.UsuarioRequestDTO;
import com.ecommerce.backend.dto.response.UsuarioResponseDTO;
import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.role.UserRole;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        usuario.setPasswordd(dto.getPassword());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(UserRole.valueOf(dto.getRol()));

        return usuario;
    }
    public static List<UsuarioResponseDTO> toDTOList(List<Usuario> usuarios){
        if (usuarios == null)
        {
            return null;
        }
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }
}
