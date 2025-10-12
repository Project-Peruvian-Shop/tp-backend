insert into usuario (nombre, apellidos, email, passwordd, telefono, rol) values
('Wilmer', 'Guevara', 'wilmer@mail.com', 'wilmer123', '999000000', 'ROLE_ADMIN'),
('Franco', 'Tineo', 'tineo@mail.com', 'franco123', '999111111', 'ROLE_ADMIN'),
('Joao', 'Urteaga', 'joao@mail.com', 'joatix123', '999222222', 'ROLE_ADMIN'),
('Cliente', 'Cliente', 'cliente@mail.com', 'cliente123', '0987654321', 'ROLE_USER'),
('Socio', 'TP', 'socio@mail.com', 'socio123', '0987654321', 'ROLE_ADMIN'),
('Jorge', 'Luis', 'jorge@mail.com', 'user123', '0987654321', 'ROLE_MANAGER'),
('Steve', 'Harrington', 'steve@mail.com', 'user123', '0987654321', 'ROLE_MANAGER'),
('Felix', 'Ruiz', 'felix@mail.com', 'user123', '0987654321', 'ROLE_MANAGER'),
('Sebastian', 'Perez', 'seb@mail.com', 'user123', '0987654321', 'ROLE_MANAGER'),
('Charles', 'Leclerc', 'charles@mail.com', 'user123', '0987654321', 'ROLE_MANAGER');

insert into imagen (enlace, nombre, alt) values
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Fluidos-a-Presion-12-SP-scaled.png', 'NTP 399.002', 'NTP-399.002'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Desague-1-12%E2%80%B3-SP-1-scaled-e1750740124332.png', 'NTP 399.003', 'NTP-399.003'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Instalacion-Electrica-1-12%E2%80%B3-SP-scaled-e1751253145336.png', 'NTP 399.006', 'NTP-399.006'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Fluidos-a-Presion-con-Union-Roscada-1-12-1-scaled.png', 'NTP 399.166', 'NTP-399.166'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/1000008612-removebg-preview.png', 'NTP ISO 1452-2', 'NTP-ISO-1452-2'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578.jpg', 'NTP ISO 4435', 'NTP-ISO-4435'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/NTP-399.003.png', 'Catálogo de Tuberías', 'catalogo-tuberias');

insert into categoria (nombre, norma, imagen_id, usos) values
('Conducción de fluidos a presión Unión SP', 'NTP 399.002', 7, 'Los tubos de PVC para agua a presión con unión tipo Campana-Espiga, son diseñados para soportar diferentes rangos de presiones según su presion nominal, lo que es ideal para instalaciones como riego, descarga con presión, agua potable a una temperatura de 25°C, el uso de unión con cemento solvente garantiza un unión hermética y duradera.'),
('Instalaciones de desagüe', 'NTP 399.003', 7, 'Los tubos de desagüe, se fabrican por el proceso de extrusión se usan como componentes esenciales en sistemas de fontanería para la evacuación de aguas residuales, instalaciones domiciliarias de desagüe y descarga de fluidos sin presión.'),
('Instalaciones eléctricas', 'NTP 399.006', 7, 'Los tubos de PVC para electricidad son ideales para instalaciones de baja y mediana tensión, por su uso se puede instalar tanto de forma aérea como enterrado, ya que su diseño le permite soportar esfuerzos externos, además de su fácil manipulación es ideal para protección de cables en casas, oficinas, tiendas supermercados.'),
('Conducción de fluidos a presión Unión Roscada', 'NTP 399.166', 7, 'Los tubos de PVC para agua a presión con unión tipo rosca, son diseñados para soportar presiones de hasta 10 bar de forma continua en instalaciones de servicio de agua potable a una temperatura de 25°C, el uso de la rosca garantiza la fácil manipulación o cambio en caso de reparación a la vez que brinda una buena heremeticidad con el ajuste.'),
('Abastecimiento de agua, drenaje y alcantarillado con presión', 'NTP ISO 1452-2', 7, 'Los tubos de PVC para agua a presión de norma NTP ISO 1452-2 son adecuados para diferentes tipo de instalaciones: conducciones de agua principales y derivaciones enterradas; transporte de agua en conducciones aéreas, en el exterior y en el interior de edificios; y drenaje y alcantarillado, enterrado o aéreo con presión, además que por su unión con anillo elastomérico son de fácil instalación sin uso de cementos manteniéndose una excelente hermeticidad.'),
('Alcantarillado y drenaje sin presión', 'NTP ISO 4435', 7, 'Los tubos de norma NTP ISO 4435 son diseñados para para sistemas sin presión de drenaje y alcantarillado, que transportan desagües domésticos e industriales, así como aguas superficiales; además de sus grandes propiedades a la corrosión es ideal para aguas servidas. Su fácil instalación con su unión con anillo elastomérico da una ventaja en la rapidez y tiempo.');

