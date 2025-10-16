package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.id <> :excludeId AND p.categoria.id = :categoriaId")
    List<Producto> findTop4ExceptByCategoria(
            @Param("excludeId") Long excludeId,
            @Param("categoriaId") Long categoriaId
    );

    @Query("SELECT COUNT(p) FROM Producto p")
    Long countAllProductos();

    @Query("""
            SELECT p 
            FROM Producto p 
            WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :filtro, '%'))
               OR LOWER(p.categoria.nombre) LIKE LOWER(CONCAT('%', :filtro, '%'))
               OR LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :filtro, '%'))
            ORDER BY LOWER(p.nombre) ASC
            """)
    Page<Producto> searchByNombreCategoriaDescripcion(
            @Param("filtro") String filtro,
            Pageable pageable
    );


    Page<Producto> findByCategoriaId(Long categoriaId, Pageable pageable);

    @Query("""
            SELECT p
            FROM Producto p
            ORDER BY LOWER(p.nombre) ASC
            """)
    Page<Producto> findAllOrderByNombreIgnoreCase(Pageable pageable);
}
