insert into usuario (nombre, apellidos, email, passwordd, telefono, rol) values
('Wilmer', 'Guevara', 'wilmer@mail.com', 'wilmer123', '999000000', 'ROLE_ADMIN'),
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
--IMAGENES DE LAS CATEGORIAS:
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/NTP-399.002.png', 'Conducción de fluidos a presión Unión SP', 'NTP 399.002'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/NTP-399.003.png', 'Instalaciones de desagüe', 'NTP 399.003'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/10/NTP-399.006.png', 'Instalaciones eléctricas', 'NTP 399.006'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/NTP-399.166.png', 'Conducción de fluidos a presión Unión Roscada', 'NTP 399.166'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/NTP-ISO-1452-2-2.png', 'Abastecimiento de agua, drenaje y alcantarillado con presión', 'NTP ISO 1452-2'),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/NTP-ISO-4435.png', 'Alcantarillado y drenaje sin presión', 'NTP ISO 4435');



insert into categoria (nombre, norma, imagen_id, usos) values
('Conducción de fluidos a presión Unión SP', 'NTP 399.002', 7, 'Los tubos de PVC para agua a presión con unión tipo Campana-Espiga, son diseñados para soportar diferentes rangos de presiones según su presion nominal, lo que es ideal para instalaciones como riego, descarga con presión, agua potable a una temperatura de 25°C, el uso de unión con cemento solvente garantiza un unión hermética y duradera.'),
('Instalaciones de desagüe', 'NTP 399.003', 8, 'Los tubos de desagüe, se fabrican por el proceso de extrusión se usan como componentes esenciales en sistemas de fontanería para la evacuación de aguas residuales, instalaciones domiciliarias de desagüe y descarga de fluidos sin presión.'),
('Instalaciones eléctricas', 'NTP 399.006', 9, 'Los tubos de PVC para electricidad son ideales para instalaciones de baja y mediana tensión, por su uso se puede instalar tanto de forma aérea como enterrado, ya que su diseño le permite soportar esfuerzos externos, además de su fácil manipulación es ideal para protección de cables en casas, oficinas, tiendas supermercados.'),
('Conducción de fluidos a presión Unión Roscada', 'NTP 399.166', 10, 'Los tubos de PVC para agua a presión con unión tipo rosca, son diseñados para soportar presiones de hasta 10 bar de forma continua en instalaciones de servicio de agua potable a una temperatura de 25°C, el uso de la rosca garantiza la fácil manipulación o cambio en caso de reparación a la vez que brinda una buena heremeticidad con el ajuste.'),
('Abastecimiento de agua, drenaje y alcantarillado con presión', 'NTP ISO 1452-2', 11, 'Los tubos de PVC para agua a presión de norma NTP ISO 1452-2 son adecuados para diferentes tipo de instalaciones: conducciones de agua principales y derivaciones enterradas; transporte de agua en conducciones aéreas, en el exterior y en el interior de edificios; y drenaje y alcantarillado, enterrado o aéreo con presión, además que por su unión con anillo elastomérico son de fácil instalación sin uso de cementos manteniéndose una excelente hermeticidad.'),
('Alcantarillado y drenaje sin presión', 'NTP ISO 4435', 12, 'Los tubos de norma NTP ISO 4435 son diseñados para para sistemas sin presión de drenaje y alcantarillado, que transportan desagües domésticos e industriales, así como aguas superficiales; además de sus grandes propiedades a la corrosión es ideal para aguas servidas. Su fácil instalación con su unión con anillo elastomérico da una ventaja en la rapidez y tiempo.');

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
(nombre, tipo_documento, documento, telefono, email, contenido, tipo, estado, medio_respuesta, usuario_id, creacion)
values
('Juan Pérez', 'DNI', '12345678', '+987654321', 'juanperez@gmail.com',
 'Estoy interesado en sus productos de PVC de 2 pulgadas. ¿Podrían enviarme una cotización detallada?',
 'CONTACTENOS', 'PENDIENTE', null, 1, '2024-12-05 11:30:30'),

