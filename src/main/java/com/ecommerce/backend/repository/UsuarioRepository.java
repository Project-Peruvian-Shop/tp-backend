package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.role.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNombreAndApellidos(String nombre, String apellidos);

    List<Usuario> findByRol(UserRole rol);

    boolean existsByEmail(String email);
}
