package co.edu.sena.lims360.dao;

import co.edu.sena.lims360.model.PerfilPersonal;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Contrato CRUD del modulo de gestion de personal.
 */
public interface PerfilPersonalDao {

    PerfilPersonal insertar(PerfilPersonal perfil) throws SQLException;

    List<PerfilPersonal> consultarTodos() throws SQLException;

    Optional<PerfilPersonal> consultarPorId(String id) throws SQLException;

    boolean actualizar(PerfilPersonal perfil) throws SQLException;

    boolean eliminar(String id) throws SQLException;
}
