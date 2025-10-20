package com.ecommerce.backend.repository;

import com.ecommerce.backend.dto.cotizacion.CategoriaMesDTO;
import com.ecommerce.backend.dto.cotizacion.ProductoCotizadoMesDTO;
import com.ecommerce.backend.dto.dashboard.CategoriaCotizadaDTO;
import com.ecommerce.backend.dto.dashboard.ProductoCotizadoDTO;
import com.ecommerce.backend.dto.producto.ProductoCarritoDetalleDTO;
import com.ecommerce.backend.entity.CotizacionDetalle;
import com.ecommerce.backend.entity.CotizacionDetalleId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionDetalleRepository extends JpaRepository<CotizacionDetalle, CotizacionDetalleId> {

    @Query("SELECT new com.ecommerce.backend.dto.dashboard.ProductoCotizadoDTO(p.id, p.nombre, SUM(d.cantidad)) " +
            "FROM CotizacionDetalle d " +
            "JOIN d.producto p " +
            "JOIN d.cotizacion c " +
            "WHERE MONTH(c.creacion) = :mes " +
            "AND YEAR(c.creacion) = :year " +
            "GROUP BY p.id, p.nombre " +
            "ORDER BY SUM(d.cantidad) DESC")
    List<ProductoCotizadoDTO> productosMasDemandados(
            @Param("mes") int mes,
            @Param("year") int year,
            Pageable pageable);

    @Query("SELECT new com.ecommerce.backend.dto.dashboard.CategoriaCotizadaDTO(cat.id, cat.nombre, SUM(d.cantidad)) " +
            "FROM CotizacionDetalle d " +
            "JOIN d.producto p " +
            "JOIN p.categoria cat " +
            "JOIN d.cotizacion c " +
            "WHERE MONTH(c.creacion) = :mes " +
            "AND YEAR(c.creacion) = :year " +
            "GROUP BY cat.id, cat.nombre " +
            "ORDER BY SUM(d.cantidad) DESC")
    List<CategoriaCotizadaDTO> categoriasMasDemandadas(
            @Param("mes") int mes,
            @Param("year") int year,
            Pageable pageable);

    @Query("""
                SELECT new com.ecommerce.backend.dto.producto.ProductoCarritoDetalleDTO(
                    p.id,
                    p.nombre,
                    cat.nombre,
                    cat.norma,
                    img.enlace,
                    img.alt,
                    d.cantidad
                )
                FROM CotizacionDetalle d
                JOIN d.producto p
                JOIN p.categoria cat
                JOIN p.imagen img
                JOIN d.cotizacion c
                WHERE c.id = :cotizacionId
            """)
    List<ProductoCarritoDetalleDTO> obtenerProductosPorCotizacion(@Param("cotizacionId") Long cotizacionId);

    @Query("""
                SELECT new com.ecommerce.backend.dto.dashboard.ProductoCotizadoDTO(p.id, p.nombre, COUNT(d))
                FROM CotizacionDetalle d
                JOIN d.producto p
                JOIN d.cotizacion c
                WHERE MONTH(c.creacion) = :mes
                  AND YEAR(c.creacion) = :year
                GROUP BY p.nombre
                ORDER BY COUNT(d) DESC
            """)
    List<ProductoCotizadoDTO> productosMasApariciones(
            @Param("mes") int mes,
            @Param("year") int year,
            Pageable pageable
    );

    @Query("""
                SELECT new com.ecommerce.backend.dto.dashboard.CategoriaCotizadaDTO(cat.id, cat.nombre, COUNT(DISTINCT c))
                FROM CotizacionDetalle d
                JOIN d.producto p
                JOIN p.categoria cat
                JOIN d.cotizacion c
                WHERE MONTH(c.creacion) = :mes
                  AND YEAR(c.creacion) = :year
                GROUP BY cat.id, cat.nombre
                ORDER BY COUNT(DISTINCT c) DESC
            """)
    List<CategoriaCotizadaDTO> categoriasMasApariciones(
            @Param("mes") int mes,
            @Param("year") int year,
            Pageable pageable
    );
}
