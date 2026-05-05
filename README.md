<p align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/PostgreSQL-Supabase-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Deploy-Render-46E3B7?style=for-the-badge&logo=render&logoColor=white" alt="Render" />
  <img src="https://img.shields.io/badge/Docker-Enabled-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker" />
</p>

<h1 align="center">UrbanixApi — Backend REST</h1>

<p align="center">
  API REST para el sistema de gestión de incidencias urbanas Urbanix.<br/>
  Provee los servicios de datos al panel administrativo React.
</p>

<p align="center">
  <strong>URL de producción:</strong><br/>
  <code>https://urbanixbackend.onrender.com/api</code>
</p>

---

## 📋 Descripción

**UrbanixApi** es el backend del sistema Urbanix, desarrollado con **Spring Boot 4** y **Java 17**. Expone una API REST que gestiona incidencias urbanas, casos agrupadores, áreas municipales, usuarios, historial de estados y más.

Está conectado a una base de datos **PostgreSQL** alojada en **Supabase** y desplegado en **Render** mediante Docker.

### Funcionalidades principales

- 📝 **CRUD de incidencias** — Crear, listar, actualizar estado y prioridad
- 📁 **Gestión de casos** — Agrupar incidencias relacionadas por zona
- 🗺️ **Áreas municipales** — Zonas con responsable asignado
- 🏷️ **Categorías** — Clasificación de tipos de incidencia
- 📍 **Ubicaciones** — Geolocalización con coordenadas
- 👥 **Usuarios** — CRUD con roles (ciudadano, inspector, administrador)
- 🔐 **Autenticación** — Login por correo y contraseña
- 📊 **Historial de estados** — Timeline de cambios por incidencia
- 📎 **Archivos multimedia** — Fotos/videos asociados a incidencias
- 🔔 **Notificaciones** — Sistema de alertas por usuario
- 💬 **Chat** — Sesiones de chat con historial de mensajes
- 👮 **Asignaciones** — Inspectores asignados a casos
- 📸 **Evidencias de resolución** — Fotos de resolución por caso

---

## 🛠️ Tech Stack

