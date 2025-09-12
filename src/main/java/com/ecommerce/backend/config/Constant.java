package com.ecommerce.backend.config;

public class Constant {
    // API Version
    public static final String API_VERSION = "/api/v1";

    // GlobalResponse - error messages
    public static final String GR_ERROR_NO_HANDLER = "The requested resource was not found";
    public static final String GR_ERROR_PARAMETER_TYPE = "The parameter '%s' must be of type '%s'";
    public static final String GR_ERROR_DETAILS = "[%s] %s";

    //TABLES NAMES
    public static final String TABLE_USUARIOS = "usuario";
    public static final String TABLE_PRODUCTOS = "producto";
    public static final String TABLE_MENSAJES = "mensaje";
    public static final String TABLE_COTIZACION= "cotizacion";
    public static final String TABLE_COTIZACION_DETALLE = "cotizacion_detalle";
    public static final String AUTH="auth";
}
