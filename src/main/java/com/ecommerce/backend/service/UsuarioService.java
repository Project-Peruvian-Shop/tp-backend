package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.request.UsuarioRequestDTO;
import com.ecommerce.backend.dto.response.UsuarioResponseDTO;
import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.UsuarioMapper;
import com.ecommerce.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDTO> findAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found - id: " + id));
    }
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
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
    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found - nombre: " + nombre));
    }
    public  UsuarioResponseDTO register(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
            throw new RuntimeException("El email " + usuarioRequestDTO.getEmail() + " ya está en uso.");
        }
        Usuario usuario = UsuarioMapper.toEntity(usuarioRequestDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);

        return UsuarioMapper.toDTO(savedUsuario);
    }
    public Optional<UsuarioResponseDTO> login(String email, String passwordd){
        Optional<Usuario> usuarioOpt= usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent()){
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPasswordd().equals(passwordd)) {
                return Optional.of(UsuarioMapper.toDTO(usuario));
            }
        }
        return Optional.empty();
    }
}
