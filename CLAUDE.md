# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 4.1.0 web application built with Maven, implementing a simple REST API for managing "Alumnos" (students). The application uses a layered architecture with controllers, services, repositories, and models.

**Technology Stack:**
- Java 17
- Spring Boot 4.1.0
- Maven (with ./mvnw wrapper)
- Lombok for annotations
- Spring Boot DevTools for development

## Architecture

The codebase follows a standard layered architecture pattern:

- **Controller Layer** (`controller/`): REST endpoints that handle HTTP requests
  - `AlumnoController`: Exposes `/alumnos` endpoints (GET all, GET by ID)
  - `HolaController`: Basic "Hello" endpoint

- **Service Layer** (`service/`): Business logic interface and implementation
  - `AlumnoService`: Interface defining student operations
  - `AlumnoServiceImpl`: Implementation with dependency injection of repository

- **Repository Layer** (`repository/`): Data access logic
  - `AlumnoRepository`: Currently returns mock data; ready to be connected to a real database

- **Model Layer** (`model/`): Domain entities
  - `Alumno`: Student entity with `id` and `nombre` (name) fields

The architecture uses constructor injection for dependencies and Spring's stereotype annotations (`@RestController`, `@Service`, `@Repository`).

## Common Commands

### Build and Run
```bash
# Build the project
./mvnw clean package

# Run the application (starts on default port 8080)
./mvnw spring-boot:run

# Run in development mode (with auto-reload via DevTools)
./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8080"
```

### Testing
```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=SpringbootApplicationTests

# Run a specific test method
./mvnw test -Dtest=SpringbootApplicationTests#testName
```

### Linting and Analysis
```bash
# Compile only (catches syntax errors)
./mvnw compile

# Run Maven validation
./mvnw validate
```

### Development
```bash
# Clean build artifacts
./mvnw clean

# Install dependencies only
./mvnw dependency:resolve

# Build without running tests
./mvnw package -DskipTests
```

## API Endpoints

- `GET /alumnos` - Retrieve all students
- `GET /alumnos?id={id}` - Retrieve a specific student by ID

Current test data includes two students: "Juan Pérez" (ID: 1) and "María Gómez" (ID: 2).

## Key Notes for Development

1. **Mock Data**: The `AlumnoRepository` currently returns hardcoded test data. When integrating with a real database, implement proper persistence logic here without changing the service/controller layers.

2. **Lombok**: The project includes Lombok but the model classes use explicit getters/setters. You can refactor using `@Getter` and `@Setter` if needed.

3. **DevTools**: Active by default, enables live reload during development. No manual server restart needed when source files change.

4. **Package Structure**: Follow the `com.idat.springboot.*` namespace for new classes.

5. **Exception Handling**: Currently minimal; the `getAlumnoById` endpoint returns `null` if not found. Consider adding proper error handling and HTTP status codes as needed.

## Project Structure
```
src/
├── main/
│   ├── java/com/idat/springboot/
│   │   ├── controller/        # REST endpoints
│   │   ├── service/           # Business logic
│   │   ├── repository/        # Data access
│   │   ├── model/             # Domain entities
│   │   └── SpringbootApplication.java
│   └── resources/
│       ├── application.properties
│       ├── static/            # Static files (CSS, JS, images)
│       └── templates/         # Thymeleaf templates (if used)
└── test/
    └── java/com/idat/springboot/
        └── SpringbootApplicationTests.java
```
