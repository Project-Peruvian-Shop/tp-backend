package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Mensaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    @Query("SELECT COUNT(m) FROM Mensaje m WHERE m.estado=1 AND MONTH(m.creacion) = :mes")
    Long count_response_mes(@Param("mes") Long mes);

    @Query("SELECT COUNT(m) FROM Mensaje m WHERE m.estado=0 AND MONTH(m.creacion) = :mes")
    Long count_pending_mes(@Param("mes") Long mes);

    @Query("SELECT COUNT(m) FROM Mensaje m")
    Long countAllMensajes();

    @Query("""
            SELECT m
            FROM Mensaje m
            WHERE LOWER(m.nombre) LIKE LOWER(CONCAT('%', :filtro, '%'))
               OR LOWER(m.documento) LIKE LOWER(CONCAT('%', :filtro, '%'))
               OR LOWER(m.telefono) LIKE LOWER(CONCAT('%', :filtro, '%'))
               OR LOWER(m.email) LIKE LOWER(CONCAT('%', :filtro, '%'))
               OR LOWER(m.contenido) LIKE LOWER(CONCAT('%', :filtro, '%'))
            """)
    Page<Mensaje> searchByNombreDocumentoTelefonoEmailContenido(
            @Param("filtro") String filtro,
            Pageable pageable
    );

}
