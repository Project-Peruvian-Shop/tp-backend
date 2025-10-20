package com.ecommerce.backend.repository;

import com.ecommerce.backend.dto.cotizacion.CotizacionResumenDTO;
import com.ecommerce.backend.entity.Cotizacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {


    @Query("SELECT COUNT(DISTINCT c.usuario.id) " +
            "FROM Cotizacion c " +
            "WHERE MONTH(c.creacion) = :mes " +
            "AND YEAR(c.creacion) = :year")
    Long count_usuarios_cotizadores_mes(@Param("mes") int mes, @Param("year") int year);

    @Query("SELECT COUNT(c.usuario.id) " +
            "FROM Cotizacion c " +
            "WHERE c.creacion = (" +
            "   SELECT MIN(c2.creacion) FROM Cotizacion c2 WHERE c2.usuario.id = c.usuario.id" +
            ") AND MONTH(c.creacion) = :mes " +
            "AND YEAR(c.creacion) = :year")
    Long count_usuarios_nuevos_mes(@Param("mes") int mes, @Param("year") int year);

    @Query("SELECT FUNCTION('MONTH', c.creacion), COUNT(c) " +
            "FROM Cotizacion c " +
            "WHERE FUNCTION('YEAR', c.creacion) = :year " +
            "GROUP BY FUNCTION('MONTH', c.creacion) " +
            "ORDER BY FUNCTION('MONTH', c.creacion)")
    List<Object[]> cotizacionesForYear(@Param("year") int year);

    Optional<Cotizacion> findTopByOrderByCreacionDesc();

    List<Cotizacion> findByUsuarioIdOrderByCreacionDesc(Long usuarioId);

    Page<Cotizacion> findByUsuarioIdOrderByCreacionDesc(Long usuarioId, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Cotizacion c")
    Long countAllCotizaciones();

    @Query("""
            SELECT c
            FROM Cotizacion c
            WHERE LOWER(c.numero) LIKE LOWER(CONCAT('%', :filtro, '%'))
               OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :filtro, '%'))
               OR LOWER(c.observaciones) LIKE LOWER(CONCAT('%', :filtro, '%'))
            ORDER BY c.creacion DESC
            """)
    Page<Cotizacion> searchByNumeroClienteObservaciones(
            @Param("filtro") String filtro,
            Pageable pageable
    );

    @Query("""
            SELECT new com.ecommerce.backend.dto.cotizacion.CotizacionResumenDTO(
                c.id,
                c.numero,
                SUM(cd.cantidad),
                c.estado
            )
            FROM CotizacionDetalle cd
            JOIN cd.cotizacion c
            WHERE c.estado IN (com.ecommerce.backend.enums.CotizacionEstadoEnum.PENDIENTE, com.ecommerce.backend.enums.CotizacionEstadoEnum.EN_PROCESO)
            GROUP BY c.id, c.numero, c.estado, c.creacion
            ORDER BY c.creacion DESC
            """)
    List<CotizacionResumenDTO> obtenerUltimasCotizaciones(Pageable pageable);

    Page<Cotizacion> findAllByOrderByCreacionDesc(Pageable pageable);

    @Query("""
                SELECT COUNT(c)
                FROM Cotizacion c
                WHERE c.estado = com.ecommerce.backend.enums.CotizacionEstadoEnum.PENDIENTE
                  AND c.creacion BETWEEN :fechaInicio AND :fechaFin
            """)
    Long countCotizacionesPendientesPorPeriodo(@Param("fechaInicio") java.time.LocalDateTime fechaInicio,
                                               @Param("fechaFin") java.time.LocalDateTime fechaFin);


    @Query("""
                SELECT COUNT(c)
                FROM Cotizacion c
                WHERE c.estado = com.ecommerce.backend.enums.CotizacionEstadoEnum.ACEPTADA
                  AND c.creacion BETWEEN :fechaInicio AND :fechaFin
            """)
    Long countCotizacionesAceptadasPorPeriodo(@Param("fechaInicio") java.time.LocalDateTime fechaInicio,
                                              @Param("fechaFin") java.time.LocalDateTime fechaFin);


}
