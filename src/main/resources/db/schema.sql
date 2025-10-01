-- USE tuberiasperuanito;

--! Table usuario
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    passwordd VARCHAR(255) NOT NULL,
    telefono VARCHAR(25) NOT NULL,
    rol VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER'
);

--! Table imagen
CREATE TABLE IF NOT EXISTS imagen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    enlace VARCHAR(255) NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    alt VARCHAR(50) NOT NULL
);

--! Table categoria
CREATE TABLE IF NOT EXISTS categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usos TEXT NOT NULL,
    norma VARCHAR(50) NOT NULL,
    imagen_id INT NOT NULL,
    FOREIGN KEY (imagen_id) REFERENCES imagen (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

--! Table producto
CREATE TABLE IF NOT EXISTS producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    imagen_id INT NOT NULL,
    categoria_id INT NOT NULL,
    FOREIGN KEY (imagen_id) REFERENCES imagen (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (categoria_id) REFERENCES categoria (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

--! Table cotizacion
CREATE TABLE IF NOT EXISTS cotizacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- datos de la cotizacion
    numero VARCHAR(50) NOT NULL UNIQUE,
    estado TINYINT NOT NULL DEFAULT 0,
    creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    comentario TEXT,
    -- datos del cliente
    nombre VARCHAR(200) NOT NULL,
    tipo_documento TINYINT NOT NULL DEFAULT 0,
    documento VARCHAR(30) NOT NULL,
    telefono VARCHAR(25) NOT NULL,
    email VARCHAR(150) NOT NULL,
    -- empresa
    observaciones TEXT,
    -- usuario relacionado
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE ON UPDATE CASCADE
);

--! Table cotizacion_detalle
CREATE TABLE IF NOT EXISTS cotizacion_detalle (
    producto_id INT NOT NULL,
    cotizacion_id INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (producto_id, cotizacion_id),
    FOREIGN KEY (producto_id) REFERENCES producto (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cotizacion_id) REFERENCES cotizacion (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS cotizacion_pdf (
    id INT AUTO_INCREMENT PRIMARY KEY,
    enlace VARCHAR(255) NOT NULL,
    creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cotizacion_id INT NOT NULL UNIQUE,
    FOREIGN KEY (cotizacion_id) REFERENCES cotizacion (id) ON DELETE CASCADE ON UPDATE CASCADE
);

--! Table mensaje
CREATE TABLE IF NOT EXISTS mensaje (
    id INT AUTO_INCREMENT PRIMARY KEY,
    -- datos del remitente
    nombre VARCHAR(200) NULL,
    tipo_documento TINYINT NULL DEFAULT 0,
    documento VARCHAR(30) NULL,
    telefono VARCHAR(25) NULL,
    email VARCHAR(150) NULL,
    -- contenido del mensaje
    contenido TEXT NOT NULL,
    tipo TINYINT NOT NULL DEFAULT 0,
    estado TINYINT NOT NULL DEFAULT 0,
    -- metadata
    creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    -- usuario relacionado
    usuario_id INT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE SET NULL ON UPDATE CASCADE
);