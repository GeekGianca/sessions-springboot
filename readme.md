# Proyecto: Session
 https://sessions-checker-d507000f8e10.herokuapp.com
## Descripción
Este proyecto sigue la arquitectura Clean Architecture en Java con Spring Boot, asegurando una separación clara de responsabilidades y facilitando la mantenibilidad y escalabilidad del software.

## Estructura del Proyecto

```
session
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.example.session
│   │   │   │   ├── app
│   │   │   │   │   ├── dto        # Clases de transferencia de datos
│   │   │   │   │   ├── mapper     # Mappeo entre entidades y DTOs
│   │   │   │   │   ├── service    # Lógica de negocio y servicios
│   │   │   │   │   ├── usecase    # Casos de uso de la aplicación
│   │   │   │   ├── core
│   │   │   │   │   ├── domain     # Entidades de negocio
│   │   │   │   │   ├── model      # Modelos de datos
│   │   │   │   │   ├── usecase    # Interfaz de los casos de uso
│   │   │   │   ├── infrastructure
│   │   │   │   │   ├── config     # Configuraciones generales del proyecto
│   │   │   │   │   ├── security   # Configuración de seguridad y autenticación
│   │   │   │   ├── presentation.controller
│   │   │   │   │   ├── AuthController  # Controlador de autenticación
│   │   │   │   │   ├── HomeController  # Controlador principal
│   │   │   │   ├── SessionApplication  # Clase principal de arranque
│   ├── resources
│   │   ├── static       # Archivos estáticos
│   │   ├── templates    # Plantillas (si se usa un motor de plantillas)
│   │   ├── application.properties  # Configuraciones de la aplicación
│   ├── test             # Pruebas unitarias y de integración
```

## Explicación de los Módulos

### 1. **app**
Este paquete contiene la lógica de aplicación y la implementación de los casos de uso:
- **dto**: Clases para la transferencia de datos entre capas.
- **mapper**: Conversión entre entidades de dominio y DTOs.
- **service**: Implementaciones de los servicios de negocio.
- **usecase**: Implementaciones de los casos de uso.

### 2. **core**
Aquí se definen las abstracciones y las reglas de negocio puras:
- **domain**: Entidades de dominio.
- **model**: Modelos de datos usados internamente.
- **usecase**: Interfaces de los casos de uso que serán implementadas en `app.usecase`.

### 3. **infrastructure**
Contiene las configuraciones y aspectos técnicos de la aplicación:
- **config**: Configuraciones generales.
- **security**: Configuraciones de seguridad y autenticación.

### 4. **presentation.controller**
Contiene los controladores que manejan las peticiones HTTP:
- **AuthController**: Controlador de autenticación.
- **HomeController**: Controlador de la página principal.

### 5. **resources**
- **static**: Archivos estáticos como CSS o JavaScript.
- **templates**: Plantillas HTML thymeleaf.
- **application.properties**: Archivo de configuración de la aplicación.

## Tecnologías Utilizadas
- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **Hibernate / JPA**
- **Maven**

## Instalación y Ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/GeekGianca/sessions-springboot
   cd session
   ```
2. Configurar el archivo `application.properties` según la base de datos y necesidades del proyecto.
3. Si desea correrlo localmente debe configurar los parametros en el `application.properties` de la siguiente manera.
   ```bash
    spring.datasource.url=jdbc:mysql://localhost/bdName?createDatabaseIfNotExist=true
    spring.datasource.username=username
    spring.datasource.password=securePassword
   ```
3. Construir y ejecutar el proyecto con Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Contribuciones
Cualquier contribución es bienvenida. Por favor, sigue las normas de desarrollo establecidas en el repositorio.

---
Este README proporciona una explicación clara de la estructura del proyecto y cómo iniciar el desarrollo. ¡Espero que te sea útil!

