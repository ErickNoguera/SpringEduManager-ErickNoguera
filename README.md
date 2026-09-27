# SpringEduManager

Aplicación web de gestión académica desarrollada como proyecto de evaluación del Módulo 6 (Desarrollo de aplicaciones JEE con Spring Framework) del bootcamp de Java en Talento Digital.

Permite gestionar estudiantes y cursos mediante una interfaz web (Spring MVC + Thymeleaf), con persistencia en base de datos vía JPA, control de acceso por roles con Spring Security, y una API REST para exponer los datos a otros sistemas.

## Tecnologías utilizadas

- Spring Boot 4.1.1
- Spring MVC (Model-View-Controller)
- Spring Data JPA + Hibernate
- Spring Security (autenticación y autorización por roles)
- Thymeleaf (motor de plantillas)
- MySQL como base de datos
- Maven como gestor de dependencias

## Funcionalidades

- Gestión de estudiantes: listar, crear
- Gestión de cursos: listar, crear
- Autenticación con 2 roles: ADMIN y USER
  - ADMIN: puede crear estudiantes y cursos
  - USER: solo puede consultar las listas
- API REST con operaciones CRUD (Create, Read, Update, Delete) completas para estudiantes y cursos, expuesta en `/api/estudiantes` y `/api/cursos`

## Estructura del proyecto

    src/main/java/com/erick/springedumanagererick/
    ├── config/       → Configuración de Spring Security (SecurityConfig)
    ├── controller/   → Controladores MVC (EstudianteController, CursoController) y REST (EstudianteRestController, CursoRestController)
    ├── model/        → Entidades JPA (Estudiante, Curso)
    └── repository/   → Repositorios Spring Data JPA (EstudianteRepository, CursoRepository)

    src/main/resources/
    ├── templates/    → Vistas Thymeleaf (estudiantes.html, estudiante-form.html, cursos.html, curso-form.html)
    └── application.properties → Configuración de conexión a MySQL

## Base de datos

Nombre: `db_springedumanager`

Las tablas (`estudiante`, `curso`) se generan automáticamente al arrancar la aplicación, gracias a `spring.jpa.hibernate.ddl-auto=update` — no requiere ningún script SQL manual.

## Usuarios de prueba

| Usuario | Contraseña | Rol   | Permisos                              |
|---------|-----------|-------|----------------------------------------|
| admin   | admin1234 | ADMIN | Crear estudiantes y cursos, ver listas |
| erick   | erick1234 | USER  | Solo ver listas (sin crear)            |

## Cómo ejecutar el proyecto

### Requisitos previos
- JDK 21 o superior
- MySQL Server
- Eclipse IDE (o cualquier IDE compatible con Maven)

### Pasos

1. Clonar el repositorio:

git clone https://github.com/ErickNoguera/SpringEduManager-ErickNoguera.git


2. Crear la base de datos en MySQL:
```sql
   CREATE DATABASE IF NOT EXISTS db_springedumanager;
```

3. Ajustar las credenciales de conexión en `src/main/resources/application.properties` si es necesario (usuario/contraseña de tu MySQL local).

4. Importar el proyecto en Eclipse como "Existing Maven Project".

5. Ejecutar la clase principal `SpringedumanagernogueraApplication` como Java Application.

6. Acceder desde el navegador a: `http://localhost:8080/estudiantes` (Spring Security pedirá login).

### Probar la API REST

Los endpoints están disponibles sin autenticación para facilitar las pruebas con Postman o similar:

- `GET /api/estudiantes` — listar todos los estudiantes
- `GET /api/estudiantes/{id}` — buscar un estudiante por ID
- `POST /api/estudiantes` — crear un estudiante (cuerpo JSON)
- `PUT /api/estudiantes/{id}` — actualizar un estudiante
- `DELETE /api/estudiantes/{id}` — eliminar un estudiante

(Los mismos 5 endpoints existen para `/api/cursos`)

## Ciclo de vida Maven verificado

- ./mvnw clean
- ./mvnw install
- ./mvnw package


## Autor

Erick Noguera — [GitHub](https://github.com/ErickNoguera)
