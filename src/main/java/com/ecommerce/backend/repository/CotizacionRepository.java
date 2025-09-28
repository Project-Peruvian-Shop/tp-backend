package com.ecommerce.backend.repository;

import com.ecommerce.backend.dto.cotizacion.CotizacionDashboardDTO;
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

    @Query(
            value = "SELECT new com.ecommerce.backend.dto.cotizacion.CotizacionDashboardDTO(" +
                    "c.id, c.creacion, c.nombre, c.email, c.telefono, c.comentario, c.estado) " +
                    "FROM Cotizacion c " +
                    "ORDER BY c.creacion DESC",
            countQuery = "SELECT COUNT(c) FROM Cotizacion c"
    )
    Page<CotizacionDashboardDTO> findAllCotizacionesDashboard(Pageable pageable);

    Optional<Cotizacion> findTopByOrderByIdDesc();
}
