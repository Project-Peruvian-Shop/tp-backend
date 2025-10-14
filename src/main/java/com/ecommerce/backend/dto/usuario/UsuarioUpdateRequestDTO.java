package com.ecommerce.backend.dto.usuario;

import com.ecommerce.backend.enums.UsuarioRolEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateRequestDTO {
    @NotBlank(message = "nombre field cannot be null")
    private String nombre;

    @NotBlank(message = "apellidos field cannot be null")
    private String apellidos;

    @NotBlank(message = "email field cannot be null")
    @Email(message = "email field must be a valid email address")
    private String email;

    @NotBlank(message = "telefono field cannot be null")
    private String telefono;

    @NotNull(message = "rol field cannot be null")
    private UsuarioRolEnum rol;
}
