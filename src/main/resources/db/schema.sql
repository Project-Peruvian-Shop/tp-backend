-- USE tuberiasperuanito;

--! Table imagenes
CREATE TABLE IF NOT EXISTS imagen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    enlace VARCHAR(255) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    alt VARCHAR(50) NOT NULL
);

--! Table usuarios
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    passwordd VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    rol VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER'
);

--! Table categorias
CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usos TEXT NOT NULL,
    norma VARCHAR(50) NOT NULL,
    imagen_id INT NOT NULL,
    FOREIGN KEY (imagen_id) REFERENCES imagen (id) ON DELETE CASCADE ON UPDATE CASCADE
);

--! Table productos
CREATE TABLE IF NOT EXISTS producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    imagen_id INT NOT NULL,
    FOREIGN KEY (imagen_id) REFERENCES imagen (id) ON DELETE CASCADE ON UPDATE CASCADE
);

--! Table cotizaciones
CREATE TABLE IF NOT EXISTS cotizacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estado TINYINT NOT NULL DEFAULT 0,
    creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE ON UPDATE CASCADE
);

--! Table mensajes
CREATE TABLE IF NOT EXISTS mensaje (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contenido TEXT NOT NULL,
    tipo TINYINT NOT NULL DEFAULT 0,
    estado TINYINT NOT NULL DEFAULT 0,
    comentario TEXT NULL,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE ON UPDATE CASCADE
);

--! Table cotizaciones_detalle
CREATE TABLE IF NOT EXISTS cotizacion_detalle (
    producto_id INT NOT NULL,
    cotizacion_id INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (producto_id, cotizacion_id),
    FOREIGN KEY (producto_id) REFERENCES producto (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cotizacion_id) REFERENCES cotizacion (id) ON DELETE CASCADE ON UPDATE CASCADE
);