-- Productos relacionados con las categorías existentes
insert into producto (nombre, descripcion, imagen_id, categoria_id) values
-- CATEGORÍA 1: Tuberías para conducción de fluidos a presión Unión SP (imagen_id = 1)
('Tuberias para Fluidos a Presión 1/2" SP', 'Tubería de 1/2" para conducción de fluidos a presión, segura y eficiente.', 1, 1),
('Tuberias para Fluidos a Presión 3/4" SP', 'Tubería de 3/4" de alta durabilidad para sistemas de presión.', 1, 1),
('Tuberias para Fluidos a Presión 1" SP', 'Tubería de 1" diseñada para instalaciones hidráulicas bajo presión.', 1, 1),
('Tuberias para Fluidos a Presión 1 1/2" SP', 'Tubería de 1 1/2" resistente, ideal para sistemas presurizados.', 1, 1),
('Tuberias para Fluidos a Presión 2" SP', 'Tubería de 2" para transporte eficiente de fluidos a presión.', 1, 1),
('Tuberias para Fluidos a Presión 3" SP', 'Tubería de 3" para sistemas hidráulicos con estándares NTP - 399.002.', 1, 1),
('Tuberias para Fluidos a Presión 4" SP', 'Tubería de 4" de alta calidad para conducción de fluidos.', 1, 1),
('Tuberias para Fluidos a Presión 6" SP', 'Tubería de 6" con excelente resistencia a la presión.', 1, 1),
('Tuberias para Fluidos a Presión 8" SP', 'Tubería de 8" para aplicaciones industriales de fluidos a presión.', 1, 1),

-- CATEGORÍA 2: Tuberías para conducción de desagüe (imagen_id = 2)
('Tuberias para Desague 1 1/2" SP', 'Tubería de 1/2" para sistemas de desagüe, durable y eficiente.', 2, 2),
('Tuberias para Desague 2" SP', 'Tubería de 2" diseñada para drenaje y evacuación de líquidos.', 2, 2),
('Tuberias para Desague 3" SP', 'Tubería de 3" para desagüe, fabricada con altos estándares de calidad.', 2, 2),
('Tuberias para Desague 4" SP', 'Tubería de 4" para sistemas de drenaje confiables y resistentes.', 2, 2),
('Tuberias para Desague 6" SP', 'Tubería de 6" para evacuación de aguas residuales en grandes instalaciones.', 2, 2),
('Tuberias para Desague 8" SP', 'Tubería de 8" para desagüe, ideal para redes de saneamiento.', 2, 2),

-- CATEGORÍA 3: Tuberías para instalaciones eléctricas de desagüe (imagen_id = 3)
('Tuberias para Desague 1/2" SP', 'Tubería de 1/2" para instalación eléctrica, resistente y flexible.', 3, 3),
('Tuberias para Desague 5/8" SP', 'Tubería de 5/8" ideal para canalización eléctrica segura.', 3, 3),
('Tuberias para Desague 3/4" SP', 'Tubería de 3/4" para protección de cables eléctricos.', 3, 3),
('Tuberias para Desague 1" SP', 'Tubería de 1" para instalaciones eléctricas con alta durabilidad.', 3, 3),
('Tuberias para Desague 1 1/2" SP', 'Tubería de 1 1/2" para canalización eficiente de conductores eléctricos.', 3, 3),
('Tuberias para Desague 2" SP', 'Tubería de 2" para infraestructura eléctrica, segura y confiable.', 3, 3),

