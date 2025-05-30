# 🎬 WsCineUNA

**WsCineUNA** es un sistema backend desarrollado en **Jakarta EE / Java EE** para gestionar operaciones de un cine universitario.  
Incluye manejo de películas, salas, tandas (funciones), asientos, usuarios, facturación y alimentos, con endpoints RESTful y lógica empresarial organizada en controladores, servicios y modelos DTO.

---

## 🚀 Tecnologías

- Java (Jakarta EE)  
- JAX-RS (RESTful services)  
- EJB (Enterprise Java Beans)  
- JPA (Java Persistence API)  
- JasperReports (para generación de reportes PDF)  
- Maven  
- Base de datos: MySQL
- Servidor: Payara / GlassFish 

---

## 📁 Estructura del proyecto

```
src/
 └── main/
      ├── java/cr/ac/una/wscineuna/
      │    ├── controller/
      │    ├── model/
      │    ├── service/
      │    ├── util/
      │    └── resources/
      └── resources/
```

---

## ⚙️ Configuración del entorno

1️⃣ **Clonar el repositorio**

```bash
git clone https://github.com/CristhoferVindas/WsCineUNA.git
cd WsCineUNA
```

2️⃣ **Importar en tu IDE**

- Usa NetBeans o IntelliJ con soporte Jakarta EE.  
- Asegúrate de tener configurado el servidor Payara / GlassFish.

3️⃣ **Configurar base de datos**

En `persistence.xml` (usualmente en `META-INF/`), define:

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/cineuna"/>
<property name="javax.persistence.jdbc.user" value="tu_usuario"/>
<property name="javax.persistence.jdbc.password" value="tu_password"/>
```

4️⃣ **Construir el proyecto**

```bash
mvn clean install
```

5️⃣ **Desplegar en el servidor**

Sube el `.war` generado a Payara / GlassFish.

---

## 📚 Endpoints principales

| Recurso    | Endpoint base    | Descripción                      |
|------------|------------------|----------------------------------|
| Películas  | `/pelicula`      | CRUD de películas                |
| Salas      | `/sala`          | Gestión de salas                 |
| Tandas     | `/tanda`         | Horarios/frecuencia de funciones |
| Asientos   | `/asiento`       | Control de asientos              |
| Usuarios   | `/usuario`       | Gestión de usuarios              |
| Facturas   | `/factura`       | Facturación y reportes           |
| Alimentos  | `/alimento`      | Productos de confitería          |

---

## 🛡️ Seguridad

El proyecto incluye:
- `SecurityFilter.java` → filtro para seguridad en los endpoints.  
- Autenticación básica o con tokens

---

## 🧪 Reportes

Se usa **JasperReports** (`Factura.jasper`, `Factura.jrxml`) para generar facturas en PDF como parte de la lógica de facturación.

---

## 📦 Compilación y despliegue

1️⃣ **Compilar**

```bash
mvn clean package
```

2️⃣ **Desplegar**

El `.war` generado en `target/` puede subirse al servidor Payara / GlassFish.

---

## ✨ Autor

- **Cristhofer Vindas**  
  GitHub: [CristhoferVindas](https://github.com/CristhoferVindas)

---
