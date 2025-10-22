package com.ecommerce.backend.security;

import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.enums.UsuarioRolEnum;
import com.ecommerce.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Data implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Usuario> usuarios = List.of(
                new Usuario(null,"Franco", "Tineo", "tineo@mail.com", bCryptPasswordEncoder.encode("franco123"), "999111111", UsuarioRolEnum.ROLE_ADMIN),
                new Usuario(null,"Joao", "Urteaga", "joao@mail.com", bCryptPasswordEncoder.encode("joatix123"), "999222222", UsuarioRolEnum.ROLE_ADMIN)
        );

        usuarioRepository.saveAll(usuarios);

    }
}
