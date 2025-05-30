# 🎬 WsCineUNA - Sistema de Gestión de Cine Universitario

**WsCineUNA** es un sistema backend completo desarrollado en **Jakarta EE** para la gestión integral de operaciones de un cine universitario. Proporciona APIs RESTful para el manejo de películas, salas, horarios, asientos, usuarios, facturación y servicios de confitería.

## 🚀 Tecnologías Principales

- **Jakarta EE** - Plataforma empresarial
- **JAX-RS** - Servicios REST con serialización JSON
- **EJB** - Lógica de negocio con beans de sesión
- **JPA/EclipseLink** - Persistencia de datos con MySQL
- **JasperReports** - Generación de reportes PDF
- **JWT** - Autenticación basada en tokens
- **Maven** - Gestión de dependencias y construcción

## 📋 Funcionalidades del Sistema

### Gestión de Contenido
- **Catálogo de Películas**: Soporte multiidioma (Español/Inglés) con imágenes
- **Administración de Salas**: Configuración de teatros con mapas de asientos visuales
- **Programación de Horarios**: Sistema de tandas para funciones de películas

### Operaciones de Negocio
- **Sistema de Reservas**: Seguimiento en tiempo real de disponibilidad de asientos
- **Gestión de Usuarios**: Registro, autenticación y perfiles de clientes
- **Punto de Venta**: Generación de facturas con integración de confitería
- **Servicios de Alimentos**: Inventario y ventas de productos

### Reportes y Análisis
- **Reportes de Asistencia**: Análisis por película y función
- **Facturación**: Generación automática de PDFs
- **Reportes de Catálogo**: Estadísticas de películas

## 🏗️ Arquitectura del Sistema

### Controladores REST Principales

| Controlador | Endpoint Base | Funcionalidad |
|-------------|---------------|---------------|
| `PeliculaController` | `/pelicula` | CRUD de películas y reportes |
| `SalaController` | `/sala` | Gestión de salas y asientos |
| `TandaController` | `/tanda` | Programación de horarios |
| `UsuarioController` | `/usuario` | Autenticación y perfiles |
| `FacturaController` | `/factura` | Facturación y reportes |
| `AlimentoController` | `/alimento` | Gestión de confitería |

### Servicios EJB
Cada controlador delega la lógica de negocio a servicios EJB especializados que manejan las operaciones de base de datos y reglas de negocio.

## 🔧 Configuración e Instalación

### Prerrequisitos
- Java 11+
- Payara/GlassFish Server
- MySQL 8.0+
- Maven 3.6+

### Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/CristhoferVindas/WsCineUNA.git
cd WsCineUNA
```

2. **Configurar base de datos**
Editar `persistence.xml`:
```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/cineuna"/>
<property name="javax.persistence.jdbc.user" value="tu_usuario"/>
<property name="javax.persistence.jdbc.password" value="tu_password"/>
```

3. **Construir el proyecto**
```bash
mvn clean package
```

4. **Desplegar en servidor**
Subir el archivo `.war` generado a Payara/GlassFish

## 🛡️ Seguridad

El sistema implementa:
- **Filtro de Seguridad**: Control de acceso a endpoints
- **Autenticación JWT**: Tokens seguros para sesiones
- **Validación de Roles**: Control de acceso basado en permisos

## 📊 Integración de Reportes

Utiliza **JasperReports** para generar:
- Facturas en PDF
- Reportes de asistencia por película
- Análisis de ventas de confitería
- Estadísticas del catálogo

## 🗂️ Estructura del Proyecto

```
src/main/java/cr/ac/una/wscineuna/
├── controller/     # Controladores JAX-RS
├── model/          # Entidades JPA y DTOs
├── service/        # Servicios EJB de negocio
├── util/           # Clases auxiliares y utilidades
└── resources/      # Configuración JAX-RS
```

## 📡 Ejemplos de API

### Obtener película por ID
```http
GET /ws/pelicula/peliculas/{id}
Authorization: Bearer {jwt_token}
```

### Crear nueva sala
```http
POST /ws/sala
Content-Type: application/json
Authorization: Bearer {jwt_token}

{
  "nombre": "Sala Premium",
  "detalle": "Sala con sonido Dolby",
  "imagenAsiento": "base64_image_data"
}
```

## 👨‍💻 Desarrollo

### Entorno de Desarrollo
- **IDE Recomendado**: NetBeans con soporte Jakarta EE
- **Servidor de Desarrollo**: Payara/GlassFish
- **Base de Datos**: MySQL con datasource `jdbcCine`

### Comandos Útiles
```bash
# Compilar proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Generar WAR
mvn package

# Limpiar y reconstruir
mvn clean install
```

## 📄 Licencia

Este proyecto es desarrollado para fines académicos en la Universidad Nacional de Costa Rica.

## ✨ Autor

**Cristhofer Vindas**  
GitHub: [CristhoferVindas](https://github.com/CristhoferVindas)


