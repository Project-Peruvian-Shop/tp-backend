package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.usuario.request.UsuarioRequestDTO;
import com.ecommerce.backend.dto.usuario.response.UsuarioResponseDTO;
import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.exceptions.InvalidCredentialsException;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.UsuarioMapper;
import com.ecommerce.backend.repository.UsuarioRepository;
import com.ecommerce.backend.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ecommerce.backend.mapper.UsuarioMapper.toEntity;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found - id: " + id));
    }

    public UsuarioResponseDTO save(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new RuntimeException("El email " + usuarioRequestDTO.getEmail() + " ya está en uso.");
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


    public UsuarioResponseDTO register(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new RuntimeException("El email " + usuarioRequestDTO.getEmail() + " ya está en uso.");
        }
        Usuario usuario = toEntity(usuarioRequestDTO);
        usuario.setRol(UserRole.ROLE_USER);
        Usuario savedUsuario = usuarioRepository.save(usuario);

        return UsuarioMapper.toDTO(savedUsuario);
    }

    public UsuarioResponseDTO login(String email, String passwordd) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found - email: " + email));
        if (!usuario.getPasswordd().equals(passwordd)) {
            throw new InvalidCredentialsException("Contraseña incorrecta, intente de nuevo.");
        }
        return UsuarioMapper.toDTO(usuario);

    }
}
