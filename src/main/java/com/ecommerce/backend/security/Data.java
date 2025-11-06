package com.ecommerce.backend.security;

import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Data implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) {

        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            System.out.println("⚠️ No hay usuarios registrados en la base de datos.");
            return;
        }

        // Encriptar y guardar cada usuario
        usuarios.forEach(usuario -> {
            String passwordActual = usuario.getPasswordd();

            // Evitar encriptar dos veces si ya está encriptado
            if (!passwordActual.startsWith("$2a$")) {
                String passwordEncriptado = bCryptPasswordEncoder.encode(passwordActual);
                usuario.setPasswordd(passwordEncriptado);
            }
        });

        usuarioRepository.saveAll(usuarios);

        System.out.println("✅ Proceso de encriptación de contraseñas completado.");
    }
}
