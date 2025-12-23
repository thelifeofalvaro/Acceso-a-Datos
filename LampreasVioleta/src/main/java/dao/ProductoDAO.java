package dao;

import db.Db;
import model.Cliente;
import model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad Producto.
 * Encapsula todo el acceso JDBC a la tabla producto.
 */
public class ProductoDAO {

    // ==========================================================
    // SENTENCIAS SQL PREPARADAS COMO CONSTANTES PARA CRUD
    // ==========================================================

    private static final String INSERT_SQL =
            "INSERT INTO producto (id, nombre, precio) VALUES (?, ?, ?)";

    private static final String SELECT_BY_ID_SQL =
            "SELECT id, nombre, precio FROM producto WHERE id = ?";

    private static final String SELECT_ALL_SQL =
            "SELECT id, nombre, precio FROM producto ORDER BY id";

    private static final String SEARCH_SQL = """
            SELECT id, nombre, precio
            FROM producto
            WHERE CAST (id AS TEXT) ILIKE ?
                OR nombre ILIKE ?
                OR CAST (precio AS TEXT) ILIKE ?
            ORDER BY id
            """;

    private static final String UPDATE_SQL =
            "UPDATE producto SET nombre= ?, precio = ? WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM producto WHERE id = ?";

    private static final String CLEAR_SQL =
            "DELETE FROM producto";


    // ==========================================================
    // CRUD COMPLETO
    // ==========================================================

    // Insertar cliente (CREATE)

    public void insert(Producto p) throws SQLException {
        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getPrecio());

            ps.executeUpdate();
        }
    }

    //Buscar Producto por ID (READ)

    public Producto findById(int id) throws SQLException {
        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_BY_ID_SQL)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }
        }
    }

    //Buscar TODOS los productos (READ)

    public List<Producto> findAll() throws SQLException {
        List<Producto> out = new ArrayList<>();

        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                out.add(mapRow(rs));
            }
        }

        return out;
    }

    //Actualizar Producto (UPDATE)

    public void update(Producto p) throws SQLException {

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {

            ps.setInt(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getPrecio());

            ps.executeUpdate();
        }
    }

    //Eliminar Producto por ID (DELETE)

    public void delete(int id) throws SQLException {

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // EXTRA: Borrar datos tabla Producto

    public void clear() throws SQLException {

        try (Connection con = Db.getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(CLEAR_SQL);
        }
    }

    // ==========================================================
    // MAPEADOR
    // ==========================================================

    private Producto mapRow(ResultSet rs) throws SQLException {
        return new Producto(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("precio")
        );
    }
}
