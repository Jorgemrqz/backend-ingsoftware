#  Sistema de Gestión Educativa - Spring Boot

Este proyecto es un sistema backend desarrollado en Spring Boot para la gestión integral de una institución educativa. El sistema abarca funcionalidades como gestión de estudiantes, docentes, asignaturas, matrículas, horarios, calificaciones, ingresos/egresos y emisión de comprobantes.

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
  - spring-boot-devtools
- H2 Database (modo de prueba)
- Lombok
- Maven
- SpringDoc OpenAPI (Swagger UI)

## 📁 Estructura del proyecto

```
src/main/java/com/tuempresa/sistemaeducativo/
│
├── model/               # Entidades JPA del sistema
│   └── Usuario.java, Estudiante.java, Clase.java, etc.
│
├── repository/           # Interfaces JpaRepository
│   └── UsuarioRepository.java, EstudianteRepository.java, etc.
│
├── service/              # Lógica de negocio
│   └── UsuarioService.java, EstudianteService.java, etc.
│
├── controller/           # Controladores REST (API)
│   └── UsuarioController.java, EstudianteController.java, etc.
│
└── SistemaEducativoApplication.java   # Clase principal
```

## 🧩 Módulos del sistema
Este backend implementa todos los módulos funcionales requeridos para una institución educativa. Cada módulo está representado por las entidades JPA correspondientes, sus repositorios, servicios y controladores REST. Aunque no están organizados como paquetes separados, su estructura sigue un esquema modelo/repositorio/servicio/controlador que permite implementar la lógica de negocio fácilmente.

1. Configuración:
   - Usuarios y perfiles de acceso
   - Parámetros generales
   - Plan de cuentas contables

2. Inscripciones:
   - Registro y consulta de estudiantes

3. Docentes:
   - Registro y consulta de docentes

4. Planes de estudio:
   - Asignaturas por nivel

5. Oferta de grupos:
   - Grupos, asignación de docentes, espacios físicos, horarios

6. Matrículas:
   - Matrícula de estudiantes, asignación a clases, rubros de cobro

7. Calificaciones:
   - Registro de calificaciones por clase/asignatura

8. Ingresos y Egresos:
   - Comprobantes, transacciones, diario de caja

9. Certificados:
   - Servicio web de calificaciones por estudiante y período

10. Reportes:
   - Récord académico, libro diario, horario de clases, resumen de comprobantes

## ⚙️ Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Jorgemrqz/backend-ingsoftware.git
   cd backend-ingsoftware
   ```

2. Ejecuta la aplicación:
     ```bash
     mvn spring-boot:run
     ```

3. Accede a la base de datos en memoria H2:
   - URL: http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Usuario: `sa`
   - Contraseña: (en blanco)
  
Nota: aunque las funcionalidades aún no están implementadas, el modelo de datos está completamente diseñado para soportarlas.

## 📘 Documentación de la API (Swagger UI)

El proyecto incluye documentación automática con OpenAPI/Swagger gracias a la dependencia de SpringDoc.

Una vez la aplicación esté corriendo, puedes acceder a la interfaz Swagger UI en:

🔗 [[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html#/)] 

Desde ahí se podrá explorar y probar todos los endpoints REST del sistema.

## 🧠 Notas

- Se aplica el esquema Modelo → Repositorio → Servicio → Controlador para cumplir con las buenas prácticas de diseño.
- Las entidades están generadas a partir de un modelo UML, y cada una tiene su JpaRepository.
- Se usa Lombok para reducir código boilerplate como getters/setters y constructores.

## 📌 ToDo / Mejoras futuras

- Agregar autenticación y seguridad con Spring Security
- Integración con base de datos real (MySQL/PostgreSQL)
- Implementación de DTOs para separación de modelos

## 🧑‍💻 Autor

Desarrollado por Jorge Márquez, Roberto Romero, Erika Villa.