('María Gómez', 'RUC', '20654321987', '+912345678', 'maria.gomez@yahoo.com',
 'Realicé una compra hace dos semanas y el pedido llegó incompleto. Necesito una solución urgente.',
 'QUEJA', 'EN_PROCESO', 'EMAIL', 1, '2023-08-30 11:30:30'),

('Carla Rivas', 'PASAPORTE', 'PZ1122334', '+12345678', 'cliente.anonimo@test.com',
 '¿Qué tipo de garantía ofrecen en las tuberías de PVC clase 10? Estoy considerando una compra mayorista.',
 'CONTACTENOS', 'CERRADO', 'WHATSAPP', NULL, '2024-12-05 11:30:30'),

('Carlos López', 'DNI', '11223344', '+999888777', 'carlos.lopez@hotmail.com',
 'Compré tuberías de PVC de 3 pulgadas y algunas vinieron con defectos de fabricación. Deseo presentar un reclamo.',
 'RECLAMO', 'CERRADO', 'PRESENCIAL', 2, '2025-01-25 11:30:30'),

('Ana Torres', 'OTRO', '94810122', '+955666444', 'ana.torres@gmail.com',
 'La atención en la sucursal de San Isidro fue deficiente. Agradecería que me contacten para resolverlo.',
 'QUEJA', 'RESUELTO', 'PRESENCIAL', NULL, '2025-10-16 11:30:30'),

('Pedro Salazar', 'DNI', '44556677', '+988776655', 'pedro.salazar@empresa.com',
 'Estoy buscando cotización para 100 metros de tubería PVC de 4" y accesorios de unión.',
 'CONTACTENOS', 'PENDIENTE', NULL, 3, '2025-10-10 10:15:20'),

('Lucía Fernández', 'RUC', '20588741236', '+976543210', 'ventas@constructoraandes.pe',
 'Requerimos cotización por mayor para tuberías de PVC clase 10 y 15. Favor de enviar proforma por correo.',
 'CONTACTENOS', 'EN_PROCESO', 'EMAIL', 1, '2025-09-02 14:40:50'),

('Diego Ramos', 'DNI', '33445566', '+933221100', 'diegoramos@gmail.com',
 'He intentado comunicarme varias veces sin respuesta. Necesito saber el estado de mi pedido.',
 'QUEJA', 'EN_PROCESO', 'WHATSAPP', 4, '2025-08-21 09:25:00'),

('Rosa Medina', 'DNI', '55667788', '+977665544', 'rosa.medina@outlook.com',
 'Deseo obtener información sobre los métodos de pago para pedidos al por mayor.',
 'CONTACTENOS', 'RESUELTO', 'EMAIL', NULL, '2025-07-15 16:45:10'),

('Miguel Torres', 'DNI', '66778899', '+911223344', 'miguel.torres@gmail.com',
 'Recibí un producto diferente al solicitado, por lo que quiero dejar constancia de mi reclamo.',
 'RECLAMO', 'PENDIENTE', 'TELEFONO', 2, '2025-06-28 08:35:00');


