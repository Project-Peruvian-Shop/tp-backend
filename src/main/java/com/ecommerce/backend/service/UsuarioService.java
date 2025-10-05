package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.usuario.UsuarioPerfilDTO;
import com.ecommerce.backend.dto.usuario.UsuarioRequestDTO;
import com.ecommerce.backend.dto.usuario.UsuarioResponseDTO;
import com.ecommerce.backend.dto.usuario.UsuarioSimpleResponseDTO;
import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.exceptions.EntityAlreadyExistsException;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.UsuarioMapper;
import com.ecommerce.backend.repository.UsuarioRepository;
import com.ecommerce.backend.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ecommerce.backend.mapper.UsuarioMapper.toEntity;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Page<UsuarioResponseDTO> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(UsuarioMapper::toDTO);
    }


    public UsuarioPerfilDTO findById(Long id) {
        Usuario usuario =
                usuarioRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario not found - id: " + id));

        return UsuarioMapper.toFindById(usuario);
    }


    public UsuarioResponseDTO save(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new EntityAlreadyExistsException("El email " + usuarioRequestDTO.getEmail() + " ya está en uso.");
        }

        Usuario usuario = toEntity(usuarioRequestDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(savedUsuario);
    }


    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario existingUser = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found - id: " + id));

        existingUser.setNombre(usuarioRequestDTO.getNombre());
        existingUser.setApellidos(usuarioRequestDTO.getApellidos());
        existingUser.setTelefono(usuarioRequestDTO.getTelefono());
        existingUser.setEmail(usuarioRequestDTO.getEmail());

        Usuario updatedUser = usuarioRepository.save(existingUser);
        return UsuarioMapper.toDTO(updatedUser);
    }


    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario not found - id: " + id);
        }
        usuarioRepository.deleteById(id);
    }


    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found - email: " + email));
    }


    public UsuarioResponseDTO findByNombreCompleto(String nombre, String apellidos) {
        Usuario usuario = usuarioRepository.findByNombreAndApellidos(nombre, apellidos)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found - nombre: " + nombre));
        return UsuarioMapper.toDTO(usuario);
    }


    public List<UsuarioResponseDTO> findByRol(UserRole rol) {
        return usuarioRepository.findByRol(rol)
                .stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }


    public Page<UsuarioSimpleResponseDTO> findAllWorkers(Long id, Pageable pageable) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found - id: " + id));

        List<UserRole> roles = List.of(UserRole.ROLE_ADMIN, UserRole.ROLE_MANAGER);

        return usuarioRepository.findByRolesExcludingId(roles, id, pageable)
                .map(UsuarioMapper::toSimpleDTO);
    }


    public Page<UsuarioResponseDTO> searchByParam(String busqueda, Pageable pageable) {
        return usuarioRepository.searchByNombreApellidosEmailTelefonoRol(busqueda, pageable)
                .map(UsuarioMapper::toDTO);
    }

    public Long countAllUsuarios() {
        return usuarioRepository.countAllUsuarios();
    }
}
