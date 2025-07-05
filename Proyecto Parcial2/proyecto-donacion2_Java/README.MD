Abre esta URL en tu navegador o Postman:
http://localhost:8080/api/receptores

Esto depende de que ya tengas implementado el controlador de la entidad Receptor

Probar la consola H2
Puedes entrar a:
http://localhost:8081/h2-console

JDBC URL: jdbc:h2:mem:testdb

Usuario: sa

Contraseña: (déjalo en blanco)

Esto te muestra la base de datos en memoria actual del proyecto (puedes inspeccionar tablas, ver datos, etc.).


vamos a probar la entidad 2 
POST http://localhost:8080/api/solicitudes

GET http://localhost:8080/api/solicitudes

GET http://localhost:8080/api/solicitudes/1