insert into cotizacion (id, numero, estado, comentario, nombre, tipo_documento, documento, telefono, email, usuario_id, creacion) values
(1, 'COT-2024-1', 'PENDIENTE', 'Entrega urgente', 'Ana Torres', 'DNI', '11111111', '987111111', 'ana@example.com', 1, '2024-10-20 09:15:00'),
(2, 'COT-2024-2', 'EN_PROCESO', 'Revisar stock', 'Luis Ramírez', 'DNI', '22222222', '987222222', 'luis@example.com', 2, '2024-10-20 11:30:00'),
(3, 'COT-2024-3', 'ENVIADA', 'Cliente solicita información', 'Carla Díaz', 'RUC', '33333333', '987333333', 'carla@example.com', 1, '2024-11-02 10:00:00'),
(4, 'COT-2024-4', 'ACEPTADA', 'Entrega programada', 'Jorge Medina', 'DNI', '44444444', '987444444', 'jorge@example.com', 1, '2024-11-10 15:45:00'),
(5, 'COT-2024-5', 'RECHAZADA', 'Cliente canceló pedido', 'Juan Pérez', 'DNI', '12345678', '987654321', 'juan@example.com', 1, '2024-12-01 09:00:00'),
(6, 'COT-2024-6', 'CERRADA', 'Cotización finalizada', 'María López', 'DNI', '87654321', '987123456', 'maria@example.com', 2, '2024-12-05 14:30:00'),
(7, 'COT-2025-7', 'PENDIENTE', 'Necesito entrega rápida', 'Pedro Gómez', 'RUC', '11223344', '986543210', 'pedro@example.com', 1, '2025-01-10 11:00:00'),
(8, 'COT-2025-8', 'ENVIADA', 'Revisar stock', 'Laura Martínez', 'DNI', '99887766', '985678901', 'laura@example.com', 1, '2025-01-15 16:20:00'),
(9, 'COT-2025-9', 'ACEPTADA', 'Entrega programada', 'Mario Silva', 'DNI', '55555555', '987555555', 'mario@example.com', 2, '2025-02-02 09:50:00'),
(10, 'COT-2025-10', 'RECHAZADA', 'Cliente urgente', 'Sofía Cruz', 'RUC', '66666666', '987666666', 'sofia@example.com', 1, '2025-02-14 12:15:00'),
(11, 'COT-2025-11', 'CERRADA', 'Revisar disponibilidad', 'Andrés Rojas', 'DNI', '77777777', '987777777', 'andres@example.com', 1, '2025-03-03 14:30:00'),
(12, 'COT-2025-12', 'PENDIENTE', 'Entrega rápida', 'Carolina Paredes', 'DNI', '88888888', '987888888', 'carolina@example.com', 2, '2025-03-15 12:45:00'),
(13, 'COT-2025-13', 'EN_PROCESO', 'Cliente urgente', 'Diego Vargas', 'RUC', '99999999', '987999999', 'diego@example.com', 1, '2025-04-01 09:20:00'),
(14, 'COT-2025-14', 'ENVIADA', 'Entrega programada', 'Natalia Ramos', 'DNI', '10101010', '987101010', 'natalia@example.com', 1, '2025-04-12 11:35:00'),
(15, 'COT-2025-15', 'ACEPTADA', 'Entrega rápida', 'Juan Pérez', 'DNI', '12345678', '987654321', 'juan@example.com', 1, '2025-05-02 08:00:00'),
(16, 'COT-2025-16', 'RECHAZADA', 'Cotización enviada por correo', 'Luis Castro', 'DNI', '12121212', '987121212', 'luis.castro@mail.com', 2, '2025-05-02 10:15:00'),
(17, 'COT-2025-17', 'CERRADA', 'Cliente aprobó la proforma', 'Marta Ruiz', 'RUC', '23232323', '987232323', 'marta.ruiz@mail.com', 1, '2025-05-02 09:00:00'),
(18, 'COT-2025-18', 'PENDIENTE', 'Esperando confirmación', 'David Pérez', 'DNI', '34343434', '987343434', 'davidp@mail.com', 2, '2025-05-03 14:40:00'),
(19, 'COT-2025-19', 'EN_PROCESO', 'Pago realizado', 'Andrea Sánchez', 'DNI', '45454545', '987454545', 'andrea@mail.com', 3, '2025-05-04 11:25:00'),
(20, 'COT-2025-20', 'ENVIADA', 'Cliente desistió de la compra', 'José Ramos', 'RUC', '56565656', '987565656', 'jose.ramos@mail.com', 1, '2025-05-05 16:55:00'),
(21, 'COT-2025-21', 'ACEPTADA', 'Se está preparando la cotización', 'Natalia Ortega', 'DNI', '67676767', '987676767', 'natalia@mail.com', 1, '2025-05-06 10:35:00'),
(22, 'COT-2025-22', 'RECHAZADA', 'Pedido entregado', 'Héctor Salas', 'DNI', '78787878', '987787878', 'hector@mail.com', 2, '2025-05-07 12:00:00'),
(23, 'COT-2025-23', 'CERRADA', 'Esperando confirmación de entrega', 'Daniela León', 'DNI', '89898989', '987898989', 'daniela@mail.com', 1, '2025-05-08 09:45:00'),
(24, 'COT-2025-24', 'PENDIENTE', 'Solicitud información adicional', 'Lucía Fernández', 'DNI', '90909090', '987909090', 'lucia@mail.com', 2, '2025-05-10 09:15:00'),
(25, 'COT-2025-25', 'EN_PROCESO', 'Revisión técnica', 'Pedro Gutiérrez', 'RUC', '91919191', '987919191', 'pedro.g@mail.com', 1, '2025-05-12 11:30:00'),
(26, 'COT-2025-26', 'ENVIADA', 'Cotización enviada al cliente', 'Carolina Rojas', 'DNI', '92929292', '987929292', 'carolina.r@mail.com', 3, '2025-05-14 14:45:00'),
(27, 'COT-2025-27', 'ACEPTADA', 'Cliente aprobó proforma', 'José Pérez', 'DNI', '93939393', '987939393', 'jose.p@mail.com', 1, '2025-05-16 10:00:00'),
(28, 'COT-2025-28', 'RECHAZADA', 'Pago parcial recibido', 'María Vargas', 'RUC', '94949494', '987949494', 'maria.v@mail.com', 2, '2025-05-18 16:30:00'),
(29, 'COT-2025-29', 'CERRADA', 'Pendiente confirmación medidas', 'Andrés Silva', 'DNI', '95959595', '987959595', 'andres@mail.com', 1, '2025-05-20 09:00:00'),
(30, 'COT-2025-30', 'PENDIENTE', 'Revisión almacén', 'Carla Díaz', 'DNI', '96969696', '987969696', 'carla.diaz@mail.com', 1, '2025-05-22 13:20:00'),
(31, 'COT-2025-31', 'EN_PROCESO', 'Cliente canceló pedido', 'Jorge Alarcón', 'DNI', '97979797', '987979797', 'jorge.a@mail.com', 2, '2025-05-23 08:40:00'),
(32, 'COT-2025-32', 'ENVIADA', 'Pedido entregado con éxito', 'Ana Ruiz', 'RUC', '98989898', '987989898', 'ana.ruiz@mail.com', 1, '2025-05-25 12:10:00'),
(33, 'COT-2025-33', 'ACEPTADA', 'Aprobado en obra', 'Luis Hernández', 'DNI', '99999990', '987999000', 'luis.h@mail.com', 3, '2025-05-30 15:45:00'),
(34, 'COT-2025-34', 'RECHAZADA', 'Cotización urgente', 'Sofía Torres', 'DNI', '10101010', '987101010', 'sofia.torres@mail.com', 1, '2025-06-01 11:00:00'),
(35, 'COT-2025-35', 'CERRADA', 'Cliente pagó', 'Miguel Gamarra', 'RUC', '20202020', '987202020', 'miguel.g@mail.com', 2, '2025-06-02 15:30:00'),
(36, 'COT-2025-36', 'PENDIENTE', 'Validación técnica', 'Carmen Silva', 'DNI', '30303030', '987303030', 'carmen.s@mail.com', 3, '2025-06-03 09:50:00'),
(37, 'COT-2025-37', 'ACEPTADA', 'Entrega programada', 'Ricardo Lozano', 'DNI', '40404040', '987404040', 'ricardo.l@mail.com', 1, '2025-08-04 17:25:00'),
(38, 'COT-2025-38', 'ENVIADA', 'Esperando respuesta cliente', 'Paola Gutiérrez', 'RUC', '50505050', '987505050', 'paola.g@mail.com', 2, '2025-09-05 10:40:00'),
(39, 'COT-2025-39', 'ACEPTADA', 'Cliente aprobó', 'Alberto Ramos', 'DNI', '60606060', '987606060', 'alberto@mail.com', 3, '2025-09-06 15:20:00'),
(40, 'COT-2025-40', 'RECHAZADA', 'Falta confirmar cantidades', 'Camila Sánchez', 'DNI', '70707070', '987707070', 'camila@mail.com', 1, '2025-09-07 09:10:00'),
(41, 'COT-2025-41', 'CERRADA', 'Entrega parcial aprobada', 'Juan Alvarado', 'RUC', '80808080', '987808080', 'juan.alvarado@mail.com', 2, '2025-10-08 13:45:00'),
(42, 'COT-2025-42', 'PENDIENTE', 'Solicitó descuento', 'Rosa Castillo', 'DNI', '11111111', '987111111', 'rosa.c@mail.com', 3, '2025-10-09 08:30:00'),
(43, 'COT-2025-43', 'EN_PROCESO', 'Aprobada cambios mínimos', 'Mario Vargas', 'DNI', '22222222', '987222222', 'mario.v@mail.com', 1, '2025-10-10 16:20:00'),
(44, 'COT-2025-44', 'ACEPTADA', 'Esperando validación ingeniería', 'Gabriela Torres', 'RUC', '33333333', '987333333', 'gabriela@mail.com', 2, '2025-10-11 10:10:00'),
(45, 'COT-2025-45', 'ACEPTADA', 'Cotización confirmada', 'Hugo Medina', 'DNI', '44444444', '987444444', 'hugo.m@mail.com', 3, '2025-10-12 14:55:00'),
(46, 'COT-2025-46', 'RECHAZADA', 'Pendiente firma de contrato', 'Juliana Flores', 'DNI', '55555555', '987555555', 'juliana@mail.com', 1, '2025-10-13 11:15:00'),
(47, 'COT-2025-47', 'ACEPTADA', 'Entrega completada', 'Ana Torres', 'DNI', '11111111', '987111111', 'ana@example.com', 1, '2025-10-14 09:15:00'),
(48, 'COT-2025-48', 'ACEPTADA', 'Revisar stock', 'Luis Ramírez', 'DNI', '22222222', '987222222', 'luis@example.com', 2, '2025-10-15 11:30:00'),
(49, 'COT-2025-49', 'ACEPTADA', 'Cliente solicita información', 'Carla Díaz', 'RUC', '33333333', '987333333', 'carla@example.com', 1, '2025-10-16 10:00:00'),
(50, 'COT-2025-50', 'ACEPTADA', 'Entrega programada', 'Jorge Medina', 'DNI', '44444444', '987444444', 'jorge@example.com', 1, '2025-10-17 15:41:00'),
(51, 'COT-2025-51', 'PENDIENTE', 'Entrega programada', 'Jorge Medina', 'DNI', '44444444', '987444444', 'jorge@example.com', 1, '2025-10-20 15:42:00'),
(52, 'COT-2025-52', 'PENDIENTE', 'Entrega programada', 'Jorge Medina', 'DNI', '44444444', '987444444', 'jorge@example.com', 1, '2025-10-20 15:43:00'),
(53, 'COT-2025-53', 'ACEPTADA', 'Entrega programada', 'Jorge Medina', 'DNI', '44444444', '987444444', 'jorge@example.com', 1, '2025-10-20 15:44:00'),
(54, 'COT-2025-54', 'ACEPTADA', 'Entrega programada', 'Jorge Medina', 'DNI', '44444444', '987444444', 'jorge@example.com', 1, '2025-10-20 15:45:00');


