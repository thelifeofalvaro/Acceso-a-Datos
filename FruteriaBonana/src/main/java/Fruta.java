/*
Creamos la clase fruta que nos permita añadir nuevas frutas
Usamos los campos requeridos en el enunciado
 */

import java.util.Locale;

public class Fruta {

    private int id;
    private String nombre;
    private double precioKg;
    private int stockKg;

    public Fruta(){
    }

    public Fruta(int id, String nombre, double precioKg, int stockKg) {
            this.id = id;
            this.nombre = nombre;
            this.precioKg = precioKg;
            this.stockKg = stockKg;
    }

    // Getters / Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioKg() {
        return precioKg;
    }

    public void setPrecioKg(double precioKg) {
        this.precioKg = precioKg;
    }

    public int getStockKg() {
        return stockKg;
    }

    public void setStockKg(int stockKg) {
        this.stockKg = stockKg;
    }

    // Como se va a mostrar por consola
    @Override
    public String toString() {
        return "Fruta {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioKg='" + precioKg + '\'' +
                ", stockKg=" + stockKg +
                '}';
    }

}
