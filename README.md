# Altairis Booking System — MVP Backoffice
 
Sistema de backoffice operativo para la gestión hotelera B2B de Viajes Altairis. Permite administrar hoteles, tipos de habitación, disponibilidad e inventario, y reservas desde una interfaz centralizada.
 
---
 
## Stack tecnológico
 
| Capa | Tecnología |
|---|---|
| Backend | Java 21 + Spring Boot |
| Frontend | Angular 18 |
| Base de datos | MySQL 8.0 |
| Contenedores | Docker + Docker Compose |
 
---
 
## Requisitos previos
 
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado y corriendo
No se requiere instalar Java, Node.js, Maven ni MySQL de forma local.
 
---
 
## Levantar el proyecto
 
```bash
git clone https://github.com/Santy0608/prueba-tecnica-fdsa.git
cd prueba-tecnica-fdsa
docker-compose up --build
```
 
Docker levantará automáticamente los 3 servicios en orden:
 
1. **MySQL** — espera hasta estar saludable
2. **Backend** — espera a MySQL
3. **Frontend** — espera al Backend
La primera vez tarda entre 3 y 5 minutos por la descarga de imágenes y compilación.
 
---
 
## URLs de acceso
 
| Servicio | URL |
|---|---|
| Frontend | http://localhost:4200 |
| Backend API | http://localhost:8080/api |
| MySQL | localhost:3307 |
 
---
 
## Datos de prueba
 
El proyecto incluye un script `init.sql` que se ejecuta automáticamente al levantar Docker, cargando:
 
- 3 hoteles
- 4 tipos de habitación
- 10 registros de disponibilidad
- 4 reservas en distintos estados
No es necesario cargar datos manualmente para visualizar el sistema funcionando.
 
---
 
## Funcionalidades implementadas
 
### Hoteles
- Listado con búsqueda por nombre
- Alta, edición y eliminación de hoteles
### Tipos de Habitación
- Listado filtrable por hotel (selector desplegable)
- Búsqueda por nombre
- Alta, edición y eliminación asociada a un hotel
### Disponibilidad
- Registro de disponibilidad por tipo de habitación y fecha
- Filtro por rango de fechas y tipo de habitación
- Actualización de inventario
### Reservas
- Registro de reservas con validación de stock en tiempo real
- Al crear una reserva, el inventario se descuenta automáticamente por cada fecha del período
- Al cancelar una reserva, el inventario se devuelve automáticamente
- Filtro por estado, nombre de cliente y rango de fechas
- Cambio de estado: PENDIENTE → CONFIRMADA / CANCELADA
---
 
## Decisiones técnicas
 
**Lógica de inventario integrada**
La creación de una reserva valida la disponibilidad por cada fecha entre check-in y check-out antes de confirmarla. Si no hay stock suficiente en alguna fecha, la operación se rechaza con un mensaje claro. Esto garantiza consistencia del inventario en tiempo real.
 
**Manejo global de errores**
Se implementó un interceptor HTTP en Angular que captura todos los errores de la aplicación y muestra mensajes descriptivos al usuario mediante SweetAlert2, sin necesidad de manejar errores individualmente en cada componente.
 
**Arquitectura por módulos**
El backend sigue una estructura por dominio (hotel, habitacion, disponibilidad, reserva), cada uno con su propio Controller, Service, ServiceImpl, Repository y DTO. Esto facilita el mantenimiento y la escalabilidad del sistema.
 
**Docker Compose con healthcheck**
El servicio de MySQL incluye un healthcheck que garantiza que el backend no intente conectarse hasta que la base de datos esté completamente lista, evitando errores de conexión en el arranque.
 
---
 
## Estructura del repositorio
 
```
prueba-tecnica-fdsa/
├── Backend/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── Frontend/
│   ├── src/
│   ├── Dockerfile
│   └── nginx.conf
├── docker-compose.yml
├── init.sql
└── README.md
```
 
---
 
## Autor
 
Santiago Barquero Torres — Prueba Técnica Full Stack FDSA