-- Detalles de cotización (continuación y completado)
insert into cotizacion_detalle (producto_id, cotizacion_id, cantidad) values
-- Cotización 1
(1, 1, 20),
(2, 1, 20),
-- Cotización 2
(3, 2, 12),
(1, 2, 5),
-- Cotización 3
(2, 3, 55),
-- Cotización 4
(1, 4, 32),

-- Cotización 5
(4, 5, 10),
(5, 5, 8),

-- Cotización 6
(6, 6, 12),
(7, 6, 15),

-- Cotización 7
(10, 7, 25),
(11, 7, 30),

-- Cotización 8
(13, 8, 20),
(14, 8, 12),

-- Cotización 9
(15, 9, 10),
(16, 9, 8),

-- Cotización 10
(18, 10, 5),
(19, 10, 7),

-- Cotización 11
(20, 11, 20),
(21, 11, 25),

-- Cotización 12
(23, 12, 15),
(24, 12, 10),

-- Cotización 13
(25, 13, 18),
(26, 13, 20),

-- Cotización 14
(27, 14, 12),
(28, 14, 10),

-- Cotización 15
(29, 15, 25),
(30, 15, 20),

-- Cotización 16
(32, 16, 10),
(33, 16, 15),

-- Cotización 17
(35, 17, 8),
(30, 17, 10),

