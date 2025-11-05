package com.ecommerce.backend.role;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ROLE_SUPERADMIN,
    ROLE_ADMINISTRADOR,
    ROLE_SUPERVISOR,
    ROLE_CLIENTE;

    @JsonCreator
    public static UserRole fromString(String value) {
        String normalized = value.toUpperCase();
        if (!normalized.startsWith("ROLE_")) {
            normalized = "ROLE_" + normalized;
        }
        return UserRole.valueOf(normalized);
    }
}
