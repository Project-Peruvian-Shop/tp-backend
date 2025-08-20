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
