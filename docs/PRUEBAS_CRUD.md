# Pruebas funcionales propuestas

## 1. Insercion
1. Ejecutar la aplicacion.
2. Elegir `1. Registrar persona`.
3. Diligenciar nombre, correo, area y cargo.
4. Verificar en MySQL:

```sql
SELECT * FROM profiles ORDER BY created_at DESC;
```

## 2. Consulta
1. Elegir `2. Consultar todas las personas`.
2. Confirmar que se listan los registros de `profiles`.
3. Elegir `3. Consultar persona por ID` y usar un identificador existente.

## 3. Actualizacion
1. Elegir `4. Actualizar persona`.
2. Ingresar el ID existente.
3. Cambiar al menos un campo.
4. Verificar en MySQL:

```sql
SELECT id, full_name, email, department, position, is_active
FROM profiles
WHERE id = 'ID_A_CONSULTAR';
```

## 4. Eliminacion
1. Elegir `5. Eliminar persona`.
2. Ingresar el ID.
3. Verificar que el registro ya no existe:

```sql
SELECT * FROM profiles WHERE id = 'ID_ELIMINADO';
```

## Resultado esperado

Las cuatro operaciones deben ejecutarse sin errores y reflejar inmediatamente los cambios en la tabla `profiles`.
