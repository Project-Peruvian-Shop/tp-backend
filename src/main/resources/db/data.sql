insert into usuario (nombre, apellidos, email, passwordd, telefono) values
('admin', 'admin', 'admin@mail.com', 'admin123', '1234567890'),
('user', 'User', 'user@mail.com', 'user123', '0987654321');

insert into imagen (enlace, nombre, alt) values
('https://tuberiasperuanito.com/img/tuberia-pvc-110.jpg', 'Tubería PVC 110mm', 'tuberia-pvc-110mm'),
('https://tuberiasperuanito.com/img/tuberia-pvc-90.jpg', 'Tubería PVC 90mm', 'tuberia-pvc-90mm'),
('https://tuberiasperuanito.com/img/tuberia-hdpe-63.jpg', 'Tubería HDPE 63mm', 'tuberia-hdpe-63mm'),
('https://tuberiasperuanito.com/img/tuberia-hdpe-160.jpg', 'Tubería HDPE 160mm', 'tuberia-hdpe-160mm'),
('https://tuberiasperuanito.com/img/codo-90.jpg', 'Codo PVC 90°', 'codo-pvc-90-grados'),
('https://tuberiasperuanito.com/img/tee-agua.jpg', 'Tee de agua PVC', 'tee-pvc-agua');

insert into categoria (nombre, usos, norma, imagen_id) values
('Tuberías PVC 110mm', 'Conducción de agua fría y desagüe domiciliario', 'ISO 1452', 1),
('Tuberías PVC 90mm', 'Instalaciones sanitarias y redes de agua potable', 'ISO 1452', 2),
('Tuberías HDPE 63mm', 'Transporte de agua a presión en riego agrícola y domicilios', 'ISO 4427', 3),
('Tuberías HDPE 160mm', 'Redes de distribución de agua potable y alcantarillado', 'ISO 4427', 4),
('Codo PVC 90°', 'Conexión de tuberías en ángulo recto para redes de agua', 'ASTM D2466', 5),
('Tee de Agua PVC', 'Distribución de flujo en redes de agua potable', 'ASTM D2466', 6);

-- Productos relacionados con las categorías existentes
INSERT INTO producto (nombre, descripcion, imagen_id, categoria_id) VALUES
('Tubería PVC 110mm x 6m', 'Ideal para desagüe y agua fría, resistente y duradera', 1, 1),
('Tubería PVC 90mm x 6m', 'Ligera y fácil de instalar, utilizada en redes domiciliarias', 1, 2),
('Tubería HDPE 63mm PN10', 'Alta resistencia a la presión, especial para riego agrícola', 2, 3),
('Tubería HDPE 160mm PN12.5', 'Gran diámetro para redes principales de agua potable', 3, 4),
('Codo PVC 90° 110mm', 'Accesorio para desvío de tuberías en ángulo recto', 4, 5),
('Codo PVC 90° 90mm', 'Conexión en ángulo para tuberías de menor diámetro', 5, 5),
('Tee de Agua PVC 110mm', 'Permite dividir el flujo en redes domiciliarias', 3, 3),
('Tee de Agua PVC 90mm', 'Accesorio para derivaciones en instalaciones de agua', 3, 4);

-- Mensajes
INSERT INTO mensaje
(nombre, tipo_documento, documento, telefono, email, contenido, tipo, estado, usuario_id)
VALUES
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