package dao;


// Clase DAO con la logica de acceso a datos de la entidad Comercial

import db.Db;
import model.Comercial;
import model.Producto;
import model.Repartidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComercialDAO {

    // ==========================================================
    // SENTENCIAS SQL PREPARADAS COMO CONSTANTES PARA CRUD
    // ==========================================================

    private static final String INSERT_SQL =
            "INSERT INTO comercial (id, nombre, email, telefono, zona) VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_BY_ID_SQL =
            "SELECT id, nombre, email, telefono, zona FROM comercial WHERE id = ?";

     private static final String SELECT_ALL_SQL =
            "SELECT id, nombre, email, telefono, zona FROM  comercial ORDER BY id";



    private static final String SEARCH_SQL = """
                        SELECT id, nombre, email, telefono, zona
                        FROM comercial
                        WHERE CAST(id AS TEXT) ILIKE ? 
                            OR nombre ILIKE ?  
                            OR email ILIKE ?
                            OR telefono ILIKE ?
                            OR zona ILIKE ?
                        ORDER BY id                    
                        """;

    private static final String UPDATE_SQL =
            "UPDATE comercial SET nombre= ?, email = ?, telefono = ?, zona = ? WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM comercial WHERE id = ?";

    private static final String CLEAR_SQL =
            "DELETE FROM comercial";

    // ==========================================================
    // CRUD COMPLETO
    // ==========================================================

    // Insertar Comercial (CREATE)

    public void insert(Comercial c) throws SQLException {

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

            ps.setInt(1, c.getId());         // Parámetro 1 → columna id
            ps.setString(2, c.getNombre());  // Parámetro 2 → columna nombre
            ps.setString(3, c.getEmail());   // Parámetro 3 → columna email
            ps.setString(4, c.getTelefono());   // Parámetro 4 → columna telefono
            ps.setString(5, c.getZona());   // Parámetro 5 → columna zona

            ps.executeUpdate();

            // Recuperar el ID generado por PostgreSQL
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    c.setId(idGenerado);  // lo guardamos en el objeto
                }
            }

        }
    }

    //Buscar Comercial por ID (READ)

    public Comercial findById(int id) throws SQLException {
        // Devuelve el Comercial con el ID insertado y si no existe, devuelve null.

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {

            ps.setInt(1, id);  // Asignamos el id al parámetro ?

            try (ResultSet rs = ps.executeQuery()) {
                // executeQuery() devuelve un ResultSet ↔ una tabla virtual con las filas devueltas.

                if (rs.next()) {
                    // Si rs.next() = true → hay fila. Avanzamos a ella y leemos sus columnas.

                    return new Comercial(
                            rs.getInt("id"),          // Columna 'id'
                            rs.getString("nombre"),   // Columna 'nombre'
                            rs.getString("email"),     // Columna 'email'
                            rs.getString("telefono"),   // Columna 'telefono'
                            rs.getString("zona")     // Columna 'zona'
                    );
                }

                return null;
                // Si no existe devolvemos null para indicar "no encontrado".
            }
        }
    }

    //Buscar TODOS los Comerciales (READ)

    public List<Comercial> findAll() throws SQLException {
        // Devuelve una lista con todos los comerciales de la tabla.
        // Nunca devuelve null; si no hay datos, devuelve lista vacía.

        List<Comercial> out = new ArrayList<>();

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Iteramos por cada fila del ResultSet.
                // Cada fila se convierte en un objeto Comercial.

                Comercial c = new Comercial(
                        rs.getInt("id"),          // Columna 'id'
                        rs.getString("nombre"),   // Columna 'nombre'
                        rs.getString("email"),     // Columna 'email'
                        rs.getString("telefono"),   // Columna 'telefono'
                        rs.getString("zona")     // Columna 'zona'
                );

                out.add(c);   // Añadimos el comercial a la lista.
            }
        }

        return out;   // Devolvemos la lista completa.
    }

    //Actualizar Comerciales (UPDATE)

    public void update(Comercial c) throws SQLException {

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {

            ps.setInt(1, c.getId());         // Parámetro 1 → columna id
            ps.setString(2, c.getNombre());  // Parámetro 2 → columna nombre
            ps.setString(3, c.getEmail());   // Parámetro 3 → columna email
            ps.setString(4, c.getTelefono());   // Parámetro 4 → columna telefono
            ps.setString(5, c.getZona());   // Parámetro 5 → columna zona

            ps.executeUpdate();
        }
    }

    //Eliminar Comercial por ID (DELETE)

    public void delete(int id) throws SQLException {

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {

            ps.setInt(1,id);
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

    private Comercial mapRow(ResultSet rs) throws SQLException {

        Comercial c = new Comercial(
                rs.getInt("id"),          // Columna 'id'
                rs.getString("nombre"),   // Columna 'nombre'
                rs.getString("email"),     // Columna 'email'
                rs.getString("telefono"),   // Columna 'telefono'
                rs.getString("zona")     // Columna 'zona'
        );

        return c;
    }

}