| Tecnología | Versión | Uso |
|------------|---------|-----|
| [Spring Boot](https://spring.io/projects/spring-boot) | 4.0.5 | Framework backend |
| [Java](https://openjdk.org/) | 17 | Lenguaje principal |
| [Spring Data JPA](https://spring.io/projects/spring-data-jpa) | — | ORM / Acceso a datos |
| [PostgreSQL](https://www.postgresql.org/) | — | Base de datos relacional |
| [Supabase](https://supabase.com/) | — | Hosting de PostgreSQL |
| [Lombok](https://projectlombok.org/) | — | Reducción de boilerplate |
| [Docker](https://www.docker.com/) | — | Contenerización |
| [Maven](https://maven.apache.org/) | — | Gestión de dependencias |

---

## 📁 Estructura del Proyecto

```
UrbanixApi/
├── src/main/java/com/cibertec/
│   ├── UrbanixApiApplication.java    # Clase principal Spring Boot
│   │
│   ├── config/
│   │   └── WebConfig.java            # Configuración CORS global
│   │
│   ├── controller/                   # Controladores REST (17)
│   │   ├── IncidenciaController      # /api/incidencias
│   │   ├── CasoController            # /api/casos
│   │   ├── AreaMunicipalController    # /api/areas
│   │   ├── CategoriaController       # /api/categorias
│   │   ├── UbicacionController       # /api/ubicaciones
│   │   ├── UsuarioController         # /api/usuarios (+ login)
│   │   ├── HistorialEstadoController # /api/historial
│   │   ├── ArchivoMultimediaController # /api/archivos
│   │   ├── AsignacionController      # /api/asignaciones
│   │   ├── EvidenciaResolucionController # /api/evidencias-resolucion
│   │   ├── NotificacionController    # /api/notificaciones
│   │   ├── ChatController            # /api/chat
│   │   ├── MensajeController         # /api/mensajes
│   │   ├── ParticipanteCasoController # /api/participantes-casos
│   │   ├── ConfiguracionUsuarioController # /api/configuraciones
│   │   ├── RolController             # /api/roles
│   │   └── TokenDispositivoController # /api/tokens
│   │
│   ├── model/                        # Entidades JPA (17)
│   │   ├── Incidencia                # Reporte ciudadano
│   │   ├── Caso                      # Agrupador de incidencias
│   │   ├── AreaMunicipal             # Zona municipal
│   │   ├── Categoria                 # Tipo de incidencia
│   │   ├── Ubicacion                 # Coordenadas geográficas
│   │   ├── Usuario                   # Cuenta de usuario
│   │   ├── Roles                     # Roles del sistema
│   │   ├── HistorialEstado           # Cambio de estado
│   │   ├── ArchivoMultimedia         # Foto/video adjunto
│   │   ├── Asignacion               # Inspector → Caso
│   │   ├── EvidenciaResolucion       # Foto de resolución
│   │   ├── Notificacion             # Alerta de usuario
│   │   ├── SesionChat               # Sesión de chat
│   │   ├── MensajeChat              # Mensaje individual
│   │   ├── ParticipanteCaso          # Participante de caso
│   │   ├── ConfiguracionUsuario     # Preferencias (tema, idioma)
│   │   └── TokenDispositivo         # Token push notification
│   │
│   ├── repository/                   # Repositorios JPA (17)
│   ├── service/                      # Capa de servicios (17)
│   └── enums/
│       └── Modelos_enum.java         # Enums del sistema
│
├── src/main/resources/
│   └── application.properties        # Config de BD y servidor
│
├── Dockerfile                        # Build multi-stage para Render
├── pom.xml                           # Dependencias Maven
└── mvnw / mvnw.cmd                   # Maven Wrapper
```

---

## 🗄️ Modelo de Datos

```
┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│   usuarios   │     │  categorias  │     │  ubicaciones  │
│──────────────│     │──────────────│     │──────────────│
│ id           │     │ id           │     │ id           │
│ nombre       │     │ nombre       │     │ latitud      │
│ correo       │     │ descripcion  │     │ longitud     │
│ contrasena   │     │ icono        │     │ direccion    │
│ rol (enum)   │     │ color        │     │ distrito     │
│ area_id (FK) │     │ activo       │     │ ciudad       │
│ activo       │     └──────┬───────┘     └──────┬───────┘
│ foto_url     │            │                    │
└──────┬───────┘            │                    │
       │                    │                    │
       │         ┌──────────┴────────────────────┘
       │         │          │
       ▼         ▼          ▼
┌──────────────────────────────────┐
│          incidencias             │
│──────────────────────────────────│
│ id, codigo, descripcion          │
│ usuario_id (FK) → usuarios       │
│ categoria_id (FK) → categorias   │
│ ubicacion_id (FK) → ubicaciones  │
│ caso_id (FK) → casos             │
│ estado (enum), prioridad (enum)  │
│ creado_en                        │
└───────────────┬──────────────────┘
                │
       ┌────────┴────────┐
       ▼                 ▼
┌──────────────┐  ┌──────────────────┐
│    casos     │  │ historial_estados│
│──────────────│  │──────────────────│
│ id, titulo   │  │ estado_anterior  │
│ categoria_id │  │ estado_nuevo     │
│ area_id (FK) │  │ comentario       │
│ ubicacion_id │  │ incidencia_id    │
│ estado       │  │ usuario_id       │
│ prioridad    │  └──────────────────┘
└──────────────┘
```

### Enums del sistema

| Enum | Valores |
|------|---------|
| `RolUsuario` | `ciudadano`, `inspector`, `responsable_area`, `administrador` |
| `EstadoIncidencia` | `pendiente`, `en_revision`, `en_proceso`, `resuelto`, `rechazado`, `cerrado` |
| `NivelPrioridad` | `baja`, `media`, `alta`, `critica` |
| `TipoArchivo` | `foto`, `video`, `audio` |
| `TipoNotificacion` | `cambio_estado`, `caso_acumulativo`, `asignacion`, `resolucion`, `alerta`, `push` |

---

## 📡 API Reference

Todos los endpoints están bajo el prefijo `/api`.

### Incidencias — `/api/incidencias`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/incidencias` | Listar todas las incidencias |
| `GET` | `/api/incidencias/{id}` | Obtener incidencia por ID |
| `POST` | `/api/incidencias` | Crear nueva incidencia |
| `PATCH` | `/api/incidencias/{id}` | Actualizar estado/prioridad/descripción |
| `GET` | `/api/incidencias/usuario/{id}` | Listar por usuario |
| `GET` | `/api/incidencias/caso/{id}` | Listar por caso |

### Casos — `/api/casos`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/casos` | Listar todos los casos |
| `POST` | `/api/casos` | Crear nuevo caso |
| `PATCH` | `/api/casos/{id}` | Actualizar caso |
| `DELETE` | `/api/casos/{id}` | Eliminar caso |

### Áreas Municipales — `/api/areas`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/areas` | Listar todas las áreas |
| `POST` | `/api/areas` | Crear nueva área |
| `PATCH` | `/api/areas/{id}` | Actualizar área |

### Categorías — `/api/categorias`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/categorias` | Listar todas |
| `GET` | `/api/categorias/activas` | Listar solo activas |
| `POST` | `/api/categorias` | Crear categoría |
| `PATCH` | `/api/categorias/{id}` | Actualizar categoría |
| `DELETE` | `/api/categorias/{id}` | Eliminar categoría |

### Ubicaciones — `/api/ubicaciones`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/ubicaciones` | Listar todas |
| `GET` | `/api/ubicaciones/{id}` | Obtener por ID |
| `POST` | `/api/ubicaciones` | Crear ubicación |
| `PATCH` | `/api/ubicaciones/{id}` | Actualizar ubicación |
| `DELETE` | `/api/ubicaciones/{id}` | Eliminar ubicación |

### Usuarios — `/api/usuarios`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/usuarios` | Listar todos |
| `GET` | `/api/usuarios/id/{id}` | Obtener por ID |
| `GET` | `/api/usuarios/correo/{correo}` | Obtener por correo |
| `GET` | `/api/usuarios/login?correo=X&clave=Y` | Autenticación |
| `POST` | `/api/usuarios` | Crear usuario |
| `PATCH` | `/api/usuarios/{id}` | Actualizar usuario |
| `DELETE` | `/api/usuarios/{id}` | Eliminar usuario |

### Historial de Estados — `/api/historial`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/historial/incidencia/{id}` | Ver historial de incidencia |
| `POST` | `/api/historial` | Registrar cambio de estado |

### Archivos Multimedia — `/api/archivos`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/archivos/incidencia/{id}` | Listar archivos de incidencia |
| `POST` | `/api/archivos` | Subir archivo |
| `DELETE` | `/api/archivos/{id}` | Eliminar archivo |

### Asignaciones — `/api/asignaciones`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/asignaciones` | Asignar inspector a caso |
| `GET` | `/api/asignaciones/inspector/{id}` | Listar por inspector |
| `GET` | `/api/asignaciones/caso/{id}` | Listar por caso |

### Evidencias de Resolución — `/api/evidencias-resolucion`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/evidencias-resolucion` | Guardar evidencia |
| `GET` | `/api/evidencias-resolucion/caso/{id}` | Listar por caso |

### Notificaciones — `/api/notificaciones`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/notificaciones/usuario/{id}` | Listar por usuario |
| `PATCH` | `/api/notificaciones/{id}/leer` | Marcar como leída |
| `GET` | `/api/notificaciones/usuario/{id}/conteo` | Contar no leídas |

### Chat — `/api/chat`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/chat/sesion/activa/{userId}` | Obtener sesión activa |
| `POST` | `/api/chat/sesion` | Iniciar sesión de chat |
| `POST` | `/api/chat/mensaje` | Enviar mensaje |
| `GET` | `/api/chat/historial/{sesionId}` | Ver historial de chat |

### Otros endpoints

| Recurso | Método | Endpoint | Descripción |
|---------|--------|----------|-------------|
| Roles | `GET` | `/api/roles` | Listar roles |
| Mensajes | `POST` | `/api/mensajes` | Enviar mensaje |
| Mensajes | `GET` | `/api/mensajes/sesion/{id}` | Historial por sesión |
| Participantes | `POST` | `/api/participantes-casos/unirse` | Unirse a caso |
| Participantes | `GET` | `/api/participantes-casos/caso/{id}` | Listar por caso |
| Configuración | `GET` | `/api/configuraciones/usuario/{id}` | Obtener config |
| Configuración | `PATCH` | `/api/configuraciones/{id}` | Actualizar config |
| Tokens | `POST` | `/api/tokens` | Registrar token push |
| Tokens | `DELETE` | `/api/tokens/usuario/{id}` | Eliminar tokens |

---

## 🚀 Instalación y Ejecución Local

### Requisitos previos

- [Java 17](https://adoptium.net/) (JDK)
- [Maven](https://maven.apache.org/) (o usar el wrapper `mvnw` incluido)
- [PostgreSQL](https://www.postgresql.org/) (local o remoto)

### Pasos

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/Jeanfranco10/UrbanixApi.git
   cd UrbanixApi
   ```

2. **Configurar las variables de entorno**

   Crear las siguientes variables de entorno con los datos de tu base de datos PostgreSQL:

   ```bash
   # Linux/Mac
   export DB_URL=jdbc:postgresql://localhost:5432/urbanix
   export DB_USER=postgres
   export DB_PASSWORD=tu_contraseña
   ```

   ```powershell
   # Windows PowerShell
   $env:DB_URL="jdbc:postgresql://localhost:5432/urbanix"
   $env:DB_USER="postgres"
   $env:DB_PASSWORD="tu_contraseña"
   ```

3. **Ejecutar la aplicación**
   ```bash
   # Con Maven Wrapper (no requiere Maven instalado)
   ./mvnw spring-boot:run

   # O con Maven instalado
   mvn spring-boot:run
   ```

4. **Verificar que funciona**
   ```
   http://localhost:8080/api/categorias
   ```

### Build de producción

```bash
./mvnw clean package -DskipTests
java -jar target/UrbanixApi-0.0.1-SNAPSHOT.jar
```

---

## 🐳 Docker

El proyecto incluye un `Dockerfile` con build multi-stage:

```bash
# Construir imagen
docker build -t urbanix-api .

# Ejecutar contenedor
docker run -p 8080:8080 \
  -e DB_URL=jdbc:postgresql://host:5432/urbanix \
  -e DB_USER=postgres \
  -e DB_PASSWORD=tu_contraseña \
  urbanix-api
```

---

## 🔧 Variables de Entorno

| Variable | Descripción | Requerida |
|----------|-------------|-----------|
| `DB_URL` | URL de conexión JDBC a PostgreSQL | ✅ Sí |
| `DB_USER` | Usuario de la base de datos | ✅ Sí |
| `DB_PASSWORD` | Contraseña de la base de datos | ✅ Sí |
| `PORT` | Puerto del servidor (default: 8080) | ❌ No |

---

## ☁️ Despliegue

El proyecto está desplegado en **Render** como Web Service con Docker.

**URL de producción:** `https://urbanixbackend.onrender.com/api`

### Configuración en Render

- **Environment:** Docker
- **Build Command:** Automático desde Dockerfile
- **Variables de entorno:** `DB_URL`, `DB_USER`, `DB_PASSWORD` configuradas en el dashboard de Render

> **Nota:** En el plan gratuito de Render, la instancia se duerme tras 15 minutos de inactividad. La primera petición puede tardar ~30 segundos en responder mientras se reactiva.

---

## 🔗 Conexión con el Frontend

Este backend sirve datos al panel administrativo React desplegado en Netlify:

| | Detalle |
|---|---|
| **Frontend** | [https://urbanix-panel.netlify.app/](https://urbanix-panel.netlify.app/) |
| **Repositorio** | [UrbanixPanel](https://github.com/Jeanfranco10/UrbanixPanel) |
| **Tech** | React 19 + Vite 8 |

### Arquitectura del sistema

```
┌─────────────────────┐         ┌─────────────────────┐         ┌──────────────┐
│     Frontend         │  HTTP   │      Backend         │  JDBC   │  PostgreSQL  │
│  React + Vite        │ ──────► │  Spring Boot 4       │ ──────► │  Supabase    │
│  Netlify             │  REST   │  Render (Docker)     │         │              │
└─────────────────────┘         └─────────────────────┘         └──────────────┘
```

---

## ✍️ Autor

**Jean Franco** — Desarrollo backend, diseño de API y modelado de base de datos.

---

## 📄 Licencia

Este es un proyecto personal. Todos los derechos reservados.
