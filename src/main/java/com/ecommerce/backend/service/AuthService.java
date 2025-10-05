package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.auth.LoginRequestDTO;
import com.ecommerce.backend.dto.auth.LoginResponseDTO;
import com.ecommerce.backend.dto.auth.RegisterRequestDTO;
import com.ecommerce.backend.dto.auth.RegisterResponseDTO;
import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.enums.UsuarioRolEnum;
import com.ecommerce.backend.exceptions.EntityAlreadyExistsException;
import com.ecommerce.backend.exceptions.InvalidCredentialsException;
import com.ecommerce.backend.exceptions.ResourceNotFoundException;
import com.ecommerce.backend.mapper.AuthMapper;
import com.ecommerce.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        if (usuarioRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new EntityAlreadyExistsException("El email " + registerRequestDTO.getEmail() + " ya está en uso.");
        }
        Usuario usuario = AuthMapper.toEntity(registerRequestDTO);
        usuario.setRol(UsuarioRolEnum.ROLES_USER);
        Usuario savedUsuario = usuarioRepository.save(usuario);

        return AuthMapper.registerToDTO(savedUsuario);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String email = loginRequestDTO.getEmail();
        String passwordd = loginRequestDTO.getPasswordd();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el email: " + email));

        if (!usuario.getPasswordd().equals(passwordd)) {
            throw new InvalidCredentialsException("Contraseña incorrecta, intente de nuevo.");
        }
        return AuthMapper.loginToDTO(usuario);
    }
}