-- CATEGORÍA 4: Tuberías para fluidos a presión Unión Roscada (imagen_id = 4)
('Tuberias para Fluidos a Presion con Unión Roscada 1/2"', 'Tubería de 1/2" con unión roscada para fácil instalación en sistemas de presión.', 4, 4),
('Tuberias para Fluidos a Presion con Unión Roscada 3/4"', 'Tubería de 3/4" con rosca, ideal para conexiones hidráulicas.', 4, 4),
('Tuberias para Fluidos a Presion con Unión Roscada 1"', 'Tubería de 1" con unión roscada, resistente y segura.', 4, 4),
('Tuberias para Fluidos a Presion con Unión Roscada 1 1/4"', 'Tubería de 1 1/4" con rosca para sistemas de presión confiables.', 4, 4),
('Tuberias para Fluidos a Presion con Unión Roscada 1 1/2"', 'Tubería de 1 1/2" con unión roscada para montaje rápido.', 4, 4),
('Tuberias para Fluidos a Presion con Unión Roscada 2"', 'Tubería de 2" con conexión roscada, ideal para redes de presión.', 4, 4),

-- CATEGORÍA 5: Tuberías para abastecimiento de agua, drenaje y alcantarillado con presión (imagen_id = 5)
('Tuberias para Fluidos a Presión 63mm UF', 'Tubería de 63mm para sistemas de presión, fabricada según NTP - ISO 1452.', 5, 5),
('Tuberias para Fluidos a Presión 75mm UF', 'Tubería de 75mm para conducción de fluidos a presión, conforme a normativas.', 5, 5),
('Tuberias para Fluidos a Presión 90mm UF', 'Tubería de 90mm ideal para redes de fluidos presurizados, resistente y confiable.', 5, 5),
('Tuberias para Fluidos a Presión 110mm UF', 'Tubería de 110mm diseñada para soportar presión en sistemas hidráulicos.', 5, 5),
('Tuberias para Fluidos a Presión 140mm UF', 'Tubería de 140mm con alta resistencia para aplicaciones de presión.', 5, 5),
('Tuberias para Fluidos a Presión 160mm UF', 'Tubería de 160mm para redes de presión, garantizando durabilidad y seguridad.', 5, 5),
('Tuberias para Fluidos a Presión 200mm UF', 'Tubería de 200mm para grandes instalaciones de fluidos a presión.', 5, 5),

-- CATEGORÍA 6: Tuberías para alcantarillado y drenaje sin presión (imagen_id = 6)
('Tuberias para Alcantarillado 110mm UF', 'Tubería de 110mm para redes de alcantarillado, resistente y durable.', 6, 6),
('Tuberias para Alcantarillado 160mm UF', 'Tubería de 160mm ideal para sistemas de drenaje y alcantarillado.', 6, 6),
('Tuberias para Alcantarillado 200mm UF', 'Tubería de 200mm para conducción eficiente de aguas residuales.', 6, 6);

-- Mensajes
insert into mensaje
(nombre, tipo_documento, documento, telefono, email, contenido, tipo, estado, medio_respuesta, usuario_id)
values
('Juan Pérez', 'DNI', '12345678', '+987654321', 'juanperez@gmail.com',
 'Estoy interesado en sus productos, por favor envíenme un catálogo.',
 'CONTACTENOS', 'PENDIENTE', null, 1),

('María Gómez', 'RUC', '87654321', '+912345678', 'maria.gomez@yahoo.com',
 'Esta es una queja sobre el servicio recibido en su tienda.',
 'QUEJA', 'EN_PROCESO', 'EMAIL', 1),

('Carla', 'PASAPORTE', '111222333', '+12345678', 'cliente.anonimo@test.com',
 'Por favor, envíenme información sobre la garantía de sus productos.',
 'CONTACTENOS', 'CERRADO', 'WHATSAPP', NULL),

('Carlos López', 'DNI', '11223344', '+999888777', 'carlos.lopez@hotmail.com',
 'Quiero presentar un reclamo formal sobre un producto defectuoso que compré.',
 'RECLAMO', 'CERRADO', 'PRESENCIAL', 2),

('Ana Torres', 'OTRO', '94810122', '+955666444', 'ana.torres@gmail.com',
 'Estoy inconforme con la atención que recibí, por favor contacten conmigo.',
 'CONTACTENOS', 'RESUELTO', 'PRESENCIAL', NULL);

