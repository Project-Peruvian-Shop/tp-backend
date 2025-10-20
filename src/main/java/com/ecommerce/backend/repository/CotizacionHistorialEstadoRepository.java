package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.CotizacionHistorialEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionHistorialEstadoRepository extends JpaRepository<CotizacionHistorialEstado, Long> {

    List<CotizacionHistorialEstado> findByCotizacionIdOrderByFechaCambioAsc(Long cotizacionId);

}
