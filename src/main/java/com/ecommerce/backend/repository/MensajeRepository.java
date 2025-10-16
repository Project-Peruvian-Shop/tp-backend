package com.ecommerce.backend.repository;

import com.ecommerce.backend.dto.cotizacion.MensajeDashboardDTO;
import com.ecommerce.backend.entity.Mensaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    @Query("SELECT COUNT(m) FROM Mensaje m WHERE FUNCTION('MONTH', m.creacion) = :mes")
    Long count_response_mes(@Param("mes") Long mes);

    @Query("""
            SELECT COUNT(m)
            FROM Mensaje m
            WHERE m.estado = com.ecommerce.backend.enums.MensajeEstadoEnum.PENDIENTE
              AND FUNCTION('MONTH', m.creacion) = :mes
            """)
    Long count_pending_mes(@Param("mes") Long mes);

    @Query("SELECT COUNT(m) FROM Mensaje m")
    Long countAllMensajes();

    @Query("""
            SELECT DISTINCT m
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


    @Query("""
            SELECT new com.ecommerce.backend.dto.cotizacion.MensajeDashboardDTO(
                m.id,
                m.contenido,
                m.tipo,
                m.creacion
            )
            FROM Mensaje m
            WHERE m.estado NOT IN (
                com.ecommerce.backend.enums.MensajeEstadoEnum.RESUELTO,
                com.ecommerce.backend.enums.MensajeEstadoEnum.CERRADO
            )
              AND FUNCTION('MONTH', m.creacion) = :mes
              AND FUNCTION('YEAR', m.creacion) = :year
            ORDER BY m.creacion DESC
            """)
    List<MensajeDashboardDTO> mensajesPendientesMes(
            @Param("mes") int mes,
            @Param("year") int year
    );
}
