# Módulo 2 - Donación de Víveres

## Descripción

Este proyecto implementa el backend para la gestión de donaciones de víveres, evolucionando hacia una arquitectura de microservicios distribuida. Incluye:

- Microservicio Java (Spring Boot) para gestión de Receptores, Entregas y Solicitudes.
- API Gateway centralizado (Apollo Server con GraphQL en TypeScript).
- Servicio de Notificaciones en tiempo real (WebSockets).
- Seguridad con JWT.

## Autor

**Jeremy Vera**

## Herramientas y Tecnologías

- Java 17, Spring Boot 3.2.5
- PostgreSQL
- Spring Data JPA, Spring Boot Validation
- JWT, Spring Security
- Node.js, TypeScript (Apollo Server para Gateway GraphQL)
- Python (WebSocket Service)
- Postman (pruebas manuales)

## Instalación

### Backend Java

1. Clona el repositorio:
   ```bash
   git clone <https://github.com/Jordyfr2004/proyecto_servidor_new.git>
   ```
2. Instala dependencias y configura la base de datos en `application.properties`.
3. Ejecuta el proyecto:
   ```bash
   mvn spring-boot:run
   ```

### Gateway GraphQL (Apollo Server)

1. Ve al directorio del gateway:
   ```bash
   cd gateway-apollo
   ```
2. Instala dependencias:
   ```bash
   npm install
   ```
3. Ejecuta el servidor:
   ```bash
   npm start
   ```

### Servicio WebSocket (Python)

1. Ve al directorio del servicio WebSocket:
   ```bash
   cd websocket-service
   ```
2. Instala dependencias:
   ```bash
   pip install -r requirements.txt
   ```
3. Ejecuta el servicio:
   ```bash
   python app.py
   ```

## Rutas y Pruebas

### Rutas Backend Java (Spring Boot)

- **Login (obtener JWT):**
  - POST `http://localhost:8082/api/public/login`
  - Body:
    ```json
    { "username": "admin", "password": "admin123" }
    ```
- **Receptores:**
  - POST `http://localhost:8082/api/receptores` (requiere JWT)
  - Body:
    ```json
    {
      "nombre": "Juan Perez",
      "cedula": "1234567890",
      "telefono": "0987654321",
      "direccion": "Av. Principal 123",
      "correo": "juan.perez@email.com"
    }
    ```
- **Solicitudes:**
  - POST `http://localhost:8082/api/solicitudes` (requiere JWT)
  - Body: según el DTO de Solicitud
- **Entregas:**
  - POST `http://localhost:8082/api/entregas` (requiere JWT)
  - Body: según el DTO de Entrega

### Rutas Gateway Apollo (GraphQL)

- **URL Playground:**
  - `http://localhost:4000/graphql`
- **Ejemplo de consulta:**
  ```graphql
  query {
    receptores {
      idReceptor
      nombre
      cedula
      telefono
      correo
    }
  }
  ```
- **Mutaciones y consultas para todas las entidades disponibles en el schema.**

### Servicio de Notificaciones (WebSocket)

- **Endpoint para notificar:**
  - POST `http://localhost:4000/notificar`
  - Body:
    ```json
    { "mensaje": "Prueba de notificación desde Postman para Receptor" }
    ```
- **Cliente WebSocket:**
  - Abre el archivo `cliente.html` en tu navegador para recibir notificaciones en tiempo real.

## Autenticación en Postman

1. Realiza login en `/api/public/login` y copia el token JWT.
2. En cada petición protegida, ve a la pestaña "Authorization", selecciona "Bearer Token" y pega el token.

## Pruebas de cada entidad

- **Receptor:**
  - Crea un receptor usando la ruta y body indicados arriba.
- **Solicitud:**
  - Crea una solicitud y verifica que se envíe la notificación.
- **Entrega:**
  - Crea una entrega y verifica la notificación.
- **Notificaciones:**
  - Envía un POST a `/notificar` y verifica la recepción en el cliente WebSocket.

## Conclusión

Este proyecto cumple con los requisitos de evolución arquitectónica, integración de microservicios, seguridad y notificaciones en tiempo real. Permite la gestión eficiente de donaciones y la interacción inmediata entre los módulos, facilitando la escalabilidad y el mantenimiento.

## Referencias

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Apollo Server GraphQL](https://www.apollographql.com/docs/apollo-server/)
- [FastAPI WebSockets](https://fastapi.tiangolo.com/advanced/websockets/)
- [JWT Introduction](https://jwt.io/introduction)

---
