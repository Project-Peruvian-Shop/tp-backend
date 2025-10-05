insert into usuario (nombre, apellidos, email, passwordd, telefono, rol) values
('admin', 'admin', 'admin@mail.com', 'admin123', '1234567890', 'ROLE_ADMIN'),
('user', 'User', 'user@mail.com', 'user123', '0987654321', 'ROLE_USER');

insert into imagen (enlace, nombre, alt) values
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 'Tubería PVC 110mm', 'tuberia-pvc-110mm'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 'Tubería PVC 90mm', 'tuberia-pvc-90mm'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 'Tubería HDPE 63mm', 'tuberia-hdpe-63mm'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 'Tubería HDPE 160mm', 'tuberia-hdpe-160mm'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 'Codo PVC 90°', 'codo-pvc-90-grados'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 'Tee de agua PVC', 'tee-pvc-agua'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/NTP-399.003.png', 'Catálogo de Tuberías', 'catalogo-tuberias');

insert into categoria (nombre, usos, norma, imagen_id) values
('Linea 1', 'Conducción de agua fría y desagüe domiciliario', 'ISO 1452', 7),
('Linea 2', 'Instalaciones sanitarias y redes de agua potable', 'ISO 1452', 7),
('Linea 3', 'Transporte de agua a presión en riego agrícola y domicilios', 'ISO 4427', 7),
('Linea 4', 'Redes de distribución de agua potable y alcantarillado', 'ISO 4427', 7),
('Linea 5', 'Conexión de tuberías en ángulo recto para redes de agua', 'ASTM D2466', 7),
('Linea 6', 'Distribución de flujo en redes de agua potable', 'ASTM D2466', 7);

-- Productos relacionados con las categorías existentes
insert into producto (nombre, descripcion, imagen_id, categoria_id) values
('Tubería 1 PVC 110mm x 6m', 'Ideal para desagüe y agua fría, resistente y duradera', 1, 1),
('Tubería 2 PVC 90mm x 6m', 'Ligera y fácil de instalar, utilizada en redes domiciliarias', 1, 2),
('Tubería 3 HDPE 63mm PN10', 'Alta resistencia a la presión, especial para riego agrícola', 2, 3),
('Tubería 4 HDPE 160mm PN12.5', 'Gran diámetro para redes principales de agua potable', 3, 4),
('Codo 5 PVC 90° 110mm', 'Accesorio para desvío de tuberías en ángulo recto', 4, 5),
('Codo 6 PVC 90° 90mm', 'Conexión en ángulo para tuberías de menor diámetro', 5, 5),
('Tee 7 de Agua PVC 110mm', 'Permite dividir el flujo en redes domiciliarias', 3, 3),
('Tee 7 de Agua PVC 90mm', 'Accesorio para derivaciones en instalaciones de agua', 3, 4);

-- Mensajes
insert into mensaje
(nombre, tipo_documento, documento, telefono, email, contenido, tipo, estado, medio_respuesta, usuario_id)
values
('Juan Pérez', 1, '12345678', '+987654321', 'juanperez@gmail.com',
 'Estoy interesado en sus productos, por favor envíenme un catálogo.',
 "CONTACTENOS", "PENDIENTE", null, 1),

('María Gómez', 2, '87654321', '+912345678', 'maria.gomez@yahoo.com',
 'Esta es una queja sobre el servicio recibido en su tienda.',
 "QUEJA", "EN_PROCESO", "EMAIL", 1),

('Carla', 1, '111222333', '+12345678', 'cliente.anonimo@test.com',
 'Por favor, envíenme información sobre la garantía de sus productos.',
 "CONTACTENOS", "CERRADO", "WHATSAPP", NULL),

('Carlos López', 1, '11223344', '+999888777', 'carlos.lopez@hotmail.com',
 'Quiero presentar un reclamo formal sobre un producto defectuoso que compré.',
 "RECLAMO", "CERRADO", "PRESENCIAL", 2),

('Ana Torres', 3, '94810122', '+955666444', 'ana.torres@gmail.com',
 'Estoy inconforme con la atención que recibí, por favor contacten conmigo.',
 "CONTACTO", "RESUELTO", "PRESENCIAL", NULL);

