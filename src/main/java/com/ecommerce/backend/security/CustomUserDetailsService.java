package com.ecommerce.backend.security;

import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email del usuario no encontrado" + username));

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPasswordd(),
                Collections.singleton(new SimpleGrantedAuthority((usuario.getRol().toString())))
        );
    }
}
