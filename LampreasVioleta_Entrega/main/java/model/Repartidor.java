package model;

import java.util.ArrayList;
import java.util.List;

public class Repartidor {


    private Integer id; //PK
    private String nombre;
    private String telefono;
    private String zona;

    // Relacion con Pedido 1:N
    private List<Pedido> pedidos = new ArrayList<>();

    public Repartidor(){}
    public Repartidor(Integer id, String nombre, String telefono, String zona){
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.zona = zona;
    }

    // =====================
    // GETTERS / SETTERS
    // =====================

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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override public String toString() {
        return "Repartidor{id=%d, nombre='%s', telefono='%s', zona='%s'}".formatted(id, nombre,telefono,zona);
    }
}
