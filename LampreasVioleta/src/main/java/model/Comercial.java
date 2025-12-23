package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Entidad principal "Comercial"
 * Relaciones:
 *  - 1:N con Cliente (un Comercial tiene muchos Clientes).
 */
public class Comercial {

    private Integer id; //PK
    private String nombre;
    private String email;
    private String telefono;
    private String zona;

    // Relacion con Cliente (1:N)

    private List<Cliente> clientes = new ArrayList<>();

    public Comercial(){}
    public Comercial(Integer id, String nombre, String email, String telefono, String zona){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.zona = zona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override public String toString() {
        return "Comercial{id=%d, nombre='%s', email='%s', telefono='%s', zona='%s'}".formatted(id, nombre, email,telefono,zona);
    }
}