-- Cotización 18
(28, 18, 12),
(32, 18, 15),

-- Cotización 19
(12, 19, 20),
(15, 19, 25),

-- Cotización 20
(1, 20, 12),
(5, 20, 8),

-- Cotización 21
(3, 21, 15),
(7, 21, 10),

-- Cotización 22
(10, 22, 25),

-- Cotización 23
(14, 23, 10),
(12, 23, 5),

-- Cotización 24
(18, 24, 20),

-- Cotización 25
(25, 25, 12),
(27, 25, 6),

-- Cotización 26
(30, 26, 18),
(29, 26, 10),

-- Cotización 27
(35, 27, 5),
(33, 27, 15),

-- Cotización 28
(1, 28, 12),
(5, 28, 8),

-- Cotización 29
(7, 29, 20),
(8, 29, 10),

-- Cotización 30
(9, 30, 15),
(10, 30, 10),
(11, 30, 15),
(12, 30, 10),

-- Cotización 31
(13, 31, 20),
(14, 31, 25),
(15, 31, 15),

-- Cotización 32
(20, 32, 30),
(21, 32, 10),

-- Cotización 33
(22, 33, 10),
(23, 33, 5),

-- Cotización 34
(25, 34, 15),
(26, 34, 20),

-- Cotización 35
(27, 35, 12),

