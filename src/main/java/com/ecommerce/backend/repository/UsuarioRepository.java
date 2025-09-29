package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Usuario;
import com.ecommerce.backend.role.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNombreAndApellidos(String nombre, String apellidos);

    List<Usuario> findByRol(UserRole rol);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.rol IN (:roles) AND u.id <> :excludedId")
    Page<Usuario> findByRolesExcludingId(
            @Param("roles") List<UserRole> roles,
            @Param("excludedId") Long excludedId,
            Pageable pageable
    );
}
