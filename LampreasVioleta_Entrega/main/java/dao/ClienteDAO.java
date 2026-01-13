package dao;

import db.Db;
import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // ==========================================================
    // SENTENCIAS SQL PREPARADAS COMO CONSTANTES PARA CRUD
    // ==========================================================

    private static final String INSERT_SQL =
            "INSERT INTO cliente (id, nombre, email) VALUES (?, ?, ?)";

    private static final String SELECT_BY_ID_SQL =
            "SELECT id, nombre, email FROM cliente WHERE id = ?";

    private static final String SELECT_ALL_SQL =
            "SELECT id, nombre, email FROM cliente ORDER BY id";

    private static final String SEARCH_SQL =
            """
            SELECT id, nombre, email
            FROM cliente
            WHERE CAST(id AS TEXT) ILIKE ? 
                OR nombre ILIKE ?  
                OR email ILIKE ?
            ORDER BY id                    
            """;

    private static final String UPDATE_SQL =
            "UPDATE cliente SET nombre = ?, email = ? WHERE id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM cliente WHERE id = ?";

    private static final String CLEAR_SQL =
            "DELETE FROM cliente";


    // ==========================================================
    // CRUD COMPLETO
    // ==========================================================

    // Insertar cliente (CREATE)

    public void insert(Cliente c) throws SQLException {
        // Método público que inserta un cliente en la base de datos.
        // Recibe un objeto Cliente y lanza SQLException si algo sale mal.

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {

            // try-with-resources: la conexión y el PreparedStatement se cerrarán automáticamente
            // al final del bloque, aunque haya errores.

            ps.setInt(1, c.getId());         // Parámetro 1 → columna id
            ps.setString(2, c.getNombre());  // Parámetro 2 → columna nombre
            ps.setString(3, c.getEmail());   // Parámetro 3 → columna email

            ps.executeUpdate();
            // Ejecuta la sentencia. Como es un INSERT, no devuelve ResultSet.

            // Recuperar el ID generado por PostgreSQL
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    c.setId(idGenerado);  // lo guardamos en el objeto
                }
            }

        }
    }

    // Versión transaccional: usa una conexión que le pasa el servicio
    public void insert(Cliente c, Connection con) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(INSERT_SQL)) {
            ps.setInt(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getEmail());
            ps.executeUpdate();
        }
    }

    //Buscar Cliente por ID (READ)

    public Cliente findById(int id) throws SQLException {
        // Devuelve el Cliente cuyo id coincida con el parámetro.
        // Si no existe, devuelve null.

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {

            ps.setInt(1, id);  // Asignamos el id al parámetro ?

            try (ResultSet rs = ps.executeQuery()) {
                // executeQuery() devuelve un ResultSet ↔ una tabla virtual con las filas devueltas.

                if (rs.next()) {
                    // Si rs.next() = true → hay fila. Avanzamos a ella y leemos sus columnas.

                    return new Cliente(
                            rs.getInt("id"),          // Columna 'id'
                            rs.getString("nombre"),   // Columna 'nombre'
                            rs.getString("email")     // Columna 'email'
                    );
                }

                return null;
                // Si no hay resultado, devolvemos null para indicar "no encontrado".
            }
        }
    }


    //Buscar TODOS los clientes (READ)

    public List<Cliente> findAll() throws SQLException {

        List<Cliente> out = new ArrayList<>();

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente c = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email")
                );

                out.add(c);
            }
        }

        return out;
    }

    public List<Cliente> search(String filtro) throws SQLException {

        String patron = "%" + filtro + "%";

        try (Connection con = Db.getConnection();
           PreparedStatement pst = con.prepareStatement(SEARCH_SQL)) {
            pst.setString(1, patron);
            pst.setString(2, patron);
            pst.setString(3, patron);

            List<Cliente> out = new ArrayList<>();

            try(ResultSet rs = pst.executeQuery()){

                while (rs.next()){
                    out.add(mapRow(rs));
                }
            }
            return out;
        }
    }

    //Actualizar Cliente (UPDATE)

    public void update(Cliente c) throws SQLException {

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getEmail());
            ps.setInt(3, c.getId());

            ps.executeUpdate();
        }
    }

    //Eliminar cliente por ID (DELETE)

    public void delete(int id) throws SQLException {

        try (Connection con = Db.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // EXTRA: Borrar datos tabla Cliente

    public void clear() throws SQLException {

        try (Connection con = Db.getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(CLEAR_SQL);
        }
    }

    // ==========================================================
    // MAPEADOR
    // ==========================================================

    private Cliente mapRow(ResultSet rs) throws SQLException {

        Cliente c = new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email")
        );

        return c;
    }


}