-- Cotización 36
(28, 36, 10),
(30, 36, 8),

-- Cotización 37
(32, 37, 20),
(33, 37, 25),

-- Cotización 38
(1, 38, 10),
(5, 38, 15),

-- Cotización 39
(3, 39, 20),
(7, 39, 8),

-- Cotización 40
(2, 40, 12),
(4, 40, 10),

-- Cotización 41
(6, 41, 25),
(8, 41, 18),

-- Cotización 42
(10, 42, 20),
(11, 42, 12),

-- Cotización 43
(12, 43, 30),
(14, 43, 15),

-- Cotización 44
(15, 44, 18),
(16, 44, 10),

-- Cotización 45
(18, 45, 25),
(19, 45, 20),

-- Cotización 46
(20, 46, 15),
(21, 46, 10),

-- Cotización 47
(22, 47, 12),
(23, 47, 18),

-- Cotización 48
(24, 48, 14),
(25, 48, 20),

-- Cotización 49
(26, 49, 22),
(27, 49, 16),

-- Cotización 50
(28, 50, 10),
(30, 50, 15);


insert into cotizacion_pdf (enlace, cotizacion_id) values
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 2),
('https://tuberiasperuanito.com/wp-content/uploads/2025/04/Tuberias-para-Alcantarillado-110mm-UF-scaled-e1750736328578-300x300.jpg', 4);
