package dao;

import db.Db;
import model.Pedido;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad Pedido.
 * Relación N:1 con Cliente.
 */
public class PedidoDAO {

    // ===============================
    // SQL
    // ===============================

    private static final String INSERT_SQL =
            "INSERT INTO pedido (id, cliente_id, repartidorid, fecha) VALUES (?, ?, ?, ?)";

    private static final String SELECT_BY_ID_SQL =
            "SELECT id, cliente_id, repartidorid, fecha FROM pedido WHERE id = ?";

    private static final String SELECT_ALL_SQL =
            "SELECT id, cliente_id, repartidorid, fecha FROM pedido ORDER BY id";

    private static final String UPDATE_SQL =
            "UPDATE pedido SET cliente_id = ?, repartidorid = ?, fecha = ? WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM pedido WHERE id = ?";

    private static final String CLEAR_SQL =
            "DELETE FROM pedido";

    // ==========================================================
    // CRUD COMPLETO
    // ==========================================================

    // Insertar pedido (CREATE)
    public void insert(Pedido p) throws SQLException {
        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_SQL)) {

            pst.setInt(1, p.getId());
            pst.setInt(2, p.getClienteId());

            if (p.getRepartidorId() != null) {
                pst.setInt(3, p.getRepartidorId());
            } else {
                pst.setNull(3, Types.INTEGER);
            }

            pst.setDate(4, Date.valueOf(p.getFecha()));

            pst.executeUpdate();
        }
    }

    //Buscar pedido por ID (READ)

    public Pedido findById(int id) throws SQLException {
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

    //Buscar TODOS los clientes (READ)

    public List<Pedido> findAll() throws SQLException {
        List<Pedido> out = new ArrayList<>();

        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                out.add(mapRow(rs));
            }
        }

        return out;
    }

    //Actualizar pedido (UPDATE)

    public void update(Pedido p) throws SQLException {
        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_SQL)) {

            pst.setInt(1, p.getClienteId());

            if (p.getRepartidorId() != null) {
                pst.setInt(2, p.getRepartidorId());
            } else {
                pst.setNull(2, Types.INTEGER);
            }

            pst.setDate(3, Date.valueOf(p.getFecha()));
            pst.setInt(4, p.getId());

            pst.executeUpdate();
        }
    }

    //Eliminar pedido por ID (DELETE)

    public void delete(int id) throws SQLException {
        try (Connection con = Db.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_SQL)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    // EXTRA: Borrar datos tabla Pedidos

    public void clear() throws SQLException {
        try (Connection con = Db.getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(CLEAR_SQL);
        }
    }

    // ===============================
    // MAPEADOR (Actualizado con FK)
    // ===============================

    private Pedido mapRow(ResultSet rs) throws SQLException {

        Pedido p = new Pedido(
                rs.getInt("id"),
                rs.getInt("cliente_id"),
                rs.getDate("fecha").toLocalDate()
        );

        int repartidorId = rs.getInt("repartidorid");
        if (!rs.wasNull()) {
            p.setRepartidorId(repartidorId);
        }

        return p;
    }
}
