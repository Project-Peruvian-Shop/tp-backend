package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // findAllAndQuantity
    @Query(value = """
            SELECT c.id, c.nombre, COUNT(p.id) AS cantidad
            FROM categoria c
            LEFT JOIN producto p ON p.categoria_id = c.id
            GROUP BY c.id, c.nombre
            """, nativeQuery = true)
    List<Object[]> findAllAndQuantity();

    @Query("SELECT COUNT(p) FROM Categoria p")
    Long countAllCategoria();
}
