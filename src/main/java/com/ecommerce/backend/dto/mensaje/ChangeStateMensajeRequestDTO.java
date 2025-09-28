package com.ecommerce.backend.dto.mensaje;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStateMensajeRequestDTO {
    private Integer new_state;
}
