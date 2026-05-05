CREATE TYPE rol_usuario AS ENUM (
  'ciudadano',
  'inspector',
  'responsable_area',
  'administrador'
);

CREATE TYPE estado_incidencia AS ENUM (
  'pendiente',
  'en_revision',
  'en_proceso',
  'resuelto',
  'rechazado',
  'cerrado'
);

CREATE TYPE nivel_prioridad AS ENUM (
  'baja',
  'media',
  'alta',
  'critica'
);

CREATE TYPE tipo_archivo AS ENUM (
  'foto',
  'video',
  'audio'
);

CREATE TYPE tipo_notificacion AS ENUM (
  'cambio_estado',
  'caso_acumulativo',
  'asignacion',
  'resolucion',
  'alerta',
  'push'
);

CREATE TYPE estado_chat AS ENUM (
  'activo',
  'completado',
  'abandonado'
);

CREATE TYPE remitente_chat AS ENUM (
  'usuario',
  'asistente'
);

CREATE TYPE tipo_mensaje AS ENUM (
  'texto',
  'opcion',
  'confirmacion',
  'ubicacion',
  'multimedia'
);

CREATE TYPE plataforma_movil AS ENUM (
  'ios'
);

CREATE TABLE roles (
  id          SERIAL       PRIMARY KEY,
  nombre      VARCHAR(50)  NOT NULL UNIQUE,
  descripcion TEXT,
  creado_en   TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE usuarios (
  id              SERIAL       PRIMARY KEY,
  nombre          VARCHAR(120) NOT NULL,
  correo          VARCHAR(200) NOT NULL UNIQUE,
  telefono        VARCHAR(20),
  contrasena_hash VARCHAR(255) NOT NULL,
  rol             rol_usuario  NOT NULL DEFAULT 'ciudadano',
  area_id         INTEGER,
  activo          BOOLEAN      NOT NULL DEFAULT TRUE,
  foto_url        TEXT,
  creado_en       TIMESTAMP    NOT NULL DEFAULT NOW(),
  actualizado_en  TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_usuarios_correo ON usuarios(correo);
CREATE INDEX idx_usuarios_rol    ON usuarios(rol);

CREATE TABLE tokens_dispositivo (
  id             SERIAL           PRIMARY KEY,
  usuario_id     INTEGER          NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
  token          TEXT             NOT NULL UNIQUE,
  plataforma     plataforma_movil NOT NULL,
  activo         BOOLEAN          NOT NULL DEFAULT TRUE,
  creado_en      TIMESTAMP        NOT NULL DEFAULT NOW(),
  actualizado_en TIMESTAMP        NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_dispositivo_usuario ON tokens_dispositivo(usuario_id);
CREATE INDEX idx_dispositivo_token   ON tokens_dispositivo(token);

CREATE TABLE categorias (
  id          SERIAL      PRIMARY KEY,
  nombre      VARCHAR(80) NOT NULL UNIQUE,
  descripcion TEXT,
  icono       VARCHAR(50),
  color       VARCHAR(10),
  activo      BOOLEAN     NOT NULL DEFAULT TRUE,
  creado_en   TIMESTAMP   NOT NULL DEFAULT NOW()
);

CREATE TABLE areas_municipales (
  id             SERIAL       PRIMARY KEY,
  nombre         VARCHAR(120) NOT NULL UNIQUE,
  descripcion    TEXT,
  distritos      TEXT[],
  responsable_id INTEGER      REFERENCES usuarios(id) ON DELETE SET NULL,
  activo         BOOLEAN      NOT NULL DEFAULT TRUE,
  creado_en      TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_areas_responsable ON areas_municipales(responsable_id);

ALTER TABLE usuarios
  ADD CONSTRAINT fk_usuarios_area
  FOREIGN KEY (area_id) REFERENCES areas_municipales(id) ON DELETE SET NULL;

CREATE TABLE configuracion_usuario (
  id                     SERIAL      PRIMARY KEY,
  usuario_id             INTEGER     NOT NULL UNIQUE REFERENCES usuarios(id) ON DELETE CASCADE,
  notif_cambio_estado    BOOLEAN     NOT NULL DEFAULT TRUE,
  notif_caso_acumulativo BOOLEAN     NOT NULL DEFAULT TRUE,
  notif_resolucion       BOOLEAN     NOT NULL DEFAULT TRUE,
  notif_asignacion       BOOLEAN     NOT NULL DEFAULT TRUE,
  ubicacion_automatica   BOOLEAN     NOT NULL DEFAULT TRUE,
  idioma                 VARCHAR(10) NOT NULL DEFAULT 'es',
  tema                   VARCHAR(10) NOT NULL DEFAULT 'oscuro',
  perfil_publico         BOOLEAN     NOT NULL DEFAULT FALSE,
  actualizado_en         TIMESTAMP   NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_config_usuario ON configuracion_usuario(usuario_id);

CREATE TABLE ubicaciones (
  id         SERIAL        PRIMARY KEY,
  latitud    NUMERIC(10,7) NOT NULL,
  longitud   NUMERIC(10,7) NOT NULL,
  direccion  VARCHAR(255),
  referencia VARCHAR(255),
  distrito   VARCHAR(80),
  provincia  VARCHAR(80),
  ciudad     VARCHAR(80)   NOT NULL DEFAULT 'Lima',
  pais       VARCHAR(60)   NOT NULL DEFAULT 'Perú',
  creado_en  TIMESTAMP     NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_ubicaciones_coords   ON ubicaciones(latitud, longitud);
CREATE INDEX idx_ubicaciones_distrito ON ubicaciones(distrito);

CREATE TABLE casos (
  id             SERIAL            PRIMARY KEY,
  categoria_id   INTEGER           NOT NULL REFERENCES categorias(id),
  ubicacion_id   INTEGER           REFERENCES ubicaciones(id),
  area_id        INTEGER           REFERENCES areas_municipales(id),
  titulo         VARCHAR(200)      NOT NULL,
  descripcion    TEXT,
  estado         estado_incidencia NOT NULL DEFAULT 'pendiente',
  prioridad      nivel_prioridad   NOT NULL DEFAULT 'media',
  total_reportes INTEGER           NOT NULL DEFAULT 1,
  radio_metros   INTEGER           NOT NULL DEFAULT 200,
  creado_en      TIMESTAMP         NOT NULL DEFAULT NOW(),
  actualizado_en TIMESTAMP         NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_casos_estado    ON casos(estado);
CREATE INDEX idx_casos_prioridad ON casos(prioridad);
CREATE INDEX idx_casos_categoria ON casos(categoria_id);
CREATE INDEX idx_casos_area      ON casos(area_id);

CREATE SEQUENCE secuencia_incidencias START 1;

CREATE TABLE incidencias (
  id             SERIAL            PRIMARY KEY,
  codigo         VARCHAR(20)       NOT NULL UNIQUE,
  usuario_id     INTEGER           NOT NULL REFERENCES usuarios(id),
  categoria_id   INTEGER           NOT NULL REFERENCES categorias(id),
  caso_id        INTEGER           REFERENCES casos(id),
  ubicacion_id   INTEGER           NOT NULL REFERENCES ubicaciones(id),
  descripcion    TEXT              NOT NULL,
  estado         estado_incidencia NOT NULL DEFAULT 'pendiente',
  prioridad      nivel_prioridad   NOT NULL DEFAULT 'media',
  creado_en      TIMESTAMP         NOT NULL DEFAULT NOW(),
  actualizado_en TIMESTAMP         NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_incidencias_usuario   ON incidencias(usuario_id);
CREATE INDEX idx_incidencias_caso      ON incidencias(caso_id);
CREATE INDEX idx_incidencias_estado    ON incidencias(estado);
CREATE INDEX idx_incidencias_categoria ON incidencias(categoria_id);
CREATE INDEX idx_incidencias_creado    ON incidencias(creado_en DESC);

CREATE TABLE participantes_caso (
  id         SERIAL    PRIMARY KEY,
  caso_id    INTEGER   NOT NULL REFERENCES casos(id) ON DELETE CASCADE,
  usuario_id INTEGER   NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
  unido_en   TIMESTAMP NOT NULL DEFAULT NOW(),
  UNIQUE (caso_id, usuario_id)
);

CREATE INDEX idx_participantes_caso    ON participantes_caso(caso_id);
CREATE INDEX idx_participantes_usuario ON participantes_caso(usuario_id);

CREATE TABLE archivos_multimedia (
  id             SERIAL       PRIMARY KEY,
  incidencia_id  INTEGER      NOT NULL REFERENCES incidencias(id) ON DELETE CASCADE,
  tipo           tipo_archivo NOT NULL,
  url            TEXT         NOT NULL,
  nombre_archivo VARCHAR(255),
  tamano_bytes   INTEGER,
  duracion_seg   INTEGER,
  miniatura_url  TEXT,
  creado_en      TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_multimedia_incidencia ON archivos_multimedia(incidencia_id);

CREATE TABLE evidencia_resolucion (
  id             SERIAL       PRIMARY KEY,
  caso_id        INTEGER      NOT NULL REFERENCES casos(id) ON DELETE CASCADE,
  inspector_id   INTEGER      NOT NULL REFERENCES usuarios(id),
  tipo           tipo_archivo NOT NULL,
  url            TEXT         NOT NULL,
  nombre_archivo VARCHAR(255),
  tamano_bytes   INTEGER,
  descripcion    TEXT,
  creado_en      TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_evidencia_caso      ON evidencia_resolucion(caso_id);
CREATE INDEX idx_evidencia_inspector ON evidencia_resolucion(inspector_id);

CREATE TABLE historial_estados (
  id              SERIAL            PRIMARY KEY,
  incidencia_id   INTEGER           NOT NULL REFERENCES incidencias(id) ON DELETE CASCADE,
  usuario_id      INTEGER           NOT NULL REFERENCES usuarios(id),
  estado_anterior estado_incidencia,
  estado_nuevo    estado_incidencia NOT NULL,
  comentario      TEXT,
  creado_en       TIMESTAMP         NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_historial_incidencia ON historial_estados(incidencia_id);
CREATE INDEX idx_historial_creado     ON historial_estados(creado_en DESC);

CREATE TABLE asignaciones (
  id               SERIAL    PRIMARY KEY,
  caso_id          INTEGER   NOT NULL REFERENCES casos(id),
  asignado_a       INTEGER   NOT NULL REFERENCES usuarios(id),
  asignado_por     INTEGER   NOT NULL REFERENCES usuarios(id),
  fecha_asignacion TIMESTAMP NOT NULL DEFAULT NOW(),
  fecha_limite     TIMESTAMP,
  fecha_resolucion TIMESTAMP,
  notas            TEXT,
  activo           BOOLEAN   NOT NULL DEFAULT TRUE
);

CREATE INDEX idx_asignaciones_caso     ON asignaciones(caso_id);
CREATE INDEX idx_asignaciones_asignado ON asignaciones(asignado_a);

CREATE TABLE sesiones_chat (
  id             SERIAL      PRIMARY KEY,
  usuario_id     INTEGER     NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
  incidencia_id  INTEGER     REFERENCES incidencias(id) ON DELETE SET NULL,
  estado         estado_chat NOT NULL DEFAULT 'activo',
  paso_actual    VARCHAR(60) NOT NULL DEFAULT 'categoria',
  creado_en      TIMESTAMP   NOT NULL DEFAULT NOW(),
  actualizado_en TIMESTAMP   NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_sesiones_chat_usuario    ON sesiones_chat(usuario_id);
CREATE INDEX idx_sesiones_chat_incidencia ON sesiones_chat(incidencia_id);

CREATE TABLE mensajes_chat (
  id        SERIAL         PRIMARY KEY,
  sesion_id INTEGER        NOT NULL REFERENCES sesiones_chat(id) ON DELETE CASCADE,
  remitente remitente_chat NOT NULL,
  tipo      tipo_mensaje   NOT NULL DEFAULT 'texto',
  contenido TEXT           NOT NULL,
  opciones  JSONB,
  metadatos JSONB,
  creado_en TIMESTAMP      NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_mensajes_sesion ON mensajes_chat(sesion_id);
CREATE INDEX idx_mensajes_creado ON mensajes_chat(creado_en ASC);

CREATE TABLE notificaciones (
  id             SERIAL            PRIMARY KEY,
  usuario_id     INTEGER           NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
  incidencia_id  INTEGER           REFERENCES incidencias(id) ON DELETE SET NULL,
  tipo           tipo_notificacion NOT NULL,
  titulo         VARCHAR(200)      NOT NULL,
  mensaje        TEXT              NOT NULL,
  leida          BOOLEAN           NOT NULL DEFAULT FALSE,
  enviada_push   BOOLEAN           NOT NULL DEFAULT FALSE,
  creado_en      TIMESTAMP         NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_notificaciones_usuario ON notificaciones(usuario_id);
CREATE INDEX idx_notificaciones_leida   ON notificaciones(usuario_id, leida);
CREATE INDEX idx_notificaciones_creado  ON notificaciones(creado_en DESC);
