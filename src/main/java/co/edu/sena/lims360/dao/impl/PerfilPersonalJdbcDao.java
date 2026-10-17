package co.edu.sena.lims360.dao.impl;

import co.edu.sena.lims360.config.DatabaseConfig;
import co.edu.sena.lims360.dao.PerfilPersonalDao;
import co.edu.sena.lims360.model.PerfilPersonal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementacion JDBC de las operaciones CRUD sobre la tabla profiles.
 */
public class PerfilPersonalJdbcDao implements PerfilPersonalDao {

    private static final String SQL_INSERTAR = """
            INSERT INTO profiles (id, user_id, full_name, email, department, position, is_active)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String SQL_CONSULTAR_TODOS = """
            SELECT id, user_id, full_name, email, department, position, is_active, created_at
            FROM profiles
            ORDER BY full_name
            """;

    private static final String SQL_CONSULTAR_POR_ID = """
            SELECT id, user_id, full_name, email, department, position, is_active, created_at
            FROM profiles
            WHERE id = ?
            """;

    private static final String SQL_ACTUALIZAR = """
            UPDATE profiles
            SET full_name = ?, email = ?, department = ?, position = ?, is_active = ?
            WHERE id = ?
            """;

    private static final String SQL_ELIMINAR = "DELETE FROM profiles WHERE id = ?";

    @Override
    public PerfilPersonal insertar(PerfilPersonal perfil) throws SQLException {
        if (perfil.getId() == null || perfil.getId().isBlank()) {
            perfil.setId(UUID.randomUUID().toString());
        }
        if (perfil.getUserId() == null || perfil.getUserId().isBlank()) {
            perfil.setUserId(UUID.randomUUID().toString());
        }

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERTAR)) {

            statement.setString(1, perfil.getId());
            statement.setString(2, perfil.getUserId());
            statement.setString(3, perfil.getNombreCompleto());
            statement.setString(4, perfil.getCorreo());
            statement.setString(5, perfil.getArea());
            statement.setString(6, perfil.getCargo());
            statement.setBoolean(7, perfil.isActivo());
            statement.executeUpdate();
        }

        return consultarPorId(perfil.getId())
                .orElseThrow(() -> new SQLException("No fue posible consultar el perfil creado."));
    }

    @Override
    public List<PerfilPersonal> consultarTodos() throws SQLException {
        List<PerfilPersonal> perfiles = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CONSULTAR_TODOS);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                perfiles.add(mapearPerfil(resultSet));
            }
        }

        return perfiles;
    }

    @Override
    public Optional<PerfilPersonal> consultarPorId(String id) throws SQLException {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CONSULTAR_POR_ID)) {

            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapearPerfil(resultSet));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean actualizar(PerfilPersonal perfil) throws SQLException {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ACTUALIZAR)) {

            statement.setString(1, perfil.getNombreCompleto());
            statement.setString(2, perfil.getCorreo());
            statement.setString(3, perfil.getArea());
            statement.setString(4, perfil.getCargo());
            statement.setBoolean(5, perfil.isActivo());
            statement.setString(6, perfil.getId());

            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public boolean eliminar(String id) throws SQLException {
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ELIMINAR)) {

            statement.setString(1, id);
            return statement.executeUpdate() == 1;
        }
    }

    private PerfilPersonal mapearPerfil(ResultSet resultSet) throws SQLException {
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return new PerfilPersonal(
                resultSet.getString("id"),
                resultSet.getString("user_id"),
                resultSet.getString("full_name"),
                resultSet.getString("email"),
                resultSet.getString("department"),
                resultSet.getString("position"),
                resultSet.getBoolean("is_active"),
                createdAt == null ? null : createdAt.toLocalDateTime()
        );
    }
}
