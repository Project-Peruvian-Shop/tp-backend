package com.ecommerce.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {
    @NotBlank(message = "nombre field cannot be null")
    @Length(min = 3, message = "nombre field must be at least 3 characters long")
    private String nombre;

    @NotBlank(message = "apellidos field cannot be null")
    @Length(min = 3, message = "apellidos field must be at least 3 characters long")
    private String apellidos;

    @NotBlank(message = "email field cannot be null")
    @Email(message = "email field must be a valid email address")
    private String email;

    @NotBlank(message = "telefono field cannot be null")
    private String telefono;

    @NotBlank(message = "passwordd field cannot be null")
    @Length(min = 8, message = "passwordd field must be at least 8 characters long")
    private String passwordd;
}
