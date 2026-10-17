# Versionamiento del proyecto

## Flujo recomendado

```bash
git init
git add .
git commit -m "feat(personal): implementa CRUD JDBC de perfiles"
git branch -M main
git remote add origin URL_DEL_REPOSITORIO
git push -u origin main
```

## Verificaciones

```bash
git status
git remote -v
git log --oneline
```

## Archivos que no deben publicarse

- Credenciales reales de base de datos.
- Archivos `.env`.
- Configuraciones locales con contrasenas.
- Directorios compilados (`target/`, `out/`).

Antes de publicar, si la contrasena de MySQL no esta vacia, cree un archivo local separado y no lo incluya en el repositorio.
