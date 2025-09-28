package com.ecommerce.backend.repository;

import com.ecommerce.backend.entity.CotizacionPDF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotizacionPdfRepository extends JpaRepository<CotizacionPDF,Long> {
}
