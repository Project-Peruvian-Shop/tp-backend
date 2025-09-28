package com.ecommerce.backend.dto.mensaje.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDashboardDTO {

    private Long mensaje_response_count_mes;
    private Long mensaje_pending_count_mes;

}
