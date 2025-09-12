package com.ecommerce.backend.role;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserRole {
    ROLE_ADMIN,
    ROLE_MANAGER,
    ROLE_USER;

    @JsonCreator
    public static UserRole fromString(String value) {
        String normalized = value.toUpperCase();
        if (!normalized.startsWith("ROLE_")) {
            normalized = "ROLE_" + normalized;
        }
        return UserRole.valueOf(normalized);
    }
}
