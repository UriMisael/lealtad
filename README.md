Proyecto de Lealtad

Este proyecto es un backend para gestionar un programa de lealtad mediante servicios REST. Permite a los usuarios acumular puntos, consultar su saldo y canjearlos por recompensas. Está desarrollado con Java 17 y Spring Boot.
Características

    Registro de usuarios con nombre de usuario único y contraseña.
    Acumulación de puntos por acciones definidas.
    Consulta de saldo de puntos acumulados.
    Canje de puntos por recompensas.
    Autenticación y autorización con Spring Security.
    Persistencia de datos con MySQL y Spring Data JPA.
    Despliegue como contenedor Docker.

Requisitos

    Java 17
    Maven para la gestión de dependencias.
    MySQL como base de datos relacional.
    Docker (opcional para despliegue).

Configuración
Base de Datos

Crea una base de datos en MySQL llamada lealtad_db y configura las credenciales en el archivo application.properties:

properties: spring.datasource.url=jdbc:mysql://localhost:3306/lealtad_db?useSSL=false&serverTimezone=UTC spring.datasource.username=tu_usuario spring.datasource.password=tu_contraseña.
Dependencias

El proyecto utiliza las siguientes dependencias principales:

Spring Boot Starter Web: Para crear APIs REST. Spring Boot Starter Data JPA: Para interactuar con la base de datos. Spring Boot Starter Security: Para autenticación y autorización. Lombok: Para reducir el código boilerplate. MySQL Connector: Para conectarse a la base de datos MySQL.
Ejecución

Localmente

    Clona el repositorio: git clone <URL_DEL_REPOSITORIO> cd lealtad
    Compila y ejecuta el proyecto: mvn spring-boot:run.
    Accede a la aplicación en http://localhost:8080.

Con Docker

    Construye la imagen Docker: docker build -t lealtad-app.
    Ejecuta el contenedor: docker-compose up.
    Accede a la aplicación en http://localhost:8080.

Endpionts

Usuarios:

POST /api/usuarios/registro: Registra un nuevo usuario. Body: { "nombreUsuario": "testuser", "contraseña": "password" }

Puntos POST /api/puntos/acumular: Acumula puntos para un usuario.

Parámetros: nombreUsuario, cantidad. GET /api/puntos/saldo: Consulta el saldo de puntos de un usuario.

Parámetros: nombreUsuario. Recompensas GET /api/recompensas: Lista todas las recompensas disponibles.

POST /api/recompensas/canjear: Canjea una recompensa.

Parámetros: nombreUsuario, recompensaId. Pruebas El proyecto incluye pruebas unitarias para los servicios y controladores. Para ejecutarlas: mvn test
Despliegue

El proyecto está preparado para ser desplegado como contenedor Docker. La imagen puede ser publicada en un registro de contenedores como Docker Hub para facilitar su descarga y ejecución.
Licencia
Licencia Este proyecto está bajo la licencia MIT.