-- cotizaciones
insert into cotizacion (id, numero, estado, comentario, nombre, tipo_documento, documento, telefono, email, usuario_id) values
(1, 'COT-2025-1', 0, 'Necesito entrega rápida', 'Juan Pérez', 1, '12345678', '987654321', 'juan@example.com', 1),
(2, 'COT-2025-2', 1, 'Revisar stock', 'María López', 1, '87654321', '987123456', 'maria@example.com', 2),
(3, 'COT-2025-3', 0, 'Cliente urgente', 'Pedro Gómez', 2, '11223344', '986543210', 'pedro@example.com', 1),
(4, 'COT-2025-4', 1, 'Entrega programada', 'Laura Martínez', 1, '99887766', '985678901', 'laura@example.com', 1);


insert into cotizacion (id, numero, estado, comentario, nombre, tipo_documento, documento, telefono, email, usuario_id, creacion) values
-- Octubre 2024
(5, 'COT-2024-10-1', 0, 'Entrega urgente', 'Ana Torres', 1, '11111111', '987111111', 'ana@example.com', 1, '2024-10-05 10:30:00'),
(6, 'COT-2024-10-2', 1, 'Revisar stock', 'Luis Ramírez', 1, '22222222', '987222222', 'luis@example.com', 2, '2024-10-15 14:20:00'),
-- Noviembre 2024
(7, 'COT-2024-11-1', 0, 'Cliente urgente', 'Carla Díaz', 2, '33333333', '987333333', 'carla@example.com', 1, '2024-11-08 09:10:00'),
-- Diciembre 2024
(8, 'COT-2024-12-1', 1, 'Entrega programada', 'Jorge Medina', 1, '44444444', '987444444', 'jorge@example.com', 1, '2024-12-20 16:45:00'),
-- Enero 2025
(9, 'COT-2025-5', 0, 'Necesito entrega rápida', 'Juan Pérez', 1, '12345678', '987654321', 'juan@example.com', 1, '2025-01-10 11:00:00'),
(10, 'COT-2025-6', 1, 'Revisar stock', 'María López', 1, '87654321', '987123456', 'maria@example.com', 2, '2025-01-25 15:30:00'),
-- Febrero 2025
(11, 'COT-2025-7', 0, 'Cliente urgente', 'Pedro Gómez', 2, '11223344', '986543210', 'pedro@example.com', 1, '2025-02-14 12:00:00'),
-- Marzo 2025
(12, 'COT-2025-8', 1, 'Entrega programada', 'Laura Martínez', 1, '99887766', '985678901', 'laura@example.com', 1, '2025-03-22 09:50:00'),
-- Abril 2025
(13, 'COT-2025-9', 0, 'Urgente', 'Mario Silva', 1, '55555555', '987555555', 'mario@example.com', 2, '2025-04-11 10:15:00'),
-- Mayo 2025
(14, 'COT-2025-10', 1, 'Revisar stock', 'Sofía Cruz', 2, '66666666', '987666666', 'sofia@example.com', 1, '2025-05-05 14:00:00'),
-- Junio 2025
(15, 'COT-2025-11', 0, 'Entrega rápida', 'Andrés Rojas', 1, '77777777', '987777777', 'andres@example.com', 1, '2025-06-18 16:30:00'),
-- Julio 2025
(16, 'COT-2025-12', 1, 'Revisar stock', 'Carolina Paredes', 1, '88888888', '987888888', 'carolina@example.com', 2, '2025-07-07 12:45:00'),
-- Agosto 2025
(17, 'COT-2025-13', 0, 'Cliente urgente', 'Diego Vargas', 2, '99999999', '987999999', 'diego@example.com', 1, '2025-08-21 09:20:00'),
-- Septiembre 2025
(18, 'COT-2025-14', 1, 'Entrega programada', 'Natalia Ramos', 1, '10101010', '987101010', 'natalia@example.com', 1, '2025-09-12 11:35:00'),
-- Octubre 2025
(19, 'COT-2025-15', 0, 'Necesito entrega rápida', 'Juan Pérez', 1, '12345678', '987654321', 'juan@example.com', 1, '2025-10-02 08:00:00');

insert into cotizacion_detalle (producto_id, cotizacion_id, cantidad) values
(1, 1, 20),
(2, 1, 20),
(3, 2, 12),
(1, 2, 5),
(2, 3, 55),
(1, 4, 32);

insert into cotizacion_pdf (enlace, cotizacion_id) values
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 2),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 4);
