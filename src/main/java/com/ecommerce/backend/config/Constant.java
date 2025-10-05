package com.ecommerce.backend.config;

public class Constant {
    // API Version
    public static final String API_VERSION = "/api/v1";

    // GlobalResponse - error messages
    public static final String GR_ERROR_NO_HANDLER = "El endpoint solicitado no existe";
    public static final String GR_ERROR_PARAMETER_TYPE = "El parámetro '%s' debe ser de tipo '%s'";
    public static final String GR_ERROR_DETAILS = "[%s] %s";

    // tables
    public static final String TABLE_IMAGEN = "imagen";
    public static final String TABLE_CATEGORIA = "categoria";
    public static final String TABLE_USUARIOS = "usuario";
    public static final String TABLE_PRODUCTOS = "producto";
    public static final String TABLE_MENSAJES = "mensaje";
    public static final String TABLE_COTIZACION= "cotizacion";
    public static final String TABLE_COTIZACION_DETALLE = "cotizacion_detalle";
    public static final String AUTH="auth";
}
