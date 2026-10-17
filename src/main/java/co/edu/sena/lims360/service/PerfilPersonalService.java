package co.edu.sena.lims360.service;

import co.edu.sena.lims360.dao.PerfilPersonalDao;
import co.edu.sena.lims360.model.PerfilPersonal;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Aplica validaciones basicas antes de acceder a la capa de persistencia.
 */
public class PerfilPersonalService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private final PerfilPersonalDao perfilDao;

    public PerfilPersonalService(PerfilPersonalDao perfilDao) {
        this.perfilDao = perfilDao;
    }

    public PerfilPersonal registrar(PerfilPersonal perfil) throws SQLException {
        validar(perfil);
        return perfilDao.insertar(perfil);
    }

    public List<PerfilPersonal> listar() throws SQLException {
        return perfilDao.consultarTodos();
    }

    public Optional<PerfilPersonal> buscarPorId(String id) throws SQLException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El identificador es obligatorio.");
        }
        return perfilDao.consultarPorId(id.trim());
    }

    public boolean actualizar(PerfilPersonal perfil) throws SQLException {
        if (perfil.getId() == null || perfil.getId().isBlank()) {
            throw new IllegalArgumentException("El identificador es obligatorio para actualizar.");
        }
        validar(perfil);
        return perfilDao.actualizar(perfil);
    }

    public boolean eliminar(String id) throws SQLException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El identificador es obligatorio para eliminar.");
        }
        return perfilDao.eliminar(id.trim());
    }

    private void validar(PerfilPersonal perfil) {
        if (perfil == null) {
            throw new IllegalArgumentException("El perfil no puede ser nulo.");
        }
        if (perfil.getNombreCompleto() == null || perfil.getNombreCompleto().isBlank()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio.");
        }
        if (perfil.getCorreo() == null || !EMAIL_PATTERN.matcher(perfil.getCorreo()).matches()) {
            throw new IllegalArgumentException("El correo no tiene un formato valido.");
        }
        if (perfil.getNombreCompleto().length() > 150) {
            throw new IllegalArgumentException("El nombre completo supera 150 caracteres.");
        }
        if (perfil.getCorreo().length() > 120) {
            throw new IllegalArgumentException("El correo supera 120 caracteres.");
        }
    }
}
