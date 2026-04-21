SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

CREATE TABLE IF NOT EXISTS hotel (
    id_hotel BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    direccion VARCHAR(200),
    ciudad VARCHAR(100),
    pais VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    estrellas TINYINT,
    activo BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tipo_habitacion (
    id_tipo_habitacion BIGINT AUTO_INCREMENT PRIMARY KEY,
    hotel_id BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    capacidad TINYINT,
    precio_base DECIMAL(10,2),
    activo BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (hotel_id) REFERENCES hotel(id_hotel)
);

CREATE TABLE IF NOT EXISTS disponibilidad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_habitacion_id BIGINT NOT NULL,
    fecha DATE NOT NULL,
    cantidad_total INT NOT NULL,
    cantidad_disponible INT NOT NULL,
    precio DECIMAL(10,2),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tipo_habitacion_id) REFERENCES tipo_habitacion(id_tipo_habitacion)
);

CREATE TABLE IF NOT EXISTS reserva (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_habitacion_id BIGINT NOT NULL,
    nombre_cliente VARCHAR(100) NOT NULL,
    email_cliente VARCHAR(100),
    telefono_cliente VARCHAR(20),
    fecha_checkin DATE NOT NULL,
    fecha_checkout DATE NOT NULL,
    cantidad_habitaciones INT DEFAULT 1,
    precio_total DECIMAL(10,2),
    estado ENUM('PENDIENTE','CONFIRMADA','CANCELADA') DEFAULT 'PENDIENTE',
    notas TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tipo_habitacion_id) REFERENCES tipo_habitacion(id_tipo_habitacion)
);

-- HOTELES
INSERT INTO hotel (nombre, descripcion, direccion, ciudad, pais, telefono, email, estrellas, activo, created_at) VALUES
('Hotel Caribe Cancun', 'Resort frente al mar con vista panorámica al Caribe', 'Blvd. Kukulcán Km 9', 'Cancún', 'México', '998123456', 'caribe@altairis.com', 5, true, NOW()),
('Hotel Montaña Real', 'Hotel boutique en las alturas con spa y naturaleza', 'Calle Sierra Nevada 45', 'Medellín', 'Colombia', '574987654', 'montana@altairis.com', 4, true, NOW()),
('Hotel Centro Madrid', 'Hotel ejecutivo en el corazón de la ciudad', 'Gran Vía 28', 'Madrid', 'España', '911456789', 'madrid@altairis.com', 4, true, NOW());

-- TIPOS DE HABITACIÓN
INSERT INTO tipo_habitacion (nombre, descripcion, capacidad, precio_base, activo, created_at, hotel_id) VALUES
('Habitacion Estándar', 'Habitación doble con vista al jardín', 2, 850.00, true, NOW(), 1),
('Habitacion Suite', 'Suite de lujo con jacuzzi y vista al mar', 2, 2900.00, true, NOW(), 1),
('Habitacion Familiar', 'Amplia habitación para familias con camas adicionales', 4, 1200.00, true, NOW(), 2),
('Habitacion Ejecutiva', 'Habitación con escritorio, sala y amenidades premium', 2, 1500.00, true, NOW(), 3);

-- DISPONIBILIDAD
INSERT INTO disponibilidad (fecha, cantidad_total, cantidad_disponible, precio, created_at, tipo_habitacion_id) VALUES
('2026-04-21', 10, 8, 850.00, NOW(), 1),
('2026-04-22', 10, 10, 850.00, NOW(), 1),
('2026-04-23', 10, 10, 850.00, NOW(), 1),
('2026-04-21', 5, 4, 2900.00, NOW(), 2),
('2026-04-22', 5, 5, 2900.00, NOW(), 2),
('2026-04-23', 5, 5, 2900.00, NOW(), 2),
('2026-04-21', 8, 8, 1200.00, NOW(), 3),
('2026-04-22', 8, 8, 1200.00, NOW(), 3),
('2026-04-21', 6, 6, 1500.00, NOW(), 4),
('2026-04-22', 6, 6, 1500.00, NOW(), 4);

-- RESERVAS
INSERT INTO reserva (nombre_cliente, email_cliente, telefono_cliente, fecha_checkin, fecha_checkout, cantidad_habitaciones, precio_total, estado, notas, created_at, tipo_habitacion_id) VALUES
('Carlos Mendez', 'carlos@gmail.com', '88112233', '2026-04-21', '2026-04-23', 2, 1700.00, 'CONFIRMADA', 'Cliente frecuente, solicita vista al mar', NOW(), 1),
('Ana Rodriguez', 'ana@gmail.com', '87654321', '2026-04-21', '2026-04-22', 1, 2900.00, 'CONFIRMADA', 'Aniversario, solicitar decoración especial', NOW(), 2),
('Empresa Soluciones SA', 'contacto@soluciones.com', '22334455', '2026-04-22', '2026-04-23', 2, 3000.00, 'PENDIENTE', 'Reserva corporativa, factura requerida', NOW(), 4),
('Maria Lopez', 'maria@gmail.com', '86543210', '2026-04-21', '2026-04-22', 1, 1200.00, 'PENDIENTE', 'Viaje familiar con niños', NOW(), 3);