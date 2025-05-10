
# TP4 - Sistema de Biblioteca

Este proyecto es un sistema de gestión de biblioteca desarrollado en Java utilizando Spring Boot. Permite manejar libros, usuarios y préstamos a través de una API REST.

## ✍️ Autor

Bruno Piastrellini – Programación II – UM 2025
Legajo: 62083

## 📦 Tecnologías utilizadas

- Java 17
- Spring Boot 3.1.2
- Maven
- JUnit 5
- Mockito
- Lombok

---

## ▶️ Cómo ejecutar el proyecto

1. Asegurate de tener Java 17 y Maven instalados.
2. Ejecutá el siguiente comando en consola:

```bash
mvn spring-boot:run
```

---

## ✅ Cómo ejecutar los tests

```bash
mvn test
```

También podés correr tests específicos:

```bash
mvn -Dtest=NombreDelTest test
```

Por ejemplo:

```bash
mvn -Dtest=LibroServiceImplTest test
```

---

## 🌐 Endpoints disponibles

### 📚 Libros

| Método | Endpoint          | Descripción               |
|--------|-------------------|---------------------------|
| GET    | `/api/libros`     | Obtener todos los libros  |
| POST   | `/api/libros`     | Guardar un nuevo libro    |

### 👤 Usuarios

| Método | Endpoint            | Descripción                  |
|--------|---------------------|------------------------------|
| GET    | `/api/usuarios`     | Obtener todos los usuarios   |
| POST   | `/api/usuarios`     | Guardar un nuevo usuario     |

### 🔄 Préstamos

| Método | Endpoint             | Descripción                   |
|--------|----------------------|-------------------------------|
| GET    | `/api/prestamos`     | Obtener todos los préstamos   |
| POST   | `/api/prestamos`     | Registrar un nuevo préstamo   |

---

## 🧪 Cobertura de tests

- Servicios testeados: ✅ `LibroServiceImpl`, `UsuarioServiceImpl`, `PrestamoServiceImpl`
- Controladores testeados: ✅ `LibroController`, `UsuarioController`, `PrestamoController`

---

## 💻 Repositorio

Este proyecto cumple con:

- Estructura de ramas (`main`, `develop`, `wip-tests-*`)
- Pull Requests por etapa
- Issues por funcionalidad
- Uso de Milestones y Project Board
- Tests automatizados

---
