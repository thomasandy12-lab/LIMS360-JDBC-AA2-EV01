# Relacion con artefactos previos de LIMS 360 Personal

| Artefacto previo | Aplicacion en este modulo |
|---|---|
| Modelo de clases | El concepto de Usuario/Perfil se representa mediante `PerfilPersonal`. |
| Casos de uso e historias de usuario | Se cubren las acciones de registrar persona, consultar perfil, modificar informacion y eliminar registros. |
| Prototipo de la vista Personal | Los datos principales del listado se reflejan en nombre, correo, area, cargo y estado. |
| Modelo de base de datos | Se utiliza la tabla `profiles` de la base `lims360`. |
| Estandares de codificacion | Paquetes en minusculas, clases PascalCase y variables/metodos camelCase. |
| Plan de versionamiento | Se incluye `.gitignore` y estructura preparada para Git/GitHub. |

## Alcance del modulo

La evidencia se concentra en la persistencia del modulo de gestion de personal. Las demas entidades del sistema (roles, rutas de aprendizaje, modulos, actividades, progreso, evidencias, notificaciones y auditoria) quedan fuera del CRUD implementado en esta entrega.
