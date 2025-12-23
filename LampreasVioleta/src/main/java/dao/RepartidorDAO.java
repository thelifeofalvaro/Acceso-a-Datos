package dao;

import db.Db;
import model.Repartidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    // ==========================================================
    // SENTENCIAS SQL PREPARADAS COMO CONSTANTES PARA CRUD
    // ==========================================================

    private static final String INSERT_SQL =
            "INSERT INTO repartidor (id, nombre, telefono, zona) VALUES (?, ?, ?, ?)";

    private static final String SELECT_BY_ID_SQL =
            "SELECT id, nombre, telefono, zona FROM repartidor WHERE id = ?";

    private static final String SELECT_ALL_SQL =
            "SELECT id, nombre, telefono, zona FROM  repartidor ORDER BY id";

    private static final String SEARCH_SQL = """
                        SELECT id, nombre, telefono, zona
                        FROM comercial
                        WHERE CAST(id AS TEXT) ILIKE ? 
                            OR nombre ILIKE ?  
                            OR telefono ILIKE ?
                            OR zona ILIKE ?
                        ORDER BY id                    
                        """;

    private static final String UPDATE_SQL =
            "UPDATE repartidor SET nombre= ?, telefono = ?, zona = ? WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM repartidor WHERE id = ?";

    private static final String CLEAR_SQL =
            "DELETE FROM repartidor";


    // ==========================================================
    // CRUD COMPLETO
    // ==========================================================

    // Insertar Repartidor (CREATE)

    public void insert(Repartidor r) throws SQLException {

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, r.getId());         // Parámetro 1 → columna id
            ps.setString(2, r.getNombre());  // Parámetro 2 → columna nombre
            ps.setString(3, r.getTelefono());   // Parámetro 3 → columna telefono
            ps.setString(4, r.getZona());   // Parámetro 4 → columna zona

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    r.setId(idGenerado);  // lo guardamos en el objeto
                }
            }

        }
    }

    //Buscar REPARTIDOR por ID (READ)

    public Repartidor findById(int id) throws SQLException {
        // Devuelve el Comercial con el ID insertado y si no existe, devuelve null.

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {

            ps.setInt(1, id);  // Asignamos el id al parámetro ?

            try (ResultSet rs = ps.executeQuery()) {
                // executeQuery() devuelve un ResultSet ↔ una tabla virtual con las filas devueltas.

                if (rs.next()) {
                    // Si rs.next() = true → hay fila. Avanzamos a ella y leemos sus columnas.

                    return new Repartidor(
                            rs.getInt("id"),          // Columna 'id'
                            rs.getString("nombre"),   // Columna 'nombre'
                            rs.getString("telefono"),   // Columna 'telefono'
                            rs.getString("zona")     // Columna 'zona'
                    );
                }

                return null;
                // Si no existe devolvemos null para indicar "no encontrado".
            }
        }
    }

    //Buscar TODOS los Repartidores (READ)

    public List<Repartidor> findAll() throws SQLException {
        // Devuelve una lista con todos los comerciales de la tabla.
        // Nunca devuelve null; si no hay datos, devuelve lista vacía.

        List<Repartidor> out = new ArrayList<>();

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Iteramos por cada fila del ResultSet.
                // Cada fila se convierte en un objeto Comercial.

                Repartidor r = new Repartidor(
                        rs.getInt("id"),          // Columna 'id'
                        rs.getString("nombre"),   // Columna 'nombre'
                        rs.getString("telefono"),   // Columna 'telefono'
                        rs.getString("zona")     // Columna 'zona'
                );

                out.add(r);   // Añadimos el comercial a la lista.
            }
        }

        return out;   // Devolvemos la lista completa.
    }

    //Actualizar Repartidor (UPDATE)

    public void update(Repartidor r) throws SQLException {

        try (Connection con = Db.getConnection();
            PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {

            ps.setInt(1, r.getId());
            ps.setString(2, r.getNombre());
            ps.setString(3, r.getTelefono());
            ps.setString(4, r.getZona());

            ps.executeUpdate();
        }
    }

    //Eliminar Repartidor por ID (DELETE)

    public void delete(int id) throws SQLException {

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // EXTRA: Borrar datos tabla Repartidor

    public void clear() throws SQLException {

        try (Connection con = Db.getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(CLEAR_SQL);
        }
    }

    // ==========================================================
    // MAPEADOR
    // ==========================================================

    private Repartidor mapRow(ResultSet rs) throws SQLException {

        Repartidor r = new Repartidor(
                rs.getInt("id"),          // Columna 'id'
                rs.getString("nombre"),   // Columna 'nombre'
                rs.getString("telefono"),   // Columna 'telefono'
                rs.getString("zona")     // Columna 'zona'
        );

        return r;
    }

}
