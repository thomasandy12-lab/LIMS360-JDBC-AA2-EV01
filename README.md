# GA7-220501096-AA2-EV01 - Codificacion de modulos del software

## Proyecto
**LIMS 360 Personal** - modulo de gestion de personal con persistencia JDBC en MySQL.

El proyecto implementa las cuatro operaciones CRUD solicitadas:

- **Insercion:** registrar una persona en `profiles`.
- **Consulta:** listar perfiles y buscar uno por identificador.
- **Actualizacion:** modificar nombre, correo, area, cargo y estado.
- **Eliminacion:** retirar un perfil por identificador.

## Estructura

```text
src/main/java/co/edu/sena/lims360/
├── app/        Punto de entrada y menu de consola
├── config/     Conexion JDBC
├── dao/        Contrato de persistencia
├── dao/impl/   Implementacion JDBC
├── model/      Modelo de dominio
└── service/    Validaciones y logica del modulo
src/main/resources/
└── database.properties
database/
├── schema.sql
└── datos_demo.sql
docs/
├── MAPA_ARTEFACTOS.md
├── PRUEBAS_CRUD.md
└── VERSIONAMIENTO.md
```

## Requisitos

- JDK 17 o superior.
- MySQL Server 8.x.
- Maven 3.9 o superior, o un IDE con soporte Maven.
- Acceso a MySQL desde `localhost:3306`.

## Preparacion de la base de datos

1. Abra MySQL Workbench.
2. Ejecute `database/schema.sql`.
3. Opcionalmente ejecute `database/datos_demo.sql`.
4. Ajuste `src/main/resources/database.properties` con el usuario y contrasena de MySQL.

## Ejecucion

Desde la raiz del proyecto:

```bash
mvn clean compile
mvn exec:java
```

El menu permite crear, consultar, actualizar y eliminar perfiles.

## JDBC

La clase `DatabaseConfig` abre conexiones mediante `DriverManager`. La implementacion `PerfilPersonalJdbcDao` utiliza `PreparedStatement`, `ResultSet` y bloques `try-with-resources` para cerrar correctamente las conexiones y recursos SQL.

## Estandares de codificacion

- Paquetes: minusculas (`co.edu.sena.lims360.dao`).
- Clases e interfaces: PascalCase (`PerfilPersonalJdbcDao`).
- Variables y metodos: camelCase (`nombreCompleto`, `consultarPorId`).
- Constantes SQL: MAYUSCULAS_CON_GUION_BAJO (`SQL_INSERTAR`).
- Responsabilidades separadas por capas: modelo, DAO, servicio, configuracion y aplicacion.
- Sentencias SQL parametrizadas mediante `PreparedStatement`.

## Versionamiento

El proyecto incluye `.gitignore` y una guia en `docs/VERSIONAMIENTO.md`. Antes de la entrega debe quedar publicado en el repositorio indicado en `ENLACE_REPOSITORIO.txt`.
