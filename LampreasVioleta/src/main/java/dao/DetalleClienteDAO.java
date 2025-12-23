package dao;

import db.Db;
import model.DetalleCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleClienteDAO {

    // ==========================================================
    // SENTENCIAS SQL PREPARADAS COMO CONSTANTES PARA CRUD
    // ==========================================================

    private static final String INSERT_SQL = """
            INSERT INTO detalle_cliente (id, direccion, telefono, notas)
            VALUES (?, ?, ?, ?)
            """;

    private static final String SELECT_BY_ID_SQL = """
            SELECT id, direccion, telefono, notas
            FROM detalle_cliente
            WHERE id = ?
            """;

    private static final String SELECT_ALL_SQL = """
            SELECT id, direccion, telefono, notas
            FROM detalle_cliente
            ORDER BY id
            """;

    private static final String UPDATE_SQL = """
            UPDATE detalle_cliente
            SET direccion = ?, telefono = ?, notas = ?
            WHERE id = ?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM detalle_cliente
            WHERE id = ?
            """;

    private static final String CLEAR_SQL =
            "DELETE FROM detalle_cliente";

    // ==========================================================
    // CRUD COMPLETO
    // ==========================================================

    // Insertar cliente (CREATE). el id debe coincidir con cliente existente

    public void insert(DetalleCliente d) throws SQLException {
        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_SQL)) {

            pst.setInt(1, d.getId());
            pst.setString(2, d.getDireccion());
            String tel = d.getTelefono();
            if (tel == null || tel.isBlank()) {
                pst.setNull(3, Types.VARCHAR);   // ← fuerza NULL → rompe NOT NULL
            } else {
                pst.setString(3, tel.trim());
            }
            pst.setString(4, d.getNotas());

            pst.executeUpdate();
        }
    }

    public void insert(DetalleCliente d, Connection con) throws SQLException {

        String sql = "INSERT INTO detalle_cliente (id, direccion, telefono, notas) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getId());
            ps.setString(2, d.getDireccion());

            String tel = d.getTelefono();
            if (tel == null || tel.isBlank()) {
                ps.setNull(3, Types.VARCHAR);   // ← fuerza NULL → rompe NOT NULL
            } else {
                ps.setString(3, tel.trim());
            }

            ps.setString(4, d.getNotas());

            ps.executeUpdate();
        }
    }


    //Buscar DetalleCliente por ID de Cliente PK (READ). Devuelve null si no existe

    public DetalleCliente findById(int id) throws SQLException {
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

    // Busca todos los DetalleCliente (READ)

    public List<DetalleCliente> findAll() throws SQLException {
        List<DetalleCliente> out = new ArrayList<>();

        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                out.add(mapRow(rs));
            }
        }

        return out;
    }


    // Actualiza DetalleCliente (UPDATE) si existe, si no, devuelve 0

    public int update(DetalleCliente d) throws SQLException {
        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_SQL)) {

            pst.setString(1, d.getDireccion());
            pst.setString(2, d.getTelefono());
            pst.setString(3, d.getNotas());
            pst.setInt(4, d.getId());

            return pst.executeUpdate(); // número de filas afectadas
        }
    }

    //Eliminar DetalleCliente por ID de Cliente (DELETE)

    public int deleteById(int id) throws SQLException {
        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_SQL)) {

            pst.setInt(1, id);
            return pst.executeUpdate();
        }
    }

    // EXTRA: Borrar datos tabla DetallePedido

    public void clear() throws SQLException {

        try (Connection con = Db.getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(CLEAR_SQL);
        }
    }



    // =========================================================================
    //  MAPEO ResultSet → DetalleCliente (buenas prácticas)
    // =========================================================================

    /**
     * Convierte una fila de ResultSet en un objeto DetalleCliente.
     */
    private DetalleCliente mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String direccion = rs.getString("direccion");
        String telefono = rs.getString("telefono");
        String notas = rs.getString("notas");

        return new DetalleCliente(id, direccion, telefono, notas);
    }
}
