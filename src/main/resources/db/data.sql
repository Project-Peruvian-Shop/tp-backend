insert into usuario (nombre, apellidos, email, passwordd, telefono) values
('admin', 'admin', 'admin@mail.com', 'admin123', '1234567890'),
('user', 'User', 'user@mail.com', 'user123', '0987654321');

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
(nombre, tipo_documento, documento, telefono, email, contenido, tipo, estado, usuario_id)
values
('Juan Pérez', 1, '12345678', '+987654321', 'juanperez@gmail.com',
 'Hola, estoy interesado en sus productos de tuberías. ¿Podrían enviarme una cotización?',
 0, 0, 1),

('María Gómez', 2, '87654321', '+912345678', 'maria.gomez@yahoo.com',
 'Quiero hacer seguimiento a mi pedido con número #4587. Gracias.',
 1, 1, 1),

('Carla', 1, '111222333', '+12345678', 'cliente.anonimo@test.com',
 'Por favor, envíenme información sobre la garantía de sus productos.',
 0, 0, NULL),

('Carlos López', 1, '11223344', '+999888777', 'carlos.lopez@hotmail.com',
 'El producto que recibí tiene un defecto, ¿cómo puedo solicitar cambio?',
 2, 2, 2),

-- mensaje tipo reclamo sin datos de documento
('Ana Torres', 3, '94810122', '+955666444', 'ana.torres@gmail.com',
 'Estoy inconforme con la atención que recibí, por favor contacten conmigo.',
 2, 0, NULL);

-- cotizaciones
insert into cotizacion (id, numero, estado, comentario, nombre, tipo_documento, documento, telefono, email, usuario_id) values
(1, 'COT-2025-1', 0, 'Necesito entrega rápida', 'Juan Pérez', 1, '12345678', '987654321', 'juan@example.com', 1),
(2, 'COT-2025-2', 1, 'Revisar stock', 'María López', 1, '87654321', '987123456', 'maria@example.com', 2),
(3, 'COT-2025-3', 0, 'Cliente urgente', 'Pedro Gómez', 2, '11223344', '986543210', 'pedro@example.com', 1),
(4, 'COT-2025-4', 1, 'Entrega programada', 'Laura Martínez', 1, '99887766', '985678901', 'laura@example.com', 1);


insert into cotizacion_detalle (producto_id, cotizacion_id, cantidad) values
(1, 1, 20),
(2, 1, 20),
(3, 2, 12),
(1, 2, 5),
(2, 3, 55),
(1, 4, 32);

insert into cotizacion_pdf (enlace, cotizacion_id) values
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 4);