-- cotizaciones
insert into cotizacion (id, numero, estado, comentario, nombre, tipo_documento, documento, telefono, email, usuario_id, creacion) values
(1, 'COT-2025-1', 'PENDIENTE', 'Necesito entrega rápida', 'Juan Pérez', 'DNI', '12345678', '987654321', 'juan@example.com', 1,'2024-12-05 11:30:30'),
(2, 'COT-2025-2', 'EN_PROCESO', 'Revisar stock', 'María López', 'DNI', '87654321', '987123456', 'maria@example.com', 2,'2024-01-10 09:30:30'),
(3, 'COT-2025-3', 'PENDIENTE', 'Cliente urgente', 'Pedro Gómez', 'RUC', '11223344', '986543210', 'pedro@example.com', 1,'2024-03-17 10:00:00'),
(4, 'COT-2025-4', 'EN_PROCESO', 'Entrega programada', 'Laura Martínez', 'PASAPORTE', '99887766', '985678901', 'laura@example.com', 1, '2024-05-17 09:30:00');


insert into cotizacion (id, numero, estado, comentario, nombre, tipo_documento, documento, telefono, email, usuario_id, creacion) values
-- Octubre 2024
(5, 'COT-2024-10-1', 'PENDIENTE', 'Entrega urgente', 'Ana Torres', 'DNI', '11111111', '987111111', 'ana@example.com', 1, '2024-10-05 10:30:00'),
(6, 'COT-2024-10-2', 'EN_PROCESO', 'Revisar stock', 'Luis Ramírez', 'DNI', '22222222', '987222222', 'luis@example.com', 2, '2024-10-15 14:20:00'),
-- Noviembre 2024
(7, 'COT-2024-11-1', 'ENVIADA', 'Cliente urgente', 'Carla Díaz', 'RUC', '33333333', '987333333', 'carla@example.com', 1, '2024-11-08 09:10:00'),
-- Diciembre 2024
(8, 'COT-2024-12-1', 'ACEPTADA', 'Entrega programada', 'Jorge Medina', 'DNI', '44444444', '987444444', 'jorge@example.com', 1, '2024-12-20 16:45:00'),
-- Enero 2025
(9, 'COT-2025-5', 'RECHAZADA', 'Necesito entrega rápida', 'Juan Pérez', 'DNI', '12345678', '987654321', 'juan@example.com', 1, '2025-01-10 11:00:00'),
(10, 'COT-2025-6', 'EN_PROCESO', 'Revisar stock', 'María López', 'DNI', '87654321', '987123456', 'maria@example.com', 2, '2025-01-25 15:30:00'),
-- Febrero 2025
(11, 'COT-2025-7', 'CERRADA', 'Cliente urgente', 'Pedro Gómez', 'RUC', '11223344', '986543210', 'pedro@example.com', 1, '2025-02-14 12:00:00'),
-- Marzo 2025
(12, 'COT-2025-8', 'CERRADA', 'Entrega programada', 'Laura Martínez', 'DNI', '99887766', '985678901', 'laura@example.com', 1, '2025-03-22 09:50:00'),
-- Abril 2025
(13, 'COT-2025-9', 'PENDIENTE', 'Urgente', 'Mario Silva', 'DNI', '55555555', '987555555', 'mario@example.com', 2, '2025-04-11 10:15:00'),
-- Mayo 2025
(14, 'COT-2025-10', 'EN_PROCESO', 'Revisar stock', 'Sofía Cruz', 'RUC', '66666666', '987666666', 'sofia@example.com', 1, '2025-05-05 14:00:00'),
-- Junio 2025
(15, 'COT-2025-11', 'PENDIENTE', 'Entrega rápida', 'Andrés Rojas', 'DNI', '77777777', '987777777', 'andres@example.com', 1, '2025-06-18 16:30:00'),
-- Julio 2025
(16, 'COT-2025-12', 'EN_PROCESO', 'Revisar stock', 'Carolina Paredes', 'DNI', '88888888', '987888888', 'carolina@example.com', 2, '2025-07-07 12:45:00'),
-- Agosto 2025
(17, 'COT-2025-13', 'PENDIENTE', 'Cliente urgente', 'Diego Vargas', 'RUC', '99999999', '987999999', 'diego@example.com', 1, '2025-08-21 09:20:00'),
-- Septiembre 2025
(18, 'COT-2025-14', 'EN_PROCESO', 'Entrega programada', 'Natalia Ramos', 'DNI', '10101010', '987101010', 'natalia@example.com', 1, '2025-09-12 11:35:00'),
-- Octubre 2025
(19, 'COT-2025-15', 'PENDIENTE', 'Necesito entrega rápida', 'Juan Pérez', 'DNI', '12345678', '987654321', 'juan@example.com', 1, '2025-10-02 08:00:00');

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
