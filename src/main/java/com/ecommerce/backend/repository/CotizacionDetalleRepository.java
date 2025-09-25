package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.CotizacionDetalle;
import com.ecommerce.backend.entity.CotizacionDetalleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotizacionDetalleRepository extends JpaRepository<CotizacionDetalle, CotizacionDetalleId> {